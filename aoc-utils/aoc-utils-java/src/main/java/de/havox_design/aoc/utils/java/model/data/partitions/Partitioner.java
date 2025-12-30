package de.havox_design.aoc.utils.java.model.data.partitions;

import java.util.List;

/**
 * Interface for partitioners for data elements.
 *
 * @param <T> the data type
 */
public interface Partitioner<T> {
    /**
     * Patitions the data
     *
     * @param toPartition the input
     * @return the partitioned output
     */
    List<List<T>> partition(List<T> toPartition);
}
