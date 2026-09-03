package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Common state and rules shared by every schema type: the required flag and
 * a set of named constraints. A constraint is keyed by its own name, so
 * calling the same kind of constraint again (e.g. {@code minLength(10)}
 * followed by {@code minLength(4)}) replaces the earlier one instead of
 * adding a second, independent check — the project's own spec calls this
 * out explicitly for every constraint kind.
 *
 * <p>{@link #required()} returns {@code BaseSchema<T>} here; every concrete
 * subclass overrides it with a covariant return type (its own class) so a
 * fluent chain like {@code v.string().required().minLength(5)} keeps
 * type-checking without a cast, while a schema can still be stored and
 * passed around as the shared {@code BaseSchema<T>} type — the shape used by
 * {@link MapSchema#shape}.
 */
public abstract class BaseSchema<T> {

    private final Map<String, Predicate<T>> constraints = new LinkedHashMap<>();
    private boolean required = false;

    public BaseSchema<T> required() {
        required = true;
        return this;
    }

    protected final void addConstraint(String name, Predicate<T> predicate) {
        constraints.put(name, predicate);
    }

    /**
     * Whether the given value counts as "no value" for this schema type.
     * {@code null} always does; {@link StringSchema} additionally treats an
     * empty string as missing, per the spec's second rule.
     */
    protected boolean isMissing(T value) {
        return value == null;
    }

    public final boolean isValid(T value) {
        if (isMissing(value)) {
            return !required;
        }
        return constraints.values().stream().allMatch(predicate -> predicate.test(value));
    }
}
