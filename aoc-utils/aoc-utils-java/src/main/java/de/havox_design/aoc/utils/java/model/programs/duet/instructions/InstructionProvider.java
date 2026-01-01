package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provider for {@link Instruction}s.
 */
public class InstructionProvider {
    /**
     * Regular instruction for instruction calls.
     */
    public static final Pattern REGEX = Pattern.compile("([a-z]+)\\s+(\\w+)\\s*(-?\\w+)?");
    /**
     * Identifier for {@link Add} instruction.
     */
    public static final String IDENTIFIER_ADD = "add";
    /**
     * Identifier for {@link JumpIfGreaterZero} instruction.
     */
    public static final String IDENTIFIER_JUMP_GREATER_ZERO = "jgz";
    /**
     * Identifier for {@link JumpIfNotZero} instruction.
     */
    public static final String IDENTIFIER_JUMP_NOT_ZERO = "jnz";
    /**
     * Identifier for {@link Modulo} instruction.
     */
    public static final String IDENTIFIER_MODULO = "mod";
    /**
     * Identifier for {@link Multiply} instruction.
     */
    public static final String IDENTIFIER_MULTIPLY = "mul";
    /**
     * Identifier for {@link Receive} instruction.
     */
    public static final String IDENTIFIER_RECOVER_RECEIVE = "rcv";
    /**
     * Identifier for {@link Set} instruction.
     */
    public static final String IDENTIFIER_SET = "set";
    /**
     * Identifier for {@link Sound} instruction.
     */
    public static final String IDENTIFIER_SOUND_SEND = "snd";
    /**
     * Identifier for {@link Substract} instruction.
     */
    public static final String IDENTIFIER_SUBSTRACT = "sub";

    /**
     * Internal instructor.
     */
    private InstructionProvider() {
    }

    /**
     * Creates the correct {@link Instruction} for a given row.
     *
     * @param row the row
     * @return the {@link Instruction}
     */
    public static Instruction createInstruction(String row) {
        return createInstruction(row, false);
    }

    /**
     * Creates the correct {@link Instruction} for a given row depending on the part of the AoC 2017 day 18.
     * @param row the row
     * @param isPart2 is it AoC2017 day18
     * @return the {@link Instruction}
     */
    public static Instruction createInstruction(String row, boolean isPart2) {
        final Matcher matcher = REGEX.matcher(row);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Input '" + row + "' does not match pattern " + REGEX.pattern());
        }

        return switch (matcher.group(1)) {
            case IDENTIFIER_SOUND_SEND -> isPart2 ? new Send(matcher.group(2)) : new Sound(matcher.group(2));
            case IDENTIFIER_ADD -> new Add(matcher.group(2), matcher.group(3));
            case IDENTIFIER_MULTIPLY -> new Multiply(matcher.group(2), matcher.group(3));
            case IDENTIFIER_MODULO -> new Modulo(matcher.group(2), matcher.group(3));
            case IDENTIFIER_SET -> new Set(matcher.group(2), matcher.group(3));
            case IDENTIFIER_RECOVER_RECEIVE -> isPart2 ? new Receive(matcher.group(2)) : new Recover(matcher.group(2));
            case IDENTIFIER_JUMP_GREATER_ZERO -> new JumpIfGreaterZero(matcher.group(2), matcher.group(3));
            case IDENTIFIER_JUMP_NOT_ZERO -> new JumpIfNotZero(matcher.group(2), matcher.group(3));
            case IDENTIFIER_SUBSTRACT -> new Substract(matcher.group(2), matcher.group(3));
            default -> throw new IllegalArgumentException(matcher.toString());
        };
    }
}
