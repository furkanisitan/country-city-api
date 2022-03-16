package com.furkanisitan.core.criteria;

import com.furkanisitan.core.exceptions.InvalidFieldException;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class RequestCriteria {

    @Getter
    private final Sort sort;
    @Getter
    private final Pageable pageable;

    private RequestCriteria(Pageable pageable, Sort sort) {
        this.pageable = pageable;
        this.sort = sort;
    }

    /**
     * Creates a {@link RequestCriteria} instance.
     *
     * @param clazz clazz clazz the {@link Class} instance of {@literal T}.
     * @param page  zero-based page index, must not be negative. default: {@value Helpers#DEFAULT_PAGE}
     * @param size  the size of the page to be returned, must be greater than 0. default: {@value Helpers#DEFAULT_SIZE}
     * @param sort  a {@link String} array containing the field names and directions.
     * @param <T>   the type of class.
     * @return a {@link RequestCriteria} instance.
     * @throws InvalidFieldException if the {@literal clazz} doesn't have a field of a specified name.
     */
    public static <T> RequestCriteria of(Class<T> clazz, Integer page, Integer size, String[] sort) {
        var s = Helpers.getSort(clazz, sort);
        var pageable = Helpers.getPageable(page, size, s);
        return new RequestCriteria(pageable, s);
    }

    /**
     * {@code sort} defaults to {@code null}.
     *
     * @see #of(Class, Integer, Integer, String[])
     */
    public static <T> RequestCriteria of(Class<T> clazz, Integer page, Integer size) {
        return of(clazz, page, size, null);
    }
}
