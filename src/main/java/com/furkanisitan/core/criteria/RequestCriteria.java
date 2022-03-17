package com.furkanisitan.core.criteria;

import com.furkanisitan.core.exceptions.InvalidFieldException;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
public final class RequestCriteria {

    private final Sort sort;
    private final Pageable pageable;
    private final List<FilterCriteria> filterCriteria;

    private RequestCriteria(Pageable pageable, Sort sort, List<FilterCriteria> filterCriteria) {
        this.pageable = pageable;
        this.sort = sort;
        this.filterCriteria = filterCriteria;
    }

    public static RequestCriteriaBuilder builder(Class<?> clazz) {
        return new RequestCriteriaBuilder(clazz);
    }

    /**
     * Creates a {@link RequestCriteria} instance.
     *
     * @param clazz  the {@link Class} instance of {@literal T}.
     * @param page   zero-based page index, must not be negative. default - {@value Helpers#DEFAULT_PAGE}
     * @param size   the size of the page to be returned, must be greater than 0. default - {@value Helpers#DEFAULT_SIZE}
     * @param sort   a {@link String} array containing the field names and directions.
     * @param filter a {@link String} array containing the filter texts.
     * @param <T>    the type of class.
     * @return a {@link RequestCriteria} instance.
     * @throws InvalidFieldException if the {@literal clazz} doesn't have a field of a specified name.
     */
    public static <T> RequestCriteria of(Class<T> clazz, String[] filter, Integer page, Integer size, String[] sort) {
        var s = Helpers.getSort(clazz, sort);
        var pageable = Helpers.getPageable(page, size, s);

        return new RequestCriteria(pageable, s, Helpers.getFilterCriteria(clazz, filter));
    }

    public static class RequestCriteriaBuilder {

        private final Class<?> clazz;
        private Integer page;
        private Integer size;
        private String[] sort;
        private String[] filter;

        public RequestCriteriaBuilder(Class<?> clazz) {
            this.clazz = clazz;
        }

        public RequestCriteriaBuilder sort(String... sort) {
            this.sort = sort;
            return this;
        }

        /**
         * @param page default - {@value Helpers#DEFAULT_PAGE}
         * @param size default - {@value Helpers#DEFAULT_SIZE}
         */
        public RequestCriteriaBuilder page(Integer page, Integer size) {
            this.page = page;
            this.size = size;
            return this;
        }

        public RequestCriteriaBuilder filter(String... filter) {
            this.filter = filter;
            return this;
        }

        public RequestCriteria build() {
            return RequestCriteria.of(clazz, filter, page, size, sort);
        }

    }

}
