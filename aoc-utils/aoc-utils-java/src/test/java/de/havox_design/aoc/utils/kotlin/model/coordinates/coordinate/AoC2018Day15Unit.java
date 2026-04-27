package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AoC2018Day15Unit {
    private final int id;
    private final AoC2018Day15Team team;
    private int health = 200;
    private int attackPower = 3;
    private Coordinate location;

    public AoC2018Day15Unit(final int id, final AoC2018Day15Team team, final Coordinate location) {
        this.id = id;
        this.team = team;
        this.setLocation(location);
    }

    public AoC2018Day15Unit(final int id, final AoC2018Day15Team team, int health, int attackPower, final Coordinate location) {
        this(id, team, location);
        this.setHealth(health);
        this.setAttackPower(attackPower);
    }

    public int getId() {
        return id;
    }

    public AoC2018Day15Team getTeam() {
        return team;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        EqualsBuilder builder = new EqualsBuilder();

        builder.append(this.id, ((AoC2018Day15Unit) obj).id);

        return builder.build();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(id);

        return builder.build();
    }
}
