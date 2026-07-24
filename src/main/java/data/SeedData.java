package data;

import java.util.List;

public class SeedData {

    private SeedData() {
    }

    public static final String HELLO_MESSAGE =
            "Hello from my server";

    public static final List<String> USERS = List.of(
            "Junaid",
            "Ali",
            "Ahmed"
    );

    public static final String USER_CREATED =
            "{\"message\":\"User created successfully\"}";
}