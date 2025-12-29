package de.havox_design.aoc.utils.java.model.arrays.bidirectional_growing_array;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.IntFunction;

/**
 *
 * @param <T> the data type
 */
public class BidirectionalGrowingArray<T> implements Iterable<T> {
    /**
     * The array producer function.
     */
    private final IntFunction<T[]> arrayProducer;

    /**
     * The internal array.
     */
    private T[] array;
    /**
     * The offset for the start index.
     */
    private int offset;

    /**
     * Constructor without offset.
     *
     * @param arrayProducer the array producing function
     */
    public BidirectionalGrowingArray(final IntFunction<T[]> arrayProducer) {
        this(arrayProducer, 0);
    }

    /**
     * Constructor having an index offset.
     *
     * @param arrayProducer the array producing function
     * @param startIndex the index offset
     */
    public BidirectionalGrowingArray(final IntFunction<T[]> arrayProducer, final int startIndex) {
        this.arrayProducer = arrayProducer;
        this.array = arrayProducer.apply(0);
        this.offset = startIndex;
    }

    /**
     * Checks the internal array to provide space for the given entries on the {@link BidirectionalGrowingArray}.
     *
     * @param neededInternalIndex the index to be used
     * @return the needed internal index
     */
    private int checkSize(final int neededInternalIndex) {
        final int delta = neededInternalIndex < 0 ? -neededInternalIndex : neededInternalIndex - array.length + 1;

        if (delta > 0) {
            final T[] newArray = arrayProducer.apply(array.length + delta);

            if (neededInternalIndex < 0) {
                System.arraycopy(array, 0, newArray, delta, array.length);
            } else {
                System.arraycopy(array, 0, newArray, 0, array.length);
            }

            array = newArray;

            if (neededInternalIndex < 0) {
                offset -= delta;
                return 0;
            }
        }

        return neededInternalIndex;
    }

    /**
     * Collects an element form the array.
     *
     * @param index the index to fetch
     * @return the data or NULL
     */
    public T get(final int index) {
        final int internalIndex = mapIndex(index);

        return (internalIndex < 0 || internalIndex >= array.length) ? null : array[internalIndex];
    }

    /**
     * Returns the index range of the {@link BidirectionalGrowingArray}.
     *
     * @return the minimum and maximum index of the array, e.g. {23, 42}
     * @throws ArrayIndexOutOfBoundsException if called on empty array
     */
    public int[] getRange() throws ArrayIndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return new int[] { offset, offset + array.length - 1 };
    }

    /**
     * Checks if the array is empty.
     *
     * @return true, if empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the {@link BidirectionalGrowingArray} iterator.
     *
     * @return the iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**
             * The position on the array.
             */
            int position;

            /**
             * Checks if there are more elements on the array.
             *
             * @return true, if there are additional elements.
             */
            @Override
            public boolean hasNext() {
                return position < array.length;
            }

            /**
             * Returns the next element on the array.
             *
             * @return the next element
             * @throws NoSuchElementException if no next element is available
             */
            @Override
            public T next() throws NoSuchElementException {
                if (hasNext()) {
                    return array[position++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Maps between the given index using the offset and the internal array index.
     *
     * @param index the index using the offset.
     * @return the internal index
     */
    private int mapIndex(final int index) {
        return index - offset;
    }

    /**
     * Adds an entry to the {@link BidirectionalGrowingArray}.
     *
     * @param index the index the entry has to be put to. If already existing the entry will be updated.
     * @param newValue the new value
     * @return the old value
     */
    public T put(final int index, final T newValue) {
        int internalIndex = mapIndex(index);

        internalIndex = checkSize(internalIndex);

        final T result = array[internalIndex];

        array[internalIndex] = newValue;

        return result;
    }

    /**
     * Checks the size of the array.
     *
     * @return the size
     */
    public int size() {
        return array.length;
    }
}
