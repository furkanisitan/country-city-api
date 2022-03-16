package com.furkanisitan.core.criteria;

public final class SortDirection {

    public static final String ASC = "+";
    public static final String DESC = "-";
    public static final String DEFAULT_DIRECTION = ASC;

    /**
     * Returns whether the specified text for the direction is valid.
     *
     * @param direction the direction text.
     * @return {@code true} if the direction is valid, {@code false} otherwise.
     */
    public static boolean isValid(String direction) {
        return ASC.equals(direction) || DESC.equals(direction);
    }

    /**
     * Returns itself if the {@literal direction} is valid, otherwise {@link #DEFAULT_DIRECTION}.
     *
     * @param direction the direction text.
     * @return itself if the {@literal direction} is valid, otherwise {@link #DEFAULT_DIRECTION}.
     */
    public static String selfOrDefault(String direction) {
        if (isValid(direction))
            return direction;
        return DEFAULT_DIRECTION;
    }

}
