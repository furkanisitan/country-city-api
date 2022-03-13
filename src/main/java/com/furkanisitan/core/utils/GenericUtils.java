package com.furkanisitan.core.utils;

import com.furkanisitan.core.exceptions.NoSuchDeclaredFieldException;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

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
     * Returns a {@link Field} object that reflects the specified declared field of the class or interface represented by this {@literal clazz} object.
     * It also controls the inherited class.
     * If Field is found, it sets the accessible flag to true.
     *
     * @param clazz the {@link Class} instance of {@literal T}.
     * @param name  the field name.
     * @param <T>   the type of class.
     * @return the {@link Field} object for the specified field in this class
     * @throws NoSuchDeclaredFieldException if a field with the specified {@literal name} is not found.
     */
    static <T> Field getField(Class<T> clazz, String name) {
        Field declaredField;
        try {
            declaredField = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            try {
                declaredField = clazz.getSuperclass().getDeclaredField(name);
            } catch (NoSuchFieldException ex) {
                throw new NoSuchDeclaredFieldException(e.getMessage());
            }
        }
        declaredField.setAccessible(true);
        return declaredField;
    }

}
