package de.havox_design.aoc.utils.java.model.computer.aoc2019;

/**
 * Simple data class for opcodes.
 *
 * @param name the name
 * @param opcode the opcode
 * @param numberParameters the number of parameters
 */
public record OpCodeData(String name, int opcode, int numberParameters) {
}
