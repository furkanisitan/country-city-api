package com.furkanisitan.core.criteria;

import com.furkanisitan.core.exceptions.InvalidFieldException;
import com.furkanisitan.core.utils.GenericUtils;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class PageCriteria {

    public static final Integer DEFAULT_PAGE = 0;
    public static final Integer DEFAULT_SIZE = 20;

    private final Pageable pageable;
    private final Sort sort;

    public PageCriteria(Pageable pageable, Sort sort) {
        this.pageable = pageable;
        this.sort = sort;
    }

    public static <T> PageCriteria of(Class<T> clazz, Integer page, Integer size, String... sort) {
        var s = sort(clazz, sort);
        var pageable = page(page, size, s);

        return new PageCriteria(pageable, s);
    }

    /**
     * Creates a {@link Pageable} instance.
     *
     * @param page zero-based page index, must not be negative. default: {@value #DEFAULT_PAGE}
     * @param size the size of the page to be returned, must be greater than 0. default: {@value #DEFAULT_SIZE}
     * @param sort the {@link Sort} instance.
     * @return a {@link Pageable} instance if {@literal page} or {@literal size} not null, {@code null} otherwise.
     */
    private static Pageable page(Integer page, Integer size, Sort sort) {

        if (page == null && size == null) return null;

        page = page == null || page < 0 ? DEFAULT_PAGE : page;
        size = size == null || size < 1 ? DEFAULT_SIZE : size;

        return PageRequest.of(page, size, sort);
    }

    /**
     * Creates a {@link Sort} instance by {@literal sort} array.
     *
     * @param clazz the {@link Class} instance of {@literal T}.
     * @param sort  a {@link String} array containing the field names and directions.
     * @param <T>   the type of class.
     * @return a {@link Sort} instance.
     */
    private static <T> Sort sort(Class<T> clazz, String[] sort) {

        if (ArrayUtils.isEmpty(sort)) return Sort.unsorted();

        List<Sort.Order> orders = new ArrayList<>();
        for (var s : sort) {
            var order = order(clazz, s);
            if (order != null && orders.stream().noneMatch(x -> x.getProperty().equals(order.getProperty())))
                orders.add(order);
        }

        return Sort.by(orders);
    }

    /**
     * Creates a {@link Sort.Order} instance by {@literal order}.
     *
     * @param clazz the {@link Class} instance of {@literal T}.
     * @param order a text containing the field name and direction.
     * @param <T>   the type of class.
     * @return a {@link Sort.Order} instance if the direction is not empty, {@code null} otherwise.
     * @throws InvalidFieldException if the {@literal clazz} doesn't have a field of a specified name.
     */
    private static <T> Sort.Order order(Class<T> clazz, String order) {

        if (StringUtils.isAllBlank(order)) return null;

        var fieldBeginIndex = 1;

        var direction = order.substring(0, 1);
        if (!SortDirection.isValid(direction)) {
            direction = SortDirection.DEFAULT_DIRECTION;
            fieldBeginIndex = 0;
        }

        var field = order.substring(fieldBeginIndex);
        if (StringUtils.isAllBlank(field)) return null;
        if (!GenericUtils.hasField(clazz, field)) throw new InvalidFieldException(field);

        if (SortDirection.ASC.equals(direction)) return Sort.Order.asc(field);
        return Sort.Order.desc(field);
    }
}
