package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This class represents a JSON object.
 */
@SuppressWarnings("javaarchitecture:S7027")
public final class JSONObject extends LinkedHashMap<String, JSONEntity> implements JSONEntity {
    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The <italic>red</italic> {@link JSONString}.
     */
    private static final JSONString RED = new JSONString("red");

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getIntegersWithoutRed() {
        if (values().contains(RED)) {
            return List.of();
        }
        return values()
                .stream()
                .map(JSONEntity::getIntegersWithoutRed)
                .flatMap(List::stream)
                .toList();
    }
}
