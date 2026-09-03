package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer, NumberSchema> {

    public NumberSchema positive() {
        addConstraint("positive", value -> value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addConstraint("range", value -> value >= min && value <= max);
        return this;
    }
}
