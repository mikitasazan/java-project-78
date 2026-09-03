package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }

    @Override
    protected boolean isMissing(String value) {
        return value == null || value.isEmpty();
    }

    public StringSchema minLength(int length) {
        addConstraint("minLength", value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addConstraint("contains", value -> value.contains(substring));
        return this;
    }
}
