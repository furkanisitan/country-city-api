package com.furkanisitan.core.criteria;

import com.furkanisitan.core.exceptions.InvalidFieldException;
import com.furkanisitan.core.utils.GenericUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface contains helper methods for {@link com.furkanisitan.core.criteria} package.
 */
interface Helpers {

    /**
     * @param clazz clazz the {@link Class} instance of {@literal T}.
     * @param sort  a text containing the field names and directions.
     * @param <T>   the type of class.
     * @return a {@link Sort} instance.
     */
    static <T> Sort getSort(Class<T> clazz, String[] sort) {

        List<Sort.Order> orders = new ArrayList<>();
        for (var s : sort) {
            orders.add(getOrder(clazz, s));
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
     */
    static <T> Sort.Order getOrder(Class<T> clazz, String order) {

        if (StringUtils.isEmpty(order))
            return null;

        var fieldBeginIndex = 1;

        var direction = order.substring(0, 1);
        if (!SortDirection.isValid(direction)) {
            direction = SortDirection.DEFAULT_DIRECTION;
            fieldBeginIndex = 0;
        }

        var field = order.substring(fieldBeginIndex);
        if (!GenericUtils.hasField(clazz, field))
            throw new InvalidFieldException(field);

        if (SortDirection.ASC.equals(direction))
            return Sort.Order.asc(field);
        return Sort.Order.desc(field);
    }

}
