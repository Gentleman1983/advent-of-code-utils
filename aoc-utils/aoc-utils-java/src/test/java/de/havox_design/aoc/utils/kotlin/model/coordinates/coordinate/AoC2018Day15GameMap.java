package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public record AoC2018Day15GameMap(AoC2018Day15Field[][] field, List<AoC2018Day15Unit> units) {
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        EqualsBuilder builder = new EqualsBuilder();

        builder.append(this.field, ((AoC2018Day15GameMap) other).field);
        builder.append(this.units, ((AoC2018Day15GameMap) other).units);

        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(this.field);
        builder.append(this.units);

        return builder.toHashCode();
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);

        builder.append("field", this.field);
        builder.append("units", this.units);

        return builder.build();
    }
}
