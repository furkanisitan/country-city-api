package com.furkanisitan.countrycityapi.api;

public final class Examples {

    public static final class Language {

        public static final class Success {
            public static final String GET_ALL = "{\"success\":true,\"message\":\"The request has been processed successfully.\",\"payload\":[{\"id\":1,\"name\":\"Turkish\",\"code\":\"tr\"}]}";
            public static final String GET = "{\"success\":true,\"message\":\"The request has been processed successfully.\",\"payload\":{\"id\":1,\"name\":\"Turkish\",\"code\":\"tr\"}}";
            public static final String CREATE = "{\"success\":true,\"message\":\"Resource(s) added successfully.\",\"payload\":{\"id\":1,\"name\":\"Turkish\",\"code\":\"tr\"}}";
        }
    }

    public static final class Error {
        public static final String BAD_REQUEST_NULL = "{\"success\":false,\"message\":\"Invalid bad request.\",\"errors\":[\"{fieldName}: must not be null.\"]}";
        public static final String BAD_REQUEST_BLANK = "{\"success\":false,\"message\":\"Invalid bad request.\",\"errors\":[\"{fieldName}: must not be blank.\"]}";
        public static final String BAD_REQUEST_MISMATCH = "{\"success\":false,\"message\":\"Invalid bad request.\",\"errors\":[\"The {param} in the route does not match the {field} in the body.\"]}";
        public static final String CONFLICT_NOT_UNIQUE = "{\"success\":false,\"message\":\"A unique constraint error has occurred.\",\"errors\":[\"{fieldName}: must be unique. (rejectedValue: fieldValue})\"]}";
        public static final String NOT_FOUND_BY_ID = "{\"success\":false,\"message\":\"The resource not found.\",\"errors\":[\"{recordName} not found for parameters (id='55').\"]}";
    }

}
