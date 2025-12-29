package de.havox_design.aoc.utils.java.model.computer.aoc2019;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.stream.Collectors.joining;

public class AoC2019Day09SensorBoost implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";
    private static final long MODE_SENSOR_BOOST = 2L;
    private static final long MODE_STANDARD = 1L;

    private final List<Long> input;

    public AoC2019Day09SensorBoost(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static String processTask1(String fileName) {
        AoC2019Day09SensorBoost instance = new AoC2019Day09SensorBoost(fileName);

        return instance.processTask1();
    }

    public static String processTask2(String fileName) {
        AoC2019Day09SensorBoost instance = new AoC2019Day09SensorBoost(fileName);

        return instance.processTask2();
    }

    public String processTask1() {
        return compute(MODE_STANDARD);
    }

    public String processTask2() {
        return compute(MODE_SENSOR_BOOST);
    }

    private String compute(long inputNumber ) {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();

        in.add( inputNumber );

        IntComputer.runComputer( input, in, out, false );

        return out
                .stream()
                .map( String::valueOf )
                .collect( joining( "," ) );
    }
}
