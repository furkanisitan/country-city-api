package com.furkanisitan.core.results;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class Result {

    private final boolean success;
    private final String message;
    private final List<String> errors;

    Result(boolean success, String message, List<String> errors) {
        this.success = success;
        this.message = message;
        this.errors = errors;
    }

    public static Result ok() {
        return new Result(true, null, Collections.emptyList());
    }

    public static Result ok(String message) {
        return new Result(true, message, Collections.emptyList());
    }

    public static Result fail(String message) {
        return new Result(false, message, Collections.emptyList());
    }

    public static Result fail(String message, String... errors) {
        return new Result(false, message, List.of(errors));
    }

}
