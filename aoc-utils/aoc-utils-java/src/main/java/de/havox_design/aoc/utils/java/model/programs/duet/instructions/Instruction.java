package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;

/**
 * The instruction interface.
 */
public interface Instruction {
    /**
     * Executes the {@link  Instruction} with a given program {@link State}.
     *
     * @param state the {@link State}
     */
    void execute(State state);
}
