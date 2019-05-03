package coursework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import coursework.model.Robot;

/**
 *
 * Test for the Board class
 *
 * @author Lewis Miller
 *
 */
public class BoardTest {

	private ReadFile rf;

	@Before
	public void setUp() throws Exception {
		rf = new ReadFile();
	}

	@Test
	public void testCheckPlayerEmpty() throws Exception {
		rf.read("src/Boards/only-starting.brd", 4);
		Board board = new Board();
		assertTrue(board.checkPlayerEmpty(0, 0));
		assertFalse(board.checkPlayerEmpty(1, 1));
	}

	@Test
	public void testCheckAdjacentSpaceForward() throws Exception {
		rf.read("src/Boards/forwardTest.brd", 4);
		Board board = new Board();
		assertEquals(Board.checkAdjacentSpaceForward(2, 2, "North"), board.getPlayerRobots().get(2));
		assertEquals(Board.checkAdjacentSpaceForward(2, 2, "East"), board.getPlayerRobots().get(0));
		assertEquals(Board.checkAdjacentSpaceForward(2, 2, "South"), null);
		assertEquals(Board.checkAdjacentSpaceForward(2, 3, "West"), board.getPlayerRobots().get(1));
	}

	@Test
	public void testCheckAdjacentSpaceBackward() throws Exception {
		rf.read("src/Boards/forwardTest.brd", 4);
		Board board = new Board();
		assertEquals(Board.checkAdjacentSpaceBackward(2, 2, "South"), board.getPlayerRobots().get(2));
		assertEquals(Board.checkAdjacentSpaceBackward(2, 2, "West"), board.getPlayerRobots().get(0));
		assertEquals(Board.checkAdjacentSpaceBackward(2, 2, "North"), null);
		assertEquals(Board.checkAdjacentSpaceBackward(2, 3, "East"), board.getPlayerRobots().get(1));
	}

	@Test
	public void testCheckOutsideBoardForward() throws Exception {
		rf.read("src/Boards/edgeTest.brd", 4);
		new Board();
		assertTrue(Board.checkOutsideBoardForward(0, 0, "North"));
		assertTrue(Board.checkOutsideBoardForward(0, 0, "West"));
		assertTrue(Board.checkOutsideBoardForward(0, 5, "East"));
		assertTrue(Board.checkOutsideBoardForward(3, 0, "South"));
		assertFalse(Board.checkOutsideBoardForward(0, 0, "East"));
	}

	@Test
	public void testCheckOutsideBoardBackward() throws Exception {
		rf.read("src/Boards/edgeTest.brd", 4);
		Board board = new Board();
		assertTrue(board.checkOutsideBoardBackward(0, 0, "South"));
		assertTrue(board.checkOutsideBoardBackward(0, 0, "East"));
		assertTrue(board.checkOutsideBoardBackward(0, 5, "West"));
		assertTrue(board.checkOutsideBoardBackward(3, 0, "North"));
		assertFalse(board.checkOutsideBoardBackward(0, 0, "West"));
	}

	@Test
	public void testSameEntityLocation() throws Exception {
		rf.read("src/Boards/only-flags.brd", 4);
		Board boardF = new Board();
		Robot robot1 = new Robot('A', 1, 1);
		Robot robot2 = new Robot('B', 0, 0);
		assertEquals(boardF.sameEntityLocation(robot1), boardF.getBoardEntity()[1][1]);
		assertEquals(boardF.sameEntityLocation(robot2), null);

		rf.read("src/Boards/only-gears.brd", 4);
		Board boardG = new Board();
		assertEquals(boardG.sameEntityLocation(robot1), boardG.getBoardEntity()[1][1]);

		rf.read("src/Boards/only-pits.brd", 4);
		Board boardP = new Board();
		Robot robot3 = new Robot('C', 0, 1);
		assertEquals(boardP.sameEntityLocation(robot3), boardP.getBoardEntity()[0][1]);
	}

	@Test
	public void testGetNumberOfFlags() throws Exception {
		rf.read("src/Boards/only-flags.brd", 4);
		Board boardF = new Board();
		assertEquals(boardF.getNumberOfFlags(), 4);

		rf.read("src/Boards/only-gears.brd", 4);
		Board boardG = new Board();
		assertEquals(boardG.getNumberOfFlags(), 0);
	}

	@Test
	public void testRemoveRobotFromBoard() throws Exception {
		rf.read("src/Boards/only-starting.brd", 1);
		Board board = new Board();
		assertFalse(board.checkPlayerEmpty(2, 3));
		Board.removeRobotFromBoard(board.getPlayerRobots().get(0));
		assertTrue(board.checkPlayerEmpty(2, 3));
	}

}