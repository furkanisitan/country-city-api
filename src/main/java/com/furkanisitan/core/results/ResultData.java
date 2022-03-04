package com.furkanisitan.core.results;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResultData<T> extends Result {

    private final T payload;

    @Builder
    ResultData(boolean success, String message, List<String> errors, T payload) {
        super(success, message, errors);
        this.payload = payload;
    }
}
