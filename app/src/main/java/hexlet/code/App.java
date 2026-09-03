package hexlet.code;

public final class App {

    public static void main(String[] args) {
        var v = new Validator();
        var schema = v.string().required().minLength(5).contains("hex");

        System.out.println(schema.isValid("hexlet")); // true
        System.out.println(schema.isValid("java")); // false
    }
}
