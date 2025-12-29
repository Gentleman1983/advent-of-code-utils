package de.havox_design.aoc.utils.java.model.arrays.bidirectional_growing_array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.IntFunction;

/**
 * Test class for {@link BidirectionalGrowingArray}.
 */
class BidirectionalGrowingArrayTest {
    /**
     * Simple test for constructor {@link BidirectionalGrowingArray#BidirectionalGrowingArray(IntFunction)}.
     */
    @Test
    void testConstructorIntSource() {
        IntFunction<Number[]> numberArrayProducer = Number[]::new;
        IntFunction<Number[]> doubleArrayProducer = Double[]::new;
        IntFunction<Number[]> floatArrayProducer = Float[]::new;
        IntFunction<Number[]> longArrayProducer = Long[]::new;
        IntFunction<Number[]> integerArrayProducer = Integer[]::new;

        Assertions.assertAll(
                () -> List
                        .of(numberArrayProducer,
                                doubleArrayProducer,
                                floatArrayProducer,
                                longArrayProducer,
                                integerArrayProducer)
                        .forEach(arrayProducer -> {
                                    BidirectionalGrowingArray<Number> bidiArray = new BidirectionalGrowingArray<>(
                                            arrayProducer
                                    );
                                    Assertions.assertAll(
                                            () -> Assertions.assertNotNull(bidiArray),
                                            () -> Assertions.assertInstanceOf(BidirectionalGrowingArray.class, bidiArray),
                                            () -> Assertions.assertTrue(bidiArray.isEmpty()),
                                            () -> Assertions.assertEquals(0, bidiArray.size())
                                    );
                                }
                        )
        );
    }

    /**
     * Simple test for constructor {@link BidirectionalGrowingArray#BidirectionalGrowingArray(IntFunction, int)}.
     *
     * @param startIdex the start indices
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 5, 42, 123, -23})
    void testConstructorIntSourceInteger(Integer startIdex) {
        IntFunction<Number[]> numberArrayProducer = Number[]::new;
        IntFunction<Number[]> doubleArrayProducer = Double[]::new;
        IntFunction<Number[]> floatArrayProducer = Float[]::new;
        IntFunction<Number[]> longArrayProducer = Long[]::new;
        IntFunction<Number[]> integerArrayProducer = Integer[]::new;

        Assertions.assertAll(
                () -> List
                        .of(numberArrayProducer,
                                doubleArrayProducer,
                                floatArrayProducer,
                                longArrayProducer,
                                integerArrayProducer)
                        .forEach(arrayProducer -> {
                                    BidirectionalGrowingArray<Number> bidiArray = new BidirectionalGrowingArray<>(
                                            arrayProducer,
                                            startIdex
                                    );
                                    Assertions.assertAll(
                                            () -> Assertions.assertNotNull(bidiArray),
                                            () -> Assertions.assertInstanceOf(BidirectionalGrowingArray.class, bidiArray),
                                            () -> Assertions.assertTrue(bidiArray.isEmpty()),
                                            () -> Assertions.assertEquals(0, bidiArray.size())
                                    );
                                }
                        )
        );
    }

    /**
     * Simple test checking basic data manipulation functions.
     */
    @Test
    void testDataManipulation() {
        List<Integer> values = List.of(0, 37, 125, 42, 23, -17, 13, 7, 666, 0);
        List<Integer> indices = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        int offset = 42;
        IntFunction<Integer[]> arrayProducer = Integer[]::new;
        BidirectionalGrowingArray<Integer> bidiArray = new BidirectionalGrowingArray<>(arrayProducer);
        BidirectionalGrowingArray<Integer> bidiArrayOffset = new BidirectionalGrowingArray<>(arrayProducer, offset);

        Assertions.assertAll(
                () -> indices.forEach(index -> {
                            bidiArray.put(index, values.get(index));
                            Assertions.assertAll(
                                    () -> Assertions.assertEquals(index + 1, bidiArray.size()),
                                    () -> Assertions.assertEquals(values.get(index), bidiArray.get(index)),
                                    () -> Assertions.assertArrayEquals(new int[]{0, index}, bidiArray.getRange())
                            );

                            bidiArrayOffset.put(index + offset, values.get(index));
                            Assertions.assertAll(
                                    () -> Assertions.assertEquals(index + 1, bidiArrayOffset.size()),
                                    () -> Assertions.assertEquals(values.get(index), bidiArrayOffset.get(index + offset)),
                                    () -> Assertions.assertArrayEquals(
                                            new int[]{offset, index + offset},
                                            bidiArrayOffset.getRange()
                                    )
                            );
                        }
                )
        );
    }

