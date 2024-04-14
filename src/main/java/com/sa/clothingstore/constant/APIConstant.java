package com.sa.clothingstore.constant;

public class APIConstant {
    // Common Routes
    public static final String VERSION = "/api/v1";
    public static final String SEARCH = "/search";
    public static final String ID = "/{id}";

    // Auth Routes
    public static final String AUTH = VERSION + "/auth";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String SIGNUP = "/signup";
    public static final String REFRESH_TOKEN = "/refresh-token";
    public static final String AUTH_ME = "/me";

    // Users Routes
    public static final String USERS = VERSION + "/users";
    public static final String USER_ID = "/{userId}";
    public static final String GETALL = "/all/{role}";
    public static final String CREATEADDRESS = "/{userId}/address";
    public static final String UPDATEADDRESS = "/{addressId}";
    public static final String CREATEUSER = "/{role}";
    public static final String VERIFYOTP = "verifyOtp/{otp}/{email}";
    public static final String CHANGEPASSWORD = "/changePassword/{email}";

    // Email Routes

    public static final String EMAIL = VERSION + "/email";
    public static final String SENDOTP = "/verifyEmail/{email}";

}
