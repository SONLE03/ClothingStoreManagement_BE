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
    public static final String REFRESH_TOKEN = "/refreshtoken";
    public static final String AUTH_ME = "/me";

    // Users Routes
    public static final String USERS = VERSION + "/users";
    public static final String USER_ID = "/{userId}";
    public static final String GETALL = "/all/{role}";
    public static final String CREATEADDRESS = "/{userId}/address";
    public static final String UPDATEADDRESS = "/{addressId}";
    public static final String VERIFYOTP = "verifyOtp/{otp}/{email}";
    public static final String CHANGEPASSWORD = "/changePassword/{email}";

    // Email Routes
    public static final String EMAIL = VERSION + "/email";
    public static final String SENDOTP = "/verifyEmail/{email}";

    // Branch Routes
    public static final String BRANCHS = VERSION + "/branch";
    public static final String BRANCH_ID = "/{branchId}";

    // Product Gender Routes
    public static final String PRODUCTGENDERS = VERSION + "/productGender";
    public static final String PRODUCTGENDER_ID = "/{productGenderId}";

    // Category Routes
    public static final String CATEGORIES = VERSION + "/category";
    public static final String CATEGORY_ID = "/{categoryId}";

    // Image Routes
    public static final String IMAGES = VERSION + "/image";
    public static final String IMAGE_ID = "/{imageId}";

    // Color Routes
    public static final String COLORS = VERSION + "/color";
    public static final String COLOR_ID = "/{colorId}";

    // Size Routes
    public static final String SIZES = VERSION + "/size";
    public static final String SIZE_ID = "/{sizeId}";

    // Product Routes
    public static final String PRODUCTS = VERSION + "/products";
    public static final String PRODUCT_ID = "/{productId}";

    // Import routes
    public static final String IMPORTS = VERSION + "/imports";
    public static final String IMPORT_ID = "/{importId}";

    // Coupon
    public static final String COUPONS = VERSION + "/coupons";
    public static final String COUPON_ID = "couponId/{couponId}";
    public static final String COUPON_STATUS = "status/{status}";

    //Cart
    public static final String CARTS = VERSION + "/carts";
    public static final String CART_ID = "/{customerId}";

}
