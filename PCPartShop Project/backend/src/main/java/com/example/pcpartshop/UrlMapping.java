package com.example.pcpartshop;

public class UrlMapping {
    public static final String API_PATH = "/api";
    public static final String ENTITY = "/{id}";
    public static final String SEARCH = "/search";
    public static final String QUERY = "/{query}";
    public static final String ORDER = "/order";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";
    public static final String CONFIGURATIONS = API_PATH + "/configurations";
    public static final String CPU = API_PATH + "/cpu";
    public static final String GPU = API_PATH + "/gpu";
    public static final String MEMORY = API_PATH + "/memory";
    public static final String MOTHERBOARD = API_PATH + "/motherboard";
    public static final String PSU = API_PATH + "/psu";
    public static final String PC_CASE = API_PATH + "/pcCase";
    public static final String STORAGE = API_PATH + "/storage";

    public static final String EXPORT_REPORT = "/export/{id}";

    public static final String MESSAGE = "/message";
    public static final String TOPIC = "/topic";
    public static final String ALERTS = "/alerts";
}
