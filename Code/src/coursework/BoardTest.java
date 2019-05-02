package coursework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import coursework.model.Robot;

/**
 * @author Lewis Miller
 *
 */
public class BoardTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testCheckPlayerEmpty() throws Exception {
		Board board = new Board("src/Boards/only-starting.brd",4);
		assertTrue(board.checkPlayerEmpty(0,0));
		assertFalse(board.checkPlayerEmpty(1,1));
	}

	@Test
	public void testCheckAdjacentSpaceForward() throws Exception {
		Board board = new Board("src/only-starting-testForward.brd",4);
		assertEquals(Board.checkAdjacentSpaceForward(2, 2, "North"),board.getPlayerRobots().get(2));
		assertEquals(Board.checkAdjacentSpaceForward(2, 2, "East"),board.getPlayerRobots().get(0));
		assertEquals(Board.checkAdjacentSpaceForward(2, 2, "South"),null);
		assertEquals(Board.checkAdjacentSpaceForward(2, 3, "West"),board.getPlayerRobots().get(1));
	}

	@Test
	public void testCheckAdjacentSpaceBackward() throws Exception {
		Board board = new Board("src/Boards/only-starting-testForward.brd",4);
		assertEquals(Board.checkAdjacentSpaceBackward(2, 2, "South"),board.getPlayerRobots().get(2));
		assertEquals(Board.checkAdjacentSpaceBackward(2, 2, "West"),board.getPlayerRobots().get(0));
		assertEquals(Board.checkAdjacentSpaceBackward(2, 2, "North"),null);
		assertEquals(Board.checkAdjacentSpaceBackward(2, 3, "East"),board.getPlayerRobots().get(1));
	}

	@Test
	public void testCheckOutsideBoardForward() throws Exception {
		Board board = new Board("src/Boards/only-starting-edge.brd",4);
		assertTrue(Board.checkOutsideBoardForward(0, 0, "North"));
		assertTrue(Board.checkOutsideBoardForward(0, 0, "West"));
		assertTrue(Board.checkOutsideBoardForward(0, 5, "East"));
		assertTrue(Board.checkOutsideBoardForward(3, 0, "South"));
		assertFalse(Board.checkOutsideBoardForward(0, 0, "East"));
	}

	@Test
	public void testCheckOutsideBoardBackward() throws Exception {
		Board board = new Board("src/Boards/only-starting-edge.brd",4);
		assertTrue(board.checkOutsideBoardBackward(0, 0, "South"));
		assertTrue(board.checkOutsideBoardBackward(0, 0, "East"));
		assertTrue(board.checkOutsideBoardBackward(0, 5, "West"));
		assertTrue(board.checkOutsideBoardBackward(3, 0, "North"));
		assertFalse(board.checkOutsideBoardBackward(0, 0, "West"));
	}

	@Test
	public void testSameEntityLocation() throws Exception{
		Board boardF = new Board("src/Boards/only-flags.brd",4);
		Robot robot1 = new Robot('A',1,1);
		Robot robot2 = new Robot('B',0,0);
		assertEquals(boardF.sameEntityLocation(robot1),boardF.getBoardEntity()[1][1]);
		assertEquals(boardF.sameEntityLocation(robot2),null);
		Board boardG = new Board("src/Boards/only-gears.brd",4);
		assertEquals(boardG.sameEntityLocation(robot1),boardG.getBoardEntity()[1][1]);
		Board boardP = new Board("src/only-pits.brd",1);
		Robot robot3 = new Robot('C',0,1);
		assertEquals(boardP.sameEntityLocation(robot3),boardP.getBoardEntity()[0][1]);
	}

	@Test
	public void testGetNumberOfFlags() throws Exception{
		Board board = new Board("src/Boards/only-flags.brd",4);
		assertEquals(board.getNumberOfFlags(),4);
		Board boardG = new Board("src/Boards/only-gears.brd",4);
		assertEquals(boardG.getNumberOfFlags(),0);
	}

	@Test
	public void testRemoveRobotFromBoard() throws Exception{
		Board board = new Board("src/Boards/only-starting.brd",1);
		assertFalse(board.checkPlayerEmpty(2,3));
		board.removeRobotFromBoard(board.getPlayerRobots().get(0));
		assertTrue(board.checkPlayerEmpty(2,3));
	}

}