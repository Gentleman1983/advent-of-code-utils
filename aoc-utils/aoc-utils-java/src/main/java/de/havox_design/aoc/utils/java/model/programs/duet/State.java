package de.havox_design.aoc.utils.java.model.programs.duet;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class State {
    private BigInteger currentSound;
    private int multiplicationInstructionCount;
    private State otherState;
    private int position;
    private final int programId;
    private final Queue<BigInteger> queue = new LinkedList<>();
    private BigInteger recoveredSound;
    private final Map<String, BigInteger> register = new TreeMap<>();
    private boolean running = true;
    private BigInteger sentCount = BigInteger.ZERO;

    public State(final int programId) {
        this.programId = programId;
        register.put("p", BigInteger.valueOf(programId));
    }

    public void countMultiplicationInstruction() {
        multiplicationInstructionCount++;
    }

    public BigInteger getCurrentSound() {
        return currentSound;
    }

    public int getMultiplicationInstructionCount() {
        return multiplicationInstructionCount;
    }

    public State getOtherState() {
        return otherState;
    }

    public int getPosition() {
        return position;
    }

    public int getProgramId() {
        return programId;
    }

    public BigInteger getValue(final String variableName) {
        final BigInteger value = register.get(variableName);

        return value == null ? BigInteger.ZERO : value;
    }

    public BigInteger getRecoveredSound() {
        return recoveredSound;
    }

    public BigInteger getSentCount() {
        return sentCount;
    }

    public void incrementPosition() {
        position++;
    }

    public void incrementSentCount() {
        sentCount = sentCount.add(BigInteger.ONE);
    }

    public void send(final BigInteger value) {
        otherState.queue.add(value);
        otherState.running = true;
        incrementSentCount();
    }

    public boolean isRunning() {
        return running;
    }

    public void jump(final int intValue) {
        this.position += intValue - 1;
    }

    public void playSound(final BigInteger value) {
        this.currentSound = value;
    }

    public BigInteger receiveValue() {
        return queue.isEmpty() ? null : queue.poll();
    }

    public void recoverSound(final BigInteger sound) {
        recoveredSound = sound;
    }

    public void setOtherState(final State otherState) {
        this.otherState = otherState;
        otherState.otherState = this;
    }

    public void setRunning(final boolean running) {
        this.running = running;
    }

    public void setValue(final String variableName, final BigInteger newValue) {
        register.put(variableName, newValue);
    }
}
