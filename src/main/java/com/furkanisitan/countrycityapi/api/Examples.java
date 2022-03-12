package com.furkanisitan.countrycityapi.api;

public final class Examples {

    public static final class Language {

        public static final class Success {
            public static final String GET_ALL = "{\"success\":true,\"message\":\"The request has been processed successfully.\",\"payload\":[{\"id\":1,\"name\":\"Turkish\",\"code\":\"tr\"}]}";
            public static final String GET = "{\"success\":true,\"message\":\"The request has been processed successfully.\",\"payload\":{\"id\":1,\"name\":\"Turkish\",\"code\":\"tr\"}}";
            public static final String CREATE = "{\"success\":true,\"message\":\"Resource(s) added successfully.\",\"payload\":{\"id\":1,\"name\":\"Turkish\",\"code\":\"tr\"}}";
        }
    }

    public static final class Country {

        public static final class Success {
            public static final String GET_ALL = "{\"success\":true,\"message\":\"The request has been processed successfully.\",\"payload\":[{\"id\":1,\"name\":\"Turkey\",\"code\":\"TR\",\"lifeExpectancy\":78.6}]}";
            public static final String GET = "{\"success\":true,\"message\":\"The request has been processed successfully.\",\"payload\":{\"id\":1,\"name\":\"Turkey\",\"code\":\"TR\",\"lifeExpectancy\":78.6,\"languages\":[{\"languageId\":1,\"languageName\":\"Turkish\",\"languageCode\":\"tr\",\"official\":true}],\"cities\":[{\"id\":1,\"name\":\"Ankara\",\"population\":5747325,\"countryCode\":\"TR\"}]}}";
            public static final String CREATE = "{\"success\":true,\"message\":\"Resource(s) added successfully.\",\"payload\":{\"id\":1,\"name\":\"Turkey\",\"code\":\"TR\",\"lifeExpectancy\":78.6,\"languages\":[{\"languageId\":1,\"languageName\":\"Turkish\",\"languageCode\":\"tr\",\"official\":true}]}}";
        }
    }

    public static final class City {

        public static final class Success {
            public static final String GET_ALL = "{\"success\":true,\"message\":\"The request has been processed successfully.\",\"payload\":[{\"id\":1,\"name\":\"Ankara\",\"population\":5747325,\"countryId\":1}]}";
            public static final String GET = "{\"success\":true,\"message\":\"The request has been processed successfully.\",\"payload\":{\"id\":1,\"name\":\"Ankara\",\"population\":5747325,\"country\":{\"id\":1,\"code\":\"TR\",\"name\":\"Turkey\",\"lifeExpectancy\":78.6}}}";
            public static final String CREATE = "{\"success\":true,\"message\":\"Resource(s) added successfully.\",\"payload\":{\"id\":1,\"name\":\"Ankara\",\"population\":5747325,\"country\":{\"id\":1,\"code\":\"TR\",\"name\":\"Turkey\",\"lifeExpectancy\":78.6}}}";
        }
    }

    public static final class Error {
        public static final String BAD_REQUEST_NULL = "{\"success\":false,\"message\":\"Invalid bad request.\",\"errors\":[\"{fieldName}: must not be null.\"]}";
        public static final String BAD_REQUEST_BLANK = "{\"success\":false,\"message\":\"Invalid bad request.\",\"errors\":[\"{fieldName}: must not be blank.\"]}";
        public static final String BAD_REQUEST_POSITIVE = "{\"success\":false,\"message\":\"Invalid bad request.\",\"errors\":[\"{fieldName}: must be greater than 0.\"]}";
        public static final String BAD_REQUEST_MISMATCH = "{\"success\":false,\"message\":\"Invalid bad request.\",\"errors\":[\"The {param} in the route does not match the {field} in the body.\"]}";
        public static final String CONFLICT_NOT_UNIQUE = "{\"success\":false,\"message\":\"A unique constraint error has occurred.\",\"errors\":[\"{fieldName}: must be unique. (rejectedValue: fieldValue})\"]}";
        public static final String CONFLICT_FOREIGN_KEY = "{\"success\":false,\"message\":\"A foreign key constraint error has occurred.\",\"errors\":[\"A foreign key with the '{key}: {value}' does not exist.\"]}";
        public static final String NOT_FOUND_BY_ID = "{\"success\":false,\"message\":\"The resource not found.\",\"errors\":[\"{recordName} not found for parameters (id='55').\"]}";
    }

}
