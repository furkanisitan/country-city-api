package com.furkanisitan.core.utils;

import com.furkanisitan.core.exceptions.NoSuchDeclaredFieldException;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public interface GenericUtils {

    /**
     * Returns an array of {@link Field} objects reflecting all the fields declared by the class or interface represented by {@literal clazz} object.
     * This includes inherited fields.
     *
     * @param clazz the {@link Class} instance of {@literal T}.
     * @param <T>   the type of class.
     * @return the array of {@link Field} objects representing all the declared fields of {@literal clazz}.
     */
    static <T> Field[] getFields(Class<T> clazz) {
        var fields = clazz.getDeclaredFields();
        var superFields = clazz.getSuperclass().getDeclaredFields();

        return ArrayUtils.addAll(fields, superFields);
    }

    /**
     * @throws NoSuchDeclaredFieldException if a field with the specified {@literal name} is not found.
     * @see #getFieldOf(Class, String)
     */
    static <T> Field getField(Class<T> clazz, String name) {
        return getFieldOf(clazz, name).orElseThrow(() -> new NoSuchDeclaredFieldException(name));
    }

    /**
     * Returns an optional {@link Field} object that reflects the specified declared field of the class or interface represented by this {@literal clazz} object.
     * It also controls the inherited class.
     * If Field is found, it sets the accessible flag to true.
     *
     * @param clazz the {@link Class} instance of {@literal T}.
     * @param name  the field name.
     * @param <T>   the type of class.
     * @return the optional {@link Field} object for the specified field in this class.
     */
    static <T> Optional<Field> getFieldOf(Class<T> clazz, String name) {
        return Arrays.stream(getFields(clazz)).filter(x -> x.getName().equals(name)).findFirst();
    }

    /**
     * Returns whether {@literal clazz} has a field with the specified {@literal name}.
     *
     * @param clazz the {@link Class} instance of {@literal T}.
     * @param name  the field name.
     * @param <T>   the type of class.
     * @return {@code true} if the field with the given name exists, {@code false} otherwise.
     */
    static <T> boolean hasField(Class<T> clazz, String name) {
        return Arrays.stream(getFields(clazz)).anyMatch(x -> x.getName().equals(name));
    }
}
