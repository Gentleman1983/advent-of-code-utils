package de.havox_design.aoc.utils.kotlin.model.coordinates.coordinate;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.coordinates.four_directions.FourDirectionsFlipped;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AoC2018Day15BeverageBandits implements AoCFunctionality {
    private final AoC2018Day15GameMap input;

    public AoC2018Day15BeverageBandits(String fileName) {
        AoC2018Day15MapParser parser = new AoC2018Day15MapParser();

        input = parser.parse(readData(fileName));
    }

    public static int processTask1(String fileName) {
        AoC2018Day15BeverageBandits instance = new AoC2018Day15BeverageBandits(fileName);

        return instance.processTask1();
    }

    public static int processTask2(String fileName) {
        AoC2018Day15BeverageBandits instance = new AoC2018Day15BeverageBandits(fileName);

        return instance.processTask2();
    }

    public int processTask1() {
        AoC2018Day15Outcome outcome = process(
                new HashMap<>(
                        input
                                .units()
                                .stream()
                                .collect(Collectors.toMap(AoC2018Day15Unit::getId, u -> u))
                )
        );

        return calculateScore(outcome);
    }

    public int processTask2() {
        AoC2018Day15Outcome outcome;
        int attackPower = 0;

        do {
            Map<Integer, AoC2018Day15Unit> units = updateElfishAttackPower(input.units(), ++attackPower);

            outcome = process(units);
        } while (countElfishDeaths(input.units(), outcome.units().values()) > 0);

        return calculateScore(outcome);
    }

    private AoC2018Day15Outcome process(Map<Integer, AoC2018Day15Unit> input) {
        int round = 0;

        Map<Integer, AoC2018Day15Unit> units = new HashMap<>(input);

        while (true) {
            Iterator<Integer> it = units
                    .values()
                    .stream()
                    .sorted(
                            Comparator
                                    .<AoC2018Day15Unit>comparingInt(v -> v.getLocation().getY())
                                    .thenComparingInt(v -> v.getLocation().getX())
                    )
                    .map(AoC2018Day15Unit::getId)
                    .iterator();

            while (it.hasNext()) {
                AoC2018Day15Unit u = units.get(it.next());

                if (u != null) {
                    if (isFinished(units)) {
                        return new AoC2018Day15Outcome(round, units);
                    }
                    propagate(units, u);
                }
            }

            round++;
        }
    }

    private boolean isFinished(Map<Integer, AoC2018Day15Unit> units) {
        return units
                .values()
                .stream()
                .map(AoC2018Day15Unit::getTeam)
                .distinct()
                .count() <= 1L;
    }

    private int calculateScore(AoC2018Day15Outcome outcome) {
        int round = outcome.round();
        int totalHealth = outcome
                .units()
                .values()
                .stream()
                .mapToInt(AoC2018Day15Unit::getHealth)
                .sum();

        return round * totalHealth;
    }

    private void propagate(Map<Integer, AoC2018Day15Unit> units, AoC2018Day15Unit unit) {
        if (!isAtEnemy(units, unit)) {
            unit = moveToClosestEnemy(units, unit);
        }

        attackBestEnemy(units, unit);
    }

    private boolean isAtEnemy(Map<Integer, AoC2018Day15Unit> units, AoC2018Day15Unit unit) {
        return units
                .values()
                .stream()
                .filter(u -> !Objects.equals(unit.getTeam(), u.getTeam()))
                .anyMatch(u -> distance(unit, u) <= 1);
    }

    private int distance(AoC2018Day15Unit unit, AoC2018Day15Unit other) {
        return Math.abs(
                unit.getLocation().getX() - other.getLocation().getX()
        ) + Math.abs(
                unit.getLocation().getY() - other.getLocation().getY()
        );
    }

    private AoC2018Day15Unit moveToClosestEnemy(Map<Integer, AoC2018Day15Unit> units, AoC2018Day15Unit unit) {
        Coordinate target = getTarget(units, unit);

        if (target == null) {
            return unit;
        }

        int[][] distanceMap = getDistanceMap(units, target);
        Set<Coordinate> options = getValidSurrounding(units, unit.getLocation())
                .stream()
                .filter(position -> distanceMap[position.getX()][position.getY()] >= 0)
                .collect(Collectors.toSet());

        if (options.isEmpty()) {
            return unit;
        }

        int min = options
                .stream()
                .mapToInt(position -> distanceMap[position.getX()][position.getY()])
                .min()
                .orElseThrow();

        units.remove(unit.getId());

        AoC2018Day15Unit newUnit = new AoC2018Day15Unit(
                unit.getId(),
                unit.getTeam(),
                unit.getHealth(),
                unit.getAttackPower(),
                options.stream()
                        .filter(position -> distanceMap[position.getX()][position.getY()] == min)
                        .min(
                                Comparator
                                        .comparingInt(Coordinate::getY)
                                        .thenComparingInt(Coordinate::getX)
                        )
                        .orElseThrow()
        );

        units.put(newUnit.getId(), newUnit);

        return newUnit;
    }

    private Coordinate getTarget(Map<Integer, AoC2018Day15Unit> units, AoC2018Day15Unit unit) {
        int[][] distanceMap = getDistanceMap(units, unit.getLocation());
        Set<Coordinate> targets = units
                .values()
                .stream()
                .filter(other -> !Objects.equals(unit.getTeam(), other.getTeam()))
                .map(AoC2018Day15Unit::getLocation)
                .flatMap(position -> getValidSurrounding(units, position).stream())
                .filter(position -> distanceMap[position.getX()][position.getY()] >= 0)
                .collect(Collectors.toSet());

        if (targets.isEmpty()) {
            return null;
        }

        int min = targets
                .stream()
                .mapToInt(position -> distanceMap[position.getX()][position.getY()])
                .min()
                .orElseThrow();

        return targets
                .stream()
                .filter(position -> distanceMap[position.getX()][position.getY()] == min)
                .min(
                        Comparator
                                .comparingInt(Coordinate::getY)
                                .thenComparingInt(Coordinate::getX)
                )
                .orElseThrow();
    }

    private int[][] getDistanceMap(Map<Integer, AoC2018Day15Unit> units, Coordinate... positions) {
        int[][] distanceMap = new int[input.field().length][input.field()[0].length];

        for (int[] distances : distanceMap) {
            Arrays.fill(distances, -1);
        }

        Set<Coordinate> toCheck = new HashSet<>();

        for (Coordinate position : positions) {
            toCheck.addAll(
                    getValidSurrounding(units, position)
                            .stream()
                            .filter(pos -> distanceMap[pos.getX()][pos.getY()] != 0)
                            .collect(Collectors.toSet())
            );
            distanceMap[position.getX()][position.getY()] = 0;
        }

        AtomicInteger distance = new AtomicInteger(1);

        while (!toCheck.isEmpty()) {
            toCheck
                    .forEach(pos -> distanceMap[pos.getX()][pos.getY()] = distance.get());
            toCheck = toCheck
                    .stream()
                    .flatMap(pos -> getValidSurrounding(units, pos).stream())
                    .filter(pos -> distanceMap[pos.getX()][pos.getY()] == -1 || distanceMap[pos.getX()][pos.getY()] > distance.get())
                    .collect(Collectors.toSet());

            distance.incrementAndGet();
        }

        return distanceMap;

    }

    private Set<Coordinate> getValidSurrounding(Map<Integer, AoC2018Day15Unit> units, Coordinate position) {
        return getSurrounding(position)
                .stream()
                .filter(
                        pos -> pos.getX() >= 0 &&
                                pos.getX() < input.field().length &&
                                pos.getY() >= 0 &&
                                pos.getY() < input.field()[pos.getX()].length
                )
                .filter(pos ->
                        units
                                .values()
                                .stream()
                                .noneMatch(u -> u.getLocation().equals(pos))
                )
                .filter(pos -> input.field()[pos.getX()][pos.getY()].equals(AoC2018Day15Field.EMPTY))
                .collect(Collectors.toSet());
    }

    private static Set<Coordinate> getSurrounding(Coordinate position) {
        return Arrays
                .stream(FourDirectionsFlipped.values())
                .map(direction -> new Coordinate(position.getX() + direction.dx(), position.getY() + direction.dy()))
                .collect(Collectors.toSet());
    }

    private void attackBestEnemy(Map<Integer, AoC2018Day15Unit> units, AoC2018Day15Unit unit) {
        Set<AoC2018Day15Unit> adjacentEnemies = units
                .values()
                .stream()
                .filter(other -> !Objects.equals(unit.getTeam(), other.getTeam()))
                .filter(other -> distance(unit, other) == 1)
                .collect(Collectors.toSet());

        if (adjacentEnemies.isEmpty()) {
            return;
        }

        int lowestHealth = adjacentEnemies
                .stream()
                .mapToInt(AoC2018Day15Unit::getHealth)
                .min()
                .orElseThrow();

        AoC2018Day15Unit toAttack = adjacentEnemies
                .stream()
                .filter(target -> target.getHealth() == lowestHealth)
                .min(
                        Comparator
                                .<AoC2018Day15Unit>comparingInt(v -> v.getLocation().getY())
                                .thenComparingInt(v -> v.getLocation().getX())
                )
                .orElseThrow();
        units.remove(toAttack.getId());

        toAttack = new AoC2018Day15Unit(
                toAttack.getId(),
                toAttack.getTeam(),
                toAttack.getHealth() - unit.getAttackPower(),
                toAttack.getAttackPower(),
                toAttack.getLocation()
        );

        if (toAttack.getHealth() > 0) {
            units.put(toAttack.getId(), toAttack);
        }
    }

    private Map<Integer, AoC2018Day15Unit> updateElfishAttackPower(Collection<AoC2018Day15Unit> units, int attackPower) {
        return new HashMap<>(
                units
                        .stream()
                        .map(unit ->
                                unit
                                        .getTeam()
                                        .equals(AoC2018Day15Team.ELF) ? new AoC2018Day15Unit(
                                        unit.getId(),
                                        unit.getTeam(),
                                        unit.getHealth(),
                                        attackPower,
                                        unit.getLocation()
                                ) : unit
                        )
                        .collect(Collectors.toMap(AoC2018Day15Unit::getId, unit -> unit))
        );
    }

    private long countElfishDeaths(Collection<AoC2018Day15Unit> before, Collection<AoC2018Day15Unit> after) {
        long numberOfElfesBefore = before
                .stream()
                .map(AoC2018Day15Unit::getTeam)
                .filter(unit -> unit.equals(AoC2018Day15Team.ELF))
                .count();
        long numberOfElfesAfter = after
                .stream()
                .map(AoC2018Day15Unit::getTeam)
                .filter(unit -> unit.equals(AoC2018Day15Team.ELF))
                .count();

        return numberOfElfesBefore - numberOfElfesAfter;
    }
}
