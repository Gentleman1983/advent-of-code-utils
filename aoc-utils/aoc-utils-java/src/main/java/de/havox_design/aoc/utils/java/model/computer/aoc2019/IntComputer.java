package de.havox_design.aoc.utils.java.model.computer.aoc2019;

import static de.havox_design.aoc.utils.java.model.computer.aoc2019.OpCode.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * The IntComputer used widely during Advent of Code 2019.
 */
@SuppressWarnings("javaarchitecture:S7027")
public class IntComputer implements Runnable {
    /**
     * The executor.
     */
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(IntComputer.class.getName());
    /**
     * All Opcode data elements.
     */
    private static final Set<OpCodeData> OPCODES = Set.of(
            new OpCodeData("ADD", 1, 3),
            new OpCodeData("MULTIPLY", 2, 3),
            new OpCodeData("IN", 3, 1),
            new OpCodeData("OUT", 4, 1),
            new OpCodeData("JUMP_TRUE", 5, 2),
            new OpCodeData("JUMP_FALSE", 6, 2),
            new OpCodeData("LESS_THAN", 7, 3),
            new OpCodeData("EQUALS", 8, 3),
            new OpCodeData("RELATIVE_BASE", 9, 1),
            new OpCodeData("HALT", 99, 0),
            new OpCodeData("NOP", -1, 0)
    );
    /**
     * Stop printing.
     */
    private static final boolean PRINT_HALT = false;

    /**
     * The input queue.
     */
    private final BlockingQueue<Long> in;
    /**
     * The computer memory.
     */
    private Map<Long, Long> memory;
    /**
     * The processing mode.
     */
    private final boolean modes;
    /**
     * The current pointer.
     */
    private long pointer = 0L;
    /**
     * The output queue.
     */
    private final BlockingQueue<Long> out;
    /**
     *the relative base.
     */
    private long relativeBase = 0L;

    /**
     * The constructor.
     *
     * @param program the program to process
     */
    public IntComputer(final List<Long> program) {
        this(false, program, null, null);
    }

    /**
     * The constructor using input and output queues.
     *
     * @param program the program to process
     * @param in the input queue
     * @param out the output queue
     */
    public IntComputer(final List<Long> program, final BlockingQueue<Long> in, final BlockingQueue<Long> out) {
        this(true, program, in, out);
    }

    /**
     * The constructor using input and output queues and modes.
     *
     * @param modes the processing mode
     * @param program the program to process
     * @param in the input queue
     * @param out the output queue
     */
    private IntComputer(final boolean modes, final List<Long> program, final BlockingQueue<Long> in, final BlockingQueue<Long> out) {
        this.modes = modes;
        this.in = in;
        this.out = out;
        loadProgram(program);
    }

    /**
     * Advances the pointer to the next instruction.
     *
     * @return the next instruction
     */
    public Long advancePointer() {
        return ++pointer;
    }

    /**
     * Checks the id of the current instruction.
     *
     * @return the current instruction
     */
    protected Long currentInstruction() {
        return getValueDirect(pointer);
    }

    /**
     * Executes one program step.
     *
     * @return is the program finished
     */
    public boolean executeOneStep() {
        final Optional<OpCode> opCode = getCurrentOpCode();

        opCode.orElse(NOP).accept(this);
        advancePointer();

        return opCode.orElse(HALT) != HALT;
    }

    /**
     * Loads a given program
     *
     * @param program the program
     */
    private void loadProgram(final List<Long> program) {
        memory = new HashMap<>();

        for (long i = 0L; i < program.size(); i++) {
            memory.put(i, program.get((int) i));
        }
    }

    /**
     * Reads the current {@link OpCode}.
     *
     * @return the {@link OpCode}
     */
    private Optional<OpCode> getCurrentOpCode() {
        long opCode = currentInstruction();

        if (modes) {
            opCode %= 100;
        }

        return Optional.ofNullable(OpCode.valueOf(opCode));
    }

    /**
     * Reads the data for a given {@link OpCode} name.
     *
     * @param opcodeName the name
     * @return the data
     */
    public static OpCodeData getDataFor(String opcodeName) {
        return OPCODES
                .stream()
                .filter(opcode -> opcode.name().equalsIgnoreCase(opcodeName))
                .findFirst()
                .orElseThrow();
    }

    /**
     * Gets the input queue.
     *
     * @return the input queue
     */
    protected BlockingQueue<Long> getIn() {
        return in;
    }

    /**
     * Gets the memory.
     *
     * @return the memory
     */
    public Map<Long, Long> getMemory() {
        return memory;
    }

    /**
     * Returns the mode.
     *
     * @param value the value
     * @param param the parameter
     * @return the mode
     */
    protected int getMode(final long value, final int param) {
        int position = 10;

        for (int i = 0; i < param; i++) {
            position *= 10;
        }

        return (int) ((value / position) % 10);
    }

