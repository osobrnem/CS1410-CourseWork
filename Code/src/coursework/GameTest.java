package coursework;

import static org.junit.Assert.*;

import java.util.ArrayDeque;

import org.junit.Before;
import org.junit.Test;

import coursework.model.Robot;
import coursework.Board;

/**
 * @author Matthew Osborne
 *
 */
public class GameTest {

	private Game g;
	private ArrayDeque<String>[] testMoves;

	private int numberOfPlayers = 1;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		g = new Game("src/boards/GameTestBoard.brd", numberOfPlayers);

		testMoves = new ArrayDeque[1];
		testMoves[0] = new ArrayDeque<String>();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlayerMovesException() {
		g.setPlayerMoves("B");
		g.setPlayerMoves("F");
		g.setPlayerMoves("B");
		g.setPlayerMoves("B");
		g.setPlayerMoves("F");

	}

	@Test
	public void testSetPlayerMoves() {
		testMoves[0].add("B");
		testMoves[0].add("F");
		testMoves[0].add("B");
		testMoves[0].add("W");

		g.setPlayerMoves("B");
		g.setPlayerMoves("F");
		g.setPlayerMoves("B");
		g.setPlayerMoves("W");

		ArrayDeque<String>[] moves = g.getAllPlayerMoves();

		assertEquals(testMoves[0].poll(), moves[0].poll());
		assertEquals(testMoves[0].poll(), moves[0].poll());
		assertEquals(testMoves[0].poll(), moves[0].poll());
		assertEquals(testMoves[0].poll(), moves[0].poll());

	}

	@Test
	public void testRemoveLastMove() {
		testMoves[0].add("B");
		testMoves[0].add("F");
		testMoves[0].add("B");

		g.setPlayerMoves("B");
		g.setPlayerMoves("F");
		g.setPlayerMoves("B");
		g.setPlayerMoves("W");

		g.removeLastMove();

		ArrayDeque<String>[] moves = g.getAllPlayerMoves();

		assertEquals(testMoves[0].poll(), moves[0].poll());
		assertEquals(testMoves[0].poll(), moves[0].poll());
		assertEquals(testMoves[0].poll(), moves[0].poll());

	}

	@Test
	public void testCheckRobot() {
		Robot r = g.getPlayerRobots().get(0);

		Board.removeRobotFromBoard(r);
		Board.setPlayerLocation(0, 1, r);
		g.checkRobot(r);
		assertEquals(1, r.getFlags().size());

		Board.removeRobotFromBoard(r);
		Board.setPlayerLocation(0, 2, r);
		g.checkRobot(r);
		assertEquals("East", r.getDirection());

		Board.removeRobotFromBoard(r);
		Board.setPlayerLocation(1, 0, r);
		g.checkRobot(r);
		assertEquals(1, r.getCol());
		assertEquals(1, r.getRow());

		Board.removeRobotFromBoard(r);
		Board.setPlayerLocation(1, 1, r);
		g.checkRobot(r);
		assertFalse(r.getIsAlive());
	}
}
