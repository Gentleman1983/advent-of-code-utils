package de.havox_design.aoc.utils.java.search.breadth_first_search;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class AoC2015Day22RPGState {

    final boolean hardMode;

    final AoC2015Day22RPGPlayer player;

    final AoC2015Day22RPGBoss boss;

    final AtomicInteger best;

    private final int[] spellDuration;

    public AoC2015Day22RPGState(AoC2015Day22RPGPlayer player, AoC2015Day22RPGBoss boss, int[] spellDuration, boolean hardMode, AtomicInteger best) {
        this.player = player;
        this.boss = boss;
        this.spellDuration = spellDuration;
        this.hardMode = hardMode;
        this.best = best;
    }

    public AoC2015Day22RPGState apply(final AoC2015Day22RPGSpell spell) {
        int playerHitPoints = player.hitPoints();
        int playerMana = player.mana() - spell.getCosts();
        int bossHitPoints = boss.hitPoints();
        int bossDamage = boss.damage();

        if (hardMode) {
            playerHitPoints--;
            if (playerHitPoints <= 0) {
                return new AoC2015Day22RPGState
                        (
                                new AoC2015Day22RPGPlayer
                                        (
                                                playerHitPoints,
                                                playerMana,
                                                player.manaSpent() + spell.getCosts()
                                        ),
                                new AoC2015Day22RPGBoss
                                        (
                                                bossHitPoints,
                                                bossDamage
                                        ),
                                spellDuration,
                                hardMode,
                                best
                        );
            }
        }

        final int[] newSpellDuration = Arrays.copyOf(spellDuration, spellDuration.length);

        // players turn
        if (newSpellDuration[AoC2015Day22RPGSpell.POISON.ordinal()] > 0) {
            bossHitPoints -= 3;
        }

        if (newSpellDuration[AoC2015Day22RPGSpell.RECHARGE.ordinal()] > 0) {
            playerMana += 101;
        }

        for (int i = 0; i < newSpellDuration.length; i++) {
            newSpellDuration[i] = Math.max(0, newSpellDuration[i] - 1);
        }
        newSpellDuration[spell.ordinal()] = spell.duration();

        if (spell == AoC2015Day22RPGSpell.MAGIC_MISSILE) {
            bossHitPoints -= 4;
        }

        if (spell == AoC2015Day22RPGSpell.DRAIN) {
            bossHitPoints -= 2;
            playerHitPoints += 2;
        }

        if (bossHitPoints <= 0) { // win
            return new AoC2015Day22RPGState
                    (
                            new AoC2015Day22RPGPlayer
                                    (
                                            playerHitPoints,
                                            playerMana,
                                            player.manaSpent() + spell.getCosts()
                                    ),
                            new AoC2015Day22RPGBoss
                                    (
                                            bossHitPoints,
                                            bossDamage
                                    ),
                            newSpellDuration,
                            hardMode,
                            best
                    );
        }

        // boss turn
        if (newSpellDuration[AoC2015Day22RPGSpell.POISON.ordinal()] > 0) {
            bossHitPoints -= 3;
        }

        if (newSpellDuration[AoC2015Day22RPGSpell.RECHARGE.ordinal()] > 0) {
            playerMana += 101;
        }

        for (int i = 0; i < newSpellDuration.length; i++) {
            newSpellDuration[i] = Math.max(0, newSpellDuration[i] - 1);
        }

        if (bossHitPoints <= 0) { // win
            return new AoC2015Day22RPGState
                    (
                            new AoC2015Day22RPGPlayer
                                    (
                                            playerHitPoints,
                                            playerMana,
                                            player.manaSpent() + spell.getCosts()
                                    ),
                            new AoC2015Day22RPGBoss
                                    (
                                            bossHitPoints,
                                            bossDamage
                                    ),
                            newSpellDuration,
                            hardMode,
                            best
                    );
        }

        playerHitPoints -= Math.max(1, bossDamage - (newSpellDuration[AoC2015Day22RPGSpell.SHIELD.ordinal()] > 0 ? 7 : 0));

        return new AoC2015Day22RPGState
                (
                        new AoC2015Day22RPGPlayer
                                (
                                        playerHitPoints,
                                        playerMana,
                                        player.manaSpent() + spell.getCosts()
                                ),
                        new AoC2015Day22RPGBoss
                                (
                                        bossHitPoints,
                                        bossDamage
                                ),
                        newSpellDuration,
                        hardMode,
                        best
                );
    }

    public int[] getSpellDuration() {
        return spellDuration;
    }
}
