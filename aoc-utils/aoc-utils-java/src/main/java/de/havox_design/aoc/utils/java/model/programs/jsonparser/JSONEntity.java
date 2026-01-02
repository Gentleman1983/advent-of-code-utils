package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import java.util.List;

/**
 * The JSON entity interface.
 */
@SuppressWarnings("javaarchitecture:S7027")
public sealed interface JSONEntity permits JSONObject, JSONString, JSONArray, JSONNumber {
    /**
     * Returns integers without red.
     *
     * @return the integer list
     */
    List<Integer> getIntegersWithoutRed();
}
