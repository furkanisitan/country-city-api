package com.furkanisitan.core.criteria;

import com.furkanisitan.core.exceptions.InvalidFilterOperatorException;
import lombok.Getter;

public enum FilterOperator {
    EQUALS(":"),
    NOT_EQUALS("!:"),
    STARTS_WITH(":%"),
    ENDS_WITH("%:"),
    CONTAINS("%"),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL(">:"),
    LESS_THAN_OR_EQUAL("<:");

    @Getter
    private final String text;

    FilterOperator(String text) {
        this.text = text;
    }

    /**
     * Creates a {@link FilterOperator} instance.
     *
     * @param text the filter operator.
     * @return a {@link FilterOperator} instance.
     * @throws InvalidFilterOperatorException if the text is not valid.
     */
    public static FilterOperator of(String text) {

        return switch (text) {
            case ":" -> EQUALS;
            case "!:" -> NOT_EQUALS;
            case ":%" -> STARTS_WITH;
            case "%:" -> ENDS_WITH;
            case "%" -> CONTAINS;
            case ">" -> GREATER_THAN;
            case "<" -> LESS_THAN;
            case ">:" -> GREATER_THAN_OR_EQUAL;
            case "<:" -> LESS_THAN_OR_EQUAL;
            default -> throw new InvalidFilterOperatorException(text);
        };
    }

    /**
     * Returns whether the specified {@literal text} for the {@link #FilterOperator} is valid.
     *
     * @param text the filter operator.
     * @return {@code true} if the text is valid, {@code false} otherwise.
     */
    public static boolean isValid(String text) {
        return switch (text) {
            case ":", "!:", ":%", "%:", "%", ">", "<", ">:", "<:" -> true;
            default -> false;
        };
    }

}
