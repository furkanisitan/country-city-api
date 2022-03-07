package com.furkanisitan.countrycityapi.api;

import com.furkanisitan.core.api.RestControllerExceptionHandler;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
class ExceptionHandler extends RestControllerExceptionHandler {
}
