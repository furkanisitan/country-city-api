package com.furkanisitan.core.api;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

/**
 * This interface contains helper methods for {@link com.furkanisitan.core.api} package.
 */
interface Helpers {

    /**
     * Creates an error list from {@literal violations}.
     *
     * @param violations the set of ConstraintViolation.
     * @return a {@link String} array containing error messages.
     */
    static String[] buildErrors(Set<ConstraintViolation<?>> violations) {

        var errors = new String[violations.size()];

        var i = 0;
        for (var violation : violations) {
            var leafNode = getLeafNode(violation.getPropertyPath());
            errors[i++] = String.format("%s: %s.", leafNode.isPresent() ? leafNode.get().getName() : String.valueOf(i), violation.getMessage());
        }

        return errors;
    }

    /**
     * Creates an error list from {@literal objectErrors}.
     *
     * @param objectErrors the list of {@link ObjectError}.
     * @return a {@link String} array containing error messages.
     */
    static String[] buildErrors(List<ObjectError> objectErrors) {

        var errors = new String[objectErrors.size()];

        var i = 0;
        for (var objectError : objectErrors) {
            errors[i++] = String.format("%s: %s.", objectError instanceof FieldError ?
                    ((FieldError) objectError).getField() : objectError.getObjectName(), objectError.getDefaultMessage());
        }

        return errors;
    }

    /**
     * Return the leaf {@link Path.Node} of  {@literal path}.
     *
     * @param path the {@link Path} value.
     * @return the leaf {@link Path.Node} of  {@literal path}.
     */
    private static Optional<Path.Node> getLeafNode(Path path) {
        return StreamSupport.stream(path.spliterator(), false).reduce((a, b) -> b);
    }

}
