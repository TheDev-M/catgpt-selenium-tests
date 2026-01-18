package com.codecool.catgpt.config;

public class TestConfig {
    public static final String BASE_URL = "http://localhost:3000";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String SIGNUP_URL = BASE_URL + "/signup";
    public static final String HOME_URL = BASE_URL + "/";
    public static final String CATBOX_URL = BASE_URL + "/catbox";

    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 15;
    public static final int PAGE_LOAD_TIMEOUT = 30;

    public static final String TEST_USERNAME = "testuser";
    public static final String TEST_PASSWORD = "Test1234!";
    public static final String INVALID_USERNAME = "invaliduser123";
    public static final String INVALID_PASSWORD = "wrongpassword";
    
    private TestConfig() {}
}
