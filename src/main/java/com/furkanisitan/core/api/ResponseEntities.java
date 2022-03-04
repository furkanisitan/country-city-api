package com.furkanisitan.core.api;

import com.furkanisitan.core.results.Result;
import com.furkanisitan.core.results.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class ResponseEntities {

    /**
     * Creates a {@link ResponseEntity} object that produces a @link {@link HttpStatus#OK}} response without value.
     *
     * @return The created {@link ResponseEntity} for the response.
     */
    public static ResponseEntity<Object> ok() {
        return ResponseEntity.ok(Result.ok(ResponseMessages.OK));
    }

    /**
     * Creates a {@link ResponseEntity} object that produces a  {@link HttpStatus#OK} response with value.
     *
     * @param value The content value to format in the entity body.
     * @param <T>   The type of value.
     * @return The created {@link ResponseEntity} for the response.
     */
    public static <T> ResponseEntity<Object> ok(T value) {
        return ResponseEntity.ok(
                ResultData.<T>builder().payload(value).success(true).message(ResponseMessages.OK).build());
    }

    /**
     * Creates a {@link ResponseEntity} object that produces a {@link HttpStatus#CREATED} response with value.
     *
     * @param uri   The URI at which the content has been created.
     * @param value The content value to format in the entity body.
     * @param <T>   The type of value.
     * @return The created {@link ResponseEntity} for the response.
     */
    public static <T> ResponseEntity<Object> created(URI uri, T value) {
        return ResponseEntity.created(uri).body(
                ResultData.<T>builder().payload(value).success(true).message(ResponseMessages.CREATED).build());
    }

    /**
     * Creates a {@link ResponseEntity} object that produces a {@link HttpStatus#NO_CONTENT}.
     *
     * @return The created {@link ResponseEntity} for the response.
     */
    public static ResponseEntity<Object> noContent() {
        return ResponseEntity.noContent().build();
    }

}
