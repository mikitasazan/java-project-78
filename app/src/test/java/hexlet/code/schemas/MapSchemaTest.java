package hexlet.code.schemas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hexlet.code.Validator;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

class MapSchemaTest {

    private final Validator validator = new Validator();

    @Test
    void notRequiredAllowsMissingValue() {
        var schema = validator.map();

        assertTrue(schema.isValid(null));
    }

    @Test
    void requiredRejectsMissingValue() {
        var schema = validator.map().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void sizeof() {
        var schema = validator.map().required().sizeof(2);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }

    @Test
    void repeatedSizeofReplacesThePreviousOne() {
        var schema = validator.map().sizeof(2).sizeof(1);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));
    }
}
