package com.furkanisitan.core.criteria;

import com.furkanisitan.core.exceptions.InvalidFieldException;
import lombok.Getter;

import java.util.List;

@Getter
public final class RequestCriteria {

    private final PageCriteria pageCriteria;
    private final List<FilterCriteria> filterCriteria;

    public RequestCriteria(PageCriteria pageCriteria, List<FilterCriteria> filterCriteria) {
        this.pageCriteria = pageCriteria;
        this.filterCriteria = filterCriteria;
    }

    public static RequestCriteriaBuilder builder(Class<?> clazz) {
        return new RequestCriteriaBuilder(clazz);
    }

    /**
     * Creates a {@link RequestCriteria} instance.
     *
     * @param clazz  the {@link Class} instance of {@literal T}.
     * @param page   zero-based page index, must not be negative. default - {@value PageCriteria#DEFAULT_PAGE}
     * @param size   the size of the page to be returned, must be greater than 0. default - {@value PageCriteria#DEFAULT_SIZE}
     * @param sort   a {@link String} array containing the field names and directions.
     * @param filter a {@link String} array containing the filter texts.
     * @param <T>    the type of class.
     * @return a {@link RequestCriteria} instance.
     * @throws InvalidFieldException if the {@literal clazz} doesn't have a field of a specified name.
     */
    public static <T> RequestCriteria of(Class<T> clazz, Integer page, Integer size, String[] sort, String[] filter) {

        return new RequestCriteria(PageCriteria.of(clazz, page, size, sort), FilterCriteria.ofAll(clazz, filter));
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
         * @param page default - {@value PageCriteria#DEFAULT_PAGE}
         * @param size default - {@value PageCriteria#DEFAULT_SIZE}
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
            return RequestCriteria.of(clazz, page, size, sort, filter);
        }

    }

}
