package de.havox_design.aoc.utils.java.model.programs.duet;

import de.havox_design.aoc.utils.java.model.programs.duet.instructions.Instruction;
import de.havox_design.aoc.utils.java.model.programs.duet.instructions.Multiply;
import de.havox_design.aoc.utils.java.model.programs.duet.instructions.Send;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * The state of the Duet program.
 */
public class State {
    /**
     * The current sound.
     */
    private BigInteger currentSound;
    /**
     * The counter for {@link Multiply} {@link Instruction}s.
     */
    private int multiplicationInstructionCount;
    /**
     * The other {@link State}.
     */
    private State otherState;
    /**
     * the position.
     */
    private int position;
    /**
     * The program ID.
     */
    private final int programId;
    /**
     * The queue.
     */
    private final Queue<BigInteger> queue = new LinkedList<>();
    /**
     * The recovered sound.
     */
    private BigInteger recoveredSound;
    /**
     * The register map.
     */
    private final Map<String, BigInteger> register = new TreeMap<>();
    /**
     * Indicator, if the program is running.
     */
    private boolean running = true;
    /**
     * The counter for {@link Send} {@link Instruction}s.
     */
    private BigInteger sentCount = BigInteger.ZERO;

    /**
     * The constructor.
     *
     * @param programId the program ID
     */
    public State(final int programId) {
        this.programId = programId;
        register.put("p", BigInteger.valueOf(programId));
    }

    /**
     * Counts another {@link Multiply} {@link Instruction}.
     */
    public void countMultiplicationInstruction() {
        multiplicationInstructionCount++;
    }

    /**
     * Returns the current sound.
     *
     * @return the current sound
     */
    public BigInteger getCurrentSound() {
        return currentSound;
    }

    /**
     * Returns the {@link Multiply} {@link Instruction} count.
     *
     * @return the count
     */
    public int getMultiplicationInstructionCount() {
        return multiplicationInstructionCount;
    }

    /**
     * Returns the other {@link State}.
     *
     * @return the stare
     */
    public State getOtherState() {
        return otherState;
    }

    /**
     * Returns the position.
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Returns the program ID.
     * @return the ID
     */
    public int getProgramId() {
        return programId;
    }

    /**
     * Returns the value of a variable.
     *
     * @param variableName the variable name
     * @return the value
     */
    public BigInteger getValue(final String variableName) {
        final BigInteger value = register.get(variableName);

        return value == null ? BigInteger.ZERO : value;
    }

    /**
     * Returns the recovered sound.
     * @return the sound
     */
    public BigInteger getRecoveredSound() {
        return recoveredSound;
    }

    /**
     * Returns the {@link Send} {@link Instruction} count.
     *
     * @return the count
     */
    public BigInteger getSentCount() {
        return sentCount;
    }

    /**
     * Increments the position.
     */
    public void incrementPosition() {
        position++;
    }

    /**
     * Increments the {@link Send} {@link Instruction} count.
     */
    public void incrementSentCount() {
        sentCount = sentCount.add(BigInteger.ONE);
    }

    /**
     * Sends a value.
     *
     * @param value the value
     */
    public void send(final BigInteger value) {
        otherState.queue.add(value);
        otherState.running = true;
        incrementSentCount();
    }

    /**
     * Checks if the program is running.
     *
     * @return true, if running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Jumps to a pointer position.
     *
     * @param intValue the position
     */
    public void jump(final int intValue) {
        this.position += intValue - 1;
    }

    /**
     * Plays a given sound.
     *
     * @param value the value
     */
    public void playSound(final BigInteger value) {
        this.currentSound = value;
    }

    /**
     * Receives a value.
     *
     * @return the value
     */
    public BigInteger receiveValue() {
        return queue.isEmpty() ? null : queue.poll();
    }

    /**
     * Recovers a sound.
     *
     * @param sound the sound
     */
    public void recoverSound(final BigInteger sound) {
        recoveredSound = sound;
    }

    /**
     * Updates the other {@link State}.
     *
     * @param otherState the other {@link State}
     */
    public void setOtherState(final State otherState) {
        this.otherState = otherState;
        otherState.otherState = this;
    }

    /**
     * Sets if the program is running.
     *
     * @param running true, if running
     */
    public void setRunning(final boolean running) {
        this.running = running;
    }

    /**
     * Sets a value to a given variable.
     *
     * @param variableName the variable name
     * @param newValue the value
     */
    public void setValue(final String variableName, final BigInteger newValue) {
        register.put(variableName, newValue);
    }
}
