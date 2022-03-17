package com.furkanisitan.core.criteria;

import com.furkanisitan.core.exceptions.InvalidFieldException;
import com.furkanisitan.core.utils.GenericUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This interface contains helper methods for {@link com.furkanisitan.core.criteria} package.
 */
interface Helpers {

    Integer DEFAULT_PAGE = 0;
    Integer DEFAULT_SIZE = 20;

    /**
     * Returns a list of {@link  FilterCriteria}.
     *
     * @param clazz  clazz the {@link Class} instance of {@literal T}.
     * @param filter a {@link String} array containing the filter texts.
     * @param <T>    the type of class.
     * @return a list of {@link  FilterCriteria}.
     */
    static <T> List<FilterCriteria> getFilterCriteria(Class<T> clazz, String[] filter) {

        if (ArrayUtils.isEmpty(filter)) return Collections.emptyList();

        var filterCriteria = new ArrayList<FilterCriteria>();
        for (var f : filter)
            filterCriteria.add(FilterCriteria.of(clazz, f));
        return filterCriteria;
    }

    /**
     * Creates a {@link Pageable} instance.
     *
     * @param page zero-based page index, must not be negative. default: {@value #DEFAULT_PAGE}
     * @param size the size of the page to be returned, must be greater than 0. default: {@value #DEFAULT_SIZE}
     * @param sort the {@link Sort} instance.
     * @return a {@link Pageable} instance if {@literal page} or {@literal size} not null, {@code null} otherwise.
     */
    static Pageable getPageable(Integer page, Integer size, Sort sort) {

        if (page == null && size == null) return null;

        page = page == null || page < 0 ? DEFAULT_PAGE : page;
        size = size == null || size < 1 ? DEFAULT_SIZE : size;

        return PageRequest.of(page, size, sort);
    }

    /**
     * {@code sort} defaults to {@link Sort#unsorted()}.
     *
     * @see #getPageable(Integer, Integer, Sort)
     */
    static Pageable getPageable(Integer page, Integer size) {
        return getPageable(page, size, Sort.unsorted());
    }

    /**
     * Creates a {@link Sort} instance by {@literal sort} array.
     *
     * @param clazz clazz the {@link Class} instance of {@literal T}.
     * @param sort  a {@link String} array containing the field names and directions.
     * @param <T>   the type of class.
     * @return a {@link Sort} instance.
     */
    static <T> Sort getSort(Class<T> clazz, String[] sort) {

        if (ArrayUtils.isEmpty(sort)) return Sort.unsorted();

        List<Sort.Order> orders = new ArrayList<>();
        for (var s : sort) {
            var order = getOrder(clazz, s);
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
    static <T> Sort.Order getOrder(Class<T> clazz, String order) {

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
