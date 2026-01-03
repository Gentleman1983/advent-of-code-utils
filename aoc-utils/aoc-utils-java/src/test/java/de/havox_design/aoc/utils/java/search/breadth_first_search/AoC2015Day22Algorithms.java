package de.havox_design.aoc.utils.java.search.breadth_first_search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class AoC2015Day22Algorithms {

    private AoC2015Day22Algorithms() {
    }

    public static <S> List<S> breadthFirstSearch(
            final S state,
            final Function<AoC2015Day22StateWrapper<S>,
                    Iterable<S>> stateProducer,
            final Consumer<AoC2015Day22StateWrapper<S>> consumer,
            final Predicate<AoC2015Day22StateWrapper<S>> acceptanceCriterion
    ) {
        final Queue<AoC2015Day22StateWrapper<S>> queue = new LinkedList<>();
        AoC2015Day22StateWrapper<S> currentState = new AoC2015Day22StateWrapper<>(state, null);

        queue.add(currentState);

        if (consumer != null) {
            consumer.accept(currentState);
        }

        while (!queue.isEmpty()) {
            currentState = queue.poll();

            final Iterable<S> possibleStates = stateProducer.apply(currentState);

            for (final S possibleState : possibleStates) {
                final AoC2015Day22StateWrapper<S> newState = new AoC2015Day22StateWrapper<>(possibleState, currentState);

                if (consumer != null) {
                    consumer.accept(newState);
                }

                if (acceptanceCriterion.test(newState)) {
                    return newState.getStates();
                }

                queue.add(newState);
            }
        }

        return Collections.emptyList();
    }
}
