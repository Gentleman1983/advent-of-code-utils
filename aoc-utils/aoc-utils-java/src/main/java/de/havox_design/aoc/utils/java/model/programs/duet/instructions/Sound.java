package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;

/**
 * The add {@link Instruction}.
 */
public class Sound implements Instruction {
    /**
     * The variable name.
     */
    private final String variableName;

    /**
     * The Constructor.
     *
     * @param variableName the variable name
     */
    public Sound(final String variableName) {
        this.variableName = variableName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final State state) {
        state.playSound(state.getValue(variableName));
    }
}
