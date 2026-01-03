package de.havox_design.aoc.utils.java.search.breadth_first_search;

import java.util.Arrays;

enum AoC2015Day22RPGSpell {
    MAGIC_MISSILE(53),
    DRAIN(73),
    SHIELD(113),
    POISON(173),
    RECHARGE(229);

    private final int costs;

    AoC2015Day22RPGSpell(int costs) {
        this.costs = costs;
    }

    static AoC2015Day22RPGSpell[] getPossibleSpells(int budget, int[] durations) {
        return Arrays
                .stream(values())
                .filter(sp -> sp.costs <= budget && durations[sp.ordinal()] <= 1)
                .toArray(AoC2015Day22RPGSpell[]::new);
    }

    public int getCosts() {
        return costs;
    }

    public int duration() {
        return switch (this) {
            case MAGIC_MISSILE, DRAIN -> 0;
            case SHIELD, POISON -> 6;
            case RECHARGE -> 5;
        };
    }
}