    /**
     * Test checking empty element checks on methods.
     *
     * @param withOffset test instance with or without offset
     */
    @ParameterizedTest
    @ValueSource(booleans = {
            true,
            false
    })
    void testGetRangeWithEmptyArray(boolean withOffset) {
        int offset = 42;
        IntFunction<Integer[]> arrayProducer = Integer[]::new;
        BidirectionalGrowingArray<Integer> bidiArray = withOffset ?
                new BidirectionalGrowingArray<>(arrayProducer, offset) :
                new BidirectionalGrowingArray<>(arrayProducer);

        Assertions.assertAll(
                () -> Assertions.assertTrue(bidiArray.isEmpty()),
                () -> Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, bidiArray::getRange)
        );
    }

    /**
     * Checks if NULL is returned on non-existing entries.
     *
     * @param withOffset test instance with or without offset
     */
    @ParameterizedTest
    @ValueSource(booleans = {
            true,
            false
    })
    void testGetFromNotExistingEntries(boolean withOffset) {
        int offset = 42;
        IntFunction<Integer[]> arrayProducer = Integer[]::new;
        BidirectionalGrowingArray<Integer> bidiArray = withOffset ?
                new BidirectionalGrowingArray<>(arrayProducer, offset) :
                new BidirectionalGrowingArray<>(arrayProducer);

        Assertions.assertAll(
                () -> Assertions.assertTrue(bidiArray.isEmpty()),
                () -> Assertions.assertNull(bidiArray.get(23))
        );
    }

    /**
     * Basic test testing the functionality of the {@link BidirectionalGrowingArray#iterator()}.
     */
    @Test
    void testIterator() {
        List<Integer> values = List.of(0, 37, 125, 42, 23, -17, 13, 7, 666, 0);
        List<Integer> indices = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        int offset = 42;
        IntFunction<Integer[]> arrayProducer = Integer[]::new;
        BidirectionalGrowingArray<Integer> bidiArray = new BidirectionalGrowingArray<>(arrayProducer);
        BidirectionalGrowingArray<Integer> bidiArrayOffset = new BidirectionalGrowingArray<>(arrayProducer, offset);

        indices.forEach(index -> {
            bidiArray.put(index, values.get(index));
            bidiArrayOffset.put(index + offset, values.get(index));
        });
        Iterator<Integer> bidiArrayIterator = bidiArray.iterator();
        Iterator<Integer> bidiArrayOffsetIterator = bidiArrayOffset.iterator();


        Assertions.assertAll(
                () -> Assertions.assertNotNull(bidiArrayIterator),
                () -> Assertions.assertNotNull(bidiArrayOffsetIterator),
                () -> {
                    for(int i=0; i <= 9; i++) {
                        Assertions.assertAll(
                                () -> Assertions.assertTrue(bidiArrayIterator.hasNext()),
                                () -> Assertions.assertTrue(bidiArrayOffsetIterator.hasNext())
                        );

                        int finalI = i;
                        int value = bidiArrayIterator.next();
                        int valueOffset = bidiArrayOffsetIterator.next();

                        Assertions.assertAll(
                                () -> Assertions.assertEquals(value, values.get(finalI)),
                                () -> Assertions.assertEquals(valueOffset, values.get(finalI))
                        );
                    }

                    Assertions.assertAll(
                            () -> Assertions.assertFalse(bidiArrayIterator.hasNext()),
                            () -> Assertions.assertThrows(NoSuchElementException.class, bidiArrayIterator::next),
                            () -> Assertions.assertFalse(bidiArrayOffsetIterator.hasNext()),
                            () -> Assertions.assertThrows(NoSuchElementException.class, bidiArrayOffsetIterator::next)
                    );
                }
        );
    }
}
