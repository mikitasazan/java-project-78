package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>, MapSchema> {

    public MapSchema sizeof(int size) {
        addConstraint("sizeof", value -> value.size() == size);
        return this;
    }
}