    /**
     * Returns the next parameter.
     *
     * @param mode the mode
     * @return the next parameter
     */
    protected Long getNextParameter(final int mode) {
        if (mode == 1) {
            return getValueDirect(advancePointer());
        } else {
            return getValueIndirect(advancePointer(), mode == 2);
        }
    }

    /**
     * Returns the output queue.
     *
     * @return the output queue
     */
    protected BlockingQueue<Long> getOut() {
        return out;
    }

    /**
     * Returns the pointer.
     *
     * @return the pointer
     */
    protected long getPointer() {
        return pointer;
    }

    /**
     * Returns the print halt parameter.
     *
     * @return the parameter
     */
    public static boolean getPrintHalt() {
        return PRINT_HALT;
    }

    /**
     * Returns the relative base.
     *
     * @return the relative base
     */
    protected long getRelativeBase() {
        return relativeBase;
    }

    /**
     * Returns a value from a memory address.
     *
     * @param address the address
     * @return the value or 0 if no value is available at the address
     */
    private Long getValueDirect(final Long address) {
        return memory.getOrDefault(address, 0L);
    }

    /**
     * Returns the value indirectly.
     *
     * @param index the index
     * @param relative use relative address
     * @return the value
     */
    private Long getValueIndirect(final Long index, final boolean relative) {
        final long offSet = relative ? relativeBase : 0;

        return getValueDirect(getValueDirect(index) + offSet);
    }

    /**
     * Prints a processing step.
     *
     * @return the {@link OpCode}
     */
    public Optional<OpCode> printOneStep() {
        final Optional<OpCode> opCode = getCurrentOpCode();

        LOGGER.info(() -> opCode.orElse(NOP).toString(this));

        return opCode;
    }

    /**
     * Prints the address of the parameter.
     *
     * @param mode the mode
     * @param param the parameter
     * @return the address
     */
    protected String printParameter(final int mode, final int param) {
        final Long address = getValueDirect(pointer + param);

        if (mode == 0) {
            return String.format("m[%s]", address);
        } else {
            return Long.toString(address);
        }
    }

    /**
     * Prints the complete program run.
     */
    public void printProgram() {
        LOGGER.info("=======BEGIN PROGRAM=======");
        final long origPointer = pointer;

        setPointer(0);

        while (memory.containsKey(pointer)) {
            final Optional<OpCode> opCode = printOneStep();
            pointer += opCode.orElse(NOP).getNumberOfParameters() + 1;
        }

        LOGGER.info("========END PROGRAM========");

        this.pointer = origPointer;
    }

    /**
     * Resets the {@link IntComputer}.
     */
    public void reset() {
        this.pointer = 0L;
        this.relativeBase = 0L;
    }

    /**
     * Start synchronous processing.
     */
    @Override
    public void run() {
        while (true) {
            if (!executeOneStep()) {
                return;
            }
        }
    }

    /**
     * Starts asynchronous processing.
     *
     * @return the execution
     */
    @SuppressWarnings("squid:S1452")
    public Future<?> runAsync() {
        return EXECUTOR.submit(this);
    }

    /**
     * Runs the whole computer.
     *
     * @param program the program
     * @param in the input queue
     * @param out the output queue
     * @param async process asynchronous?
     * @return the execution
     */
    @SuppressWarnings("squid:S1452")
    public static Future<?> runComputer(final List<Long> program, final BlockingQueue<Long> in, final BlockingQueue<Long> out, final boolean async) {
        final IntComputer computer = new IntComputer(program, in, out);
        final Future<?> future;

        if (async) {
            future = computer.runAsync();
        } else {
            computer.run();
            future = CompletableFuture.completedFuture(null);
        }

        return future;
    }

    /**
     * Sets the next parameter.
     *
     * @param val the value
     * @param mode the mode
     * @return the old value
     */
    protected Long setNextParameter(final Long val, final int mode) {
        return setValueIndirect(advancePointer(), val, mode == 2);
    }

    /**
     * Sets the pointer position.
     *
     * @param value the position
     */
    protected void setPointer(long value) {
        pointer = value;
    }

    /**
     * Sets the pointer position.
     *
     * @param pointer the position
     */
    private void setPointer(final int pointer) {
        this.pointer = pointer;
    }

    /**
     * Sets the relative base.
     *
     * @param value the base
     */
    protected void setRelativeBase(long value) {
        relativeBase = value;
    }

    /**
     * Sets the value directly.
     *
     * @param address the address
     * @param val the value
     * @return the old value
     */
    private Long setValueDirect(final Long address, final Long val) {
        Objects.requireNonNull(val);

        return memory.put(address, val);
    }

    /**
     * Sets the value indirectly.
     *
     * @param index the index
     * @param val the value
     * @param relative the relative flag
     * @return the old value
     */
    private Long setValueIndirect(final Long index, final Long val, final boolean relative) {
        final long offSet = relative ? relativeBase : 0;

        return setValueDirect(getValueDirect(index) + offSet, val);
    }

    /**
     * Shuts down the executor.
     */
    public static void shutdownAll() {
        EXECUTOR.shutdown();
    }
}
