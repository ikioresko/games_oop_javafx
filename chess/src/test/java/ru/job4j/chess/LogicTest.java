package ru.job4j.chess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LogicTest {

    @Test
    public void move()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.move(Cell.C1, Cell.G5);
    }

    @Test
    public void whenCheckPosition() {
        Logic logic = new Logic();
        BishopBlack bb = new BishopBlack(Cell.C1);
        logic.add(bb);
        assertThat(bb.position(), is(Cell.C1));
    }

    @Test
    public void whenCopy() {
        Logic logic = new Logic();
        BishopBlack bb = new BishopBlack(Cell.C1);
        logic.add(bb);
        assertThat(bb.copy(Cell.A2).position(), is(Cell.A2));
    }

    @Test
    public void whenWayIsBack() throws ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bb = new BishopBlack(Cell.C1);
        logic.add(bb);
        Cell[] actualWay = bb.way(Cell.H6);
        assertThat(actualWay[0], is(Cell.D2));
        assertThat(actualWay[1], is(Cell.E3));
        assertThat(actualWay[2], is(Cell.F4));
        assertThat(actualWay[3], is(Cell.G5));
        assertThat(actualWay[4], is(Cell.H6));

    }

    @Test
    public void whenWayInAnotherSide() throws ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bb = new BishopBlack(Cell.C1);
        logic.add(bb);
        Cell[] actualWay = bb.way(Cell.A3);
        assertThat(actualWay[0], is(Cell.B2));
        assertThat(actualWay[1], is(Cell.A3));
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void whenWayException() throws ImpossibleMoveException {
        expectedEx.expect(ImpossibleMoveException.class);
        expectedEx.expectMessage("Could not move by diagonal from C1 to C3");
        Logic logic = new Logic();
        BishopBlack bb = new BishopBlack(Cell.C1);
        logic.add(bb);
        Cell[] actualWay = bb.way(Cell.C3);
    }

    @Test
    public void whenIsDiagonal() {
        Logic logic = new Logic();
        BishopBlack bb = new BishopBlack(Cell.C1);
        logic.add(bb);
        boolean actual = bb.isDiagonal(Cell.C1, Cell.C5);
        assertThat(actual, is(false));
    }
}