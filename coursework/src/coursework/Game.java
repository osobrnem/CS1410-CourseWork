/**
 *
 */
package coursework;

import board.Board;

/**
 * @author Matthew
 *
 */

public class Game {

	private Board board;

	/**
	 *
	 * Creates a <code> board </code> from a file.
	 *
	 *
	 * @param args
	 * @throws Exception
	 *
	 */

	public Game(String file) throws Exception {

		board = new Board(file);
	}

	/************************************************************************************/



}
