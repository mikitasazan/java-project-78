package hexlet.code.schemas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

class NumberSchemaTest {

    private final Validator validator = new Validator();

    @Test
    void notRequiredAllowsMissingValue() {
        var schema = validator.number();

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));
    }

    @Test
    void requiredRejectsMissingValue() {
        var schema = validator.number().positive().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    void positive() {
        var schema = validator.number().required().positive();

        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));
    }

    @Test
    void range() {
        var schema = validator.number().required().positive().range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void repeatedRangeReplacesThePreviousOne() {
        var schema = validator.number().range(5, 10).range(6, 9);

        assertFalse(schema.isValid(5));
        assertFalse(schema.isValid(10));
        assertTrue(schema.isValid(9));
    }
}
