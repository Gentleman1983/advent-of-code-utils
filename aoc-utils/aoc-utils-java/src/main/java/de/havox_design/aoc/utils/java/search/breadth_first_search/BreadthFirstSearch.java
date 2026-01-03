package de.havox_design.aoc.utils.java.search.breadth_first_search;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Implementation of breadth first search algorithm.
 */
public final class BreadthFirstSearch {
    /**
     * The constructor.
     */
    private BreadthFirstSearch() {
    }

    /**
     * Runs the search.
     *
     * @param source the source node
     * @param neighborProvider the provider of node neighbors
     * @param targetPredicate the {@link Predicate} describing the target node
     * @return the result
     * @param <T> the type of node
     */
    public static <T> Optional<PathResult<T>> run(
            T source,
            Function<T, Iterable<T>> neighborProvider,
            Predicate<T> targetPredicate
    ) {
        Map<T, PathResult<T>> map = run(List.of(source), neighborProvider, targetPredicate);

        return map
                .values()
                .stream()
                .filter(PathResult::isTarget)
                .min(Comparator.comparing(PathResult::getDistance));
    }

    /**
     * Runs a search step.
     *
     * @param source the source node
     * @param neighborProvider the provider of node neighbors
     * @return the updated data map
     * @param <T> the type of node
     */
    public static <T> Map<T, PathResult<T>> run(T source, Function<T, Iterable<T>> neighborProvider) {
        return run(List.of(source), neighborProvider, t -> false);
    }

    /**
     * Runs a search step.
     *
     * @param sources the source node
     * @param neighborProvider the provider of node neighbors
     * @param targetPredicate the {@link Predicate} describing the target node
     * @return the updated data map
     * @param <T> the type of node
     */
    @SuppressWarnings("squid:S3824")
    public static <T> Map<T, PathResult<T>> run(
            Iterable<T> sources,
            Function<T, Iterable<T>> neighborProvider,
            Predicate<T> targetPredicate
    ) {
        Map<T, PathResult<T>> results = new HashMap<>();
        Deque<T> queue = new ArrayDeque<>();

        for (T source : sources) {
            results.put(source, new PathResult<>(source, 0, targetPredicate.test(source), null));
            queue.add(source);
        }

        while (!queue.isEmpty()) {
            T node = queue.poll();
            PathResult<T> result = results.get(node);

            if (result.isTarget()) {
                break;
            }

            for (T neighbor : neighborProvider.apply(node)) {
                if (!results.containsKey(neighbor)) {
                    results.put(
                            neighbor,
                            new PathResult<>(neighbor, result.getDistance() + 1, targetPredicate.test(neighbor), result)
                    );
                    queue.add(neighbor);
                }
            }
        }

        return results;
    }
}
