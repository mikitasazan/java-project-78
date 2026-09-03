package hexlet.code.schemas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

class StringSchemaTest {

    private final Validator validator = new Validator();

    @Test
    void notRequiredAllowsMissingValue() {
        var schema = validator.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    void notRequiredIgnoresOtherConstraintsOnMissingValue() {
        var schema = validator.string().minLength(5).contains("hex");

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    void requiredRejectsMissingValue() {
        var schema = validator.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    void minLength() {
        var schema = validator.string().minLength(5);

        assertFalse(schema.isValid("java"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void contains() {
        var schema = validator.string().contains("hex");

        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("java"));
    }

    @Test
    void combinedConstraints() {
        var schema = validator.string().required().minLength(5).contains("hex");

        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("java"));
        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void repeatedMinLengthReplacesThePreviousOne() {
        var schema = validator.string().minLength(10).minLength(4);

        assertTrue(schema.isValid("Hexlet"));
    }

    @Test
    void repeatedContainsReplacesThePreviousOne() {
        var schema = validator.string()
                .contains("wh")
                .contains("what")
                .contains("whatthe");

        assertFalse(schema.isValid("what does the fox say"));
    }
}
