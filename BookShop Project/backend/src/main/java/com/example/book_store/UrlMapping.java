package com.example.book_store;

public class UrlMapping {
    public static final String API_PATH = "/api";
    public static final String ENTITY = "/{id}";
    public static final String QUERY = "/{query}";

    public static final String BOOKS = API_PATH + "/books";
    public static final String SEARCH_BOOKS = "/search";

    public static final String USERS = API_PATH + "/users";
    public static final String SEARCH_USERS = "/search";

    public static final String EXPORT_REPORT = "/export/{type}";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";
}
