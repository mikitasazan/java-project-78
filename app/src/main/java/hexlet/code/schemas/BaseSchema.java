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
 * out explicitly for every constraint kind, contains included.
 *
 * <p>{@code S} is the concrete subclass, so fluent methods declared here
 * (like {@link #required()}) can return that subclass rather than the bare
 * base type, keeping chains like {@code v.string().required().minLength(5)}
 * type-correct without a cast.
 */
public abstract class BaseSchema<T, S extends BaseSchema<T, S>> {

    private final Map<String, Predicate<T>> constraints = new LinkedHashMap<>();
    private boolean required = false;

    @SuppressWarnings("unchecked")
    public final S required() {
        required = true;
        return (S) this;
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
