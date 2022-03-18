package com.furkanisitan.core.utils;

import com.furkanisitan.core.exceptions.CreateInstanceException;
import com.furkanisitan.core.exceptions.NoSuchDeclaredFieldException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public interface ExampleUtils {

    /**
     * Create a new {@link Example} using the {@link ExampleMatcher.GenericPropertyMatchers#exact()}.
     *
     * @param clazz the {@link Class} instance of {@literal T}.
     * @param name  the field name.
     * @param value the field value.
     * @param <T>   the type of probe.
     * @param <V>   the type of value.
     * @return a new {@link Example} instance.
     * @throws NoSuchDeclaredFieldException if a field with the specified {@literal name} is not found.
     * @throws CreateInstanceException      if an error occurs while generating an object from {@literal clazz}.
     */
    static <T, V> Example<T> getExample(Class<T> clazz, String name, V value) {
        try {
            var probe = clazz.getDeclaredConstructor().newInstance();

            var declaredField = GenericUtils.getField(clazz, name);
            declaredField.setAccessible(true);
            declaredField.set(probe, value);

            var exampleMatcher = ExampleMatcher.matchingAny().withMatcher(name, ExampleMatcher.GenericPropertyMatchers.exact());

            return Example.of(probe, exampleMatcher);
        } catch (NoSuchDeclaredFieldException e) {
            throw e;
        } catch (Exception e) {
            throw new CreateInstanceException(e.getMessage());
        }
    }

}
