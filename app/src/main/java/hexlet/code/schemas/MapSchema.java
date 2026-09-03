package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        addConstraint("sizeof", value -> value.size() == size);
        return this;
    }

    /**
     * Binds a schema to each named property of the Map, so isValid() also
     * validates the value under every key in {@code schemas} against its
     * own schema. Every property in this project is a String, matching the
     * step's own worked example (firstName/lastName), so a missing key
     * simply reads as {@code null}, which each property's own schema
     * already handles via {@link BaseSchema#isMissing}.
     */
    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        addConstraint("shape", value -> schemas.entrySet().stream()
                .allMatch(entry -> entry.getValue().isValid((String) value.get(entry.getKey()))));
        return this;
    }
}
