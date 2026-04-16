package de.havox_design.aoc.utils.kotlin.model.positions.directed_position;

import de.havox_design.aoc.utils.kotlin.model.coordinates.four_directions.FourDirections;
import de.havox_design.aoc.utils.kotlin.model.positions.position_2d.Position2d;

/**
 * A directed position on a 2-dimensional grid.
 */
public class DirectedPosition {
    /**
     * The position.
     */
    private final Position2d<Integer> position;
    /**
     * The facing direction.
     */
    private final FourDirections direction;

    /**
     * The constructor.
     *
     * @param position the current position
     * @param direction the direction facing to
     */
    public DirectedPosition(final Position2d<Integer> position, final FourDirections direction) {
        this.position = position;
        this.direction = direction;
    }

    /**
     * Moves one step forward.
     *
     * @return the new {@link DirectedPosition}
     */
    public DirectedPosition forward() {
        return switch (direction) {
            case FourDirections.UP -> new DirectedPosition(new Position2d<>(getX(), getY() - 1), direction);
            case FourDirections.DOWN -> new DirectedPosition(new Position2d<>(getX(), getY() + 1), direction);
            case FourDirections.LEFT -> new DirectedPosition(new Position2d<>(getX() - 1, getY()), direction);
            case FourDirections.RIGHT -> new DirectedPosition(new Position2d<>(getX() + 1, getY()), direction);
        };
    }

    /**
     * Returns the X position.
     *
     * @return the X position
     */
    public int getX() {
        return position.getX();
    }

    /**
     * Returns the Y position.
     *
     * @return the Y position
     */
    public int getY() {
        return position.getY();
    }

    /**
     * Turns on the current position to the left.
     *
     * @return the new {@link DirectedPosition}
     */
    public DirectedPosition left() {
        return new DirectedPosition(new Position2d<>(getX(), getY()), direction.turnLeft());
    }

    /**
     * Turns on the current position to the right.
     *
     * @return the new {@link DirectedPosition}
     */
    public DirectedPosition right() {
        return new DirectedPosition(new Position2d<>(getX(), getY()), direction.turnRight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[x=%s, y=%s, direction=%s]", getX(), getY(), direction);
    }
}
