package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }
        int size = Math.abs(position.getX() - dest.getX());
        Cell[] steps = new Cell[size];
        int deltaX = 1;
        int deltaY = 1;
        if (position.getX() > dest.getX()) {
            deltaX = -1;
        }
        if (position.getY()> dest.getY()) {
            deltaY = -1;
        }
        for (int index = 1; index <= size; index++) {
            steps[index-1] = Cell.findBy( position.getX() + deltaX * index, position.getY() + deltaY * index);
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        return (Math.abs(source.getX() - source.getY()) == Math.abs(dest.getX() - dest.getY())) ||
                ((source.getX() + source.getY()) == (dest.getX() + dest.getY()));
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
