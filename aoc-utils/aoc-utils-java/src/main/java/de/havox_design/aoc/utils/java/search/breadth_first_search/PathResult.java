package de.havox_design.aoc.utils.java.search.breadth_first_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Instance of the breadth first search path result.
 *
 * @param <T> the type of data
 */
public class PathResult<T> {
    /**
     * The node.
     */
    private final T node;
    /**
     * The distance.
     */
    private final long distance;
    /**
     * Is it the target?
     */
    private final boolean target;
    /**
     * the link to the previous path result.
     */
    private final PathResult<T> prev;
    /**
     * The path.
     */
    private List<T> path;

    /**
     * The constructor.
     *
     * @param node the node
     * @param distance the distance
     * @param target is this node a target?
     * @param prev the previous path result
     */
    public PathResult(T node, long distance, boolean target, PathResult<T> prev) {
        this.node = node;
        this.distance = distance;
        this.target = target;
        this.prev = prev;
    }

    /**
     * Returns the distance.
     *
     * @return the distance
     */
    public long getDistance() {
        return distance;
    }

    /**
     * Returns the current node.
     *
     * @return the node
     */
    public T getNode() {
        return node;
    }

    /**
     * Checks if the current node is the target.
     *
     * @return true, if we found the target
     */
    public boolean isTarget() {
        return target;
    }

    /**
     * Returns the search path.
     *
     * @return the search path
     */
    public List<T> getPath() {
        if (path == null) {
            List<T> list = new ArrayList<>();

            for (PathResult<T> e = this; e != null; e = e.prev) {
                list.add(e.node);
            }

            Collections.reverse(list);
            path = Collections.unmodifiableList(list);
        }

        return path;
    }
}
