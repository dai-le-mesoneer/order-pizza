package com.assignment.pizza.mapper;

import java.util.List;

/**
 * @author dai-le-mesoneer
 * @since 10/18/24
 */

public abstract class BaseMapper<I, O> {
    public abstract O map(I input);

    public List<O> mapAsList(List<I> inputLists) {
        return inputLists.stream()
                .map(this::map)
                .toList();
    }
}
