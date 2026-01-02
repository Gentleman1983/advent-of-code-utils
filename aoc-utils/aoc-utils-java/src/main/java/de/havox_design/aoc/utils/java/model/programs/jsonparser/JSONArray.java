package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * The JSON array class.
 */
@SuppressWarnings("javaarchitecture:S7027")
public final class JSONArray extends ArrayList<JSONEntity> implements JSONEntity {
    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getIntegersWithoutRed() {
        return stream()
                .map(JSONEntity::getIntegersWithoutRed)
                .flatMap(List::stream)
                .toList();
    }
}
