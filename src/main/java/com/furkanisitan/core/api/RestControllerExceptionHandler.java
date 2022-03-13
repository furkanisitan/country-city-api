package com.furkanisitan.core.api;

import com.furkanisitan.core.exceptions.*;
import com.furkanisitan.core.results.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

public abstract class RestControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    Result handle(ConstraintViolationException e) {
        return Result.fail(ResponseMessages.ERR_VALIDATION, Helpers.buildErrors(e.getConstraintViolations()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Result handle(MethodArgumentNotValidException e) {
        return Result.fail(ResponseMessages.ERR_VALIDATION, Helpers.buildErrors(e.getBindingResult().getAllErrors()));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ForeignKeyConstraintException.class)
    @ResponseBody
    Result handle(ForeignKeyConstraintException e) {
        return Result.fail(ResponseMessages.ERR_FOREIGN_KEY_CONSTRAINT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseBody
    Result handle(RecordNotFoundException e) {
        return Result.fail(ResponseMessages.ERR_NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RouteBodyMismatchException.class)
    @ResponseBody
    Result handle(RouteBodyMismatchException e) {
        return Result.fail(ResponseMessages.ERR_BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UniqueConstraintException.class)
    @ResponseBody
    Result handle(UniqueConstraintException e) {
        return Result.fail(ResponseMessages.ERR_UNIQUE_CONSTRAINT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoSuchDeclaredFieldException.class)
    @ResponseBody
    Result handle(NoSuchDeclaredFieldException e) {
        return Result.fail(ResponseMessages.ERR_INTERNAL_SERVER, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CreateInstanceException.class)
    @ResponseBody
    Result handle(CreateInstanceException e) {
        return Result.fail(ResponseMessages.ERR_INTERNAL_SERVER, e.getMessage());
    }

}
