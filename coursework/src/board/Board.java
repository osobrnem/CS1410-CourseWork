package board;

/**
 * @author Matthew
 *
 */

public class Board {

	private static ReadAndGenerateBoard br;
	private static String[][] board;
	private static String boardFile;

	/**
	 * Gets a board
	 *
	 * <p>
	 * Generates a new ReadAndGenerateBoard class and sends it the board file.
	 * The board entity array is then returned and set as the board
	 * </p>
	 *
	 * @param file
	 *            Board file from input
	 * @throws Exception
	 */
	public Board(String file) throws Exception {

		boardFile = file;
		br = new ReadAndGenerateBoard();

		Board.board = br.generateBoard(boardFile);

		// br.printArrayList();

		// printBoard();
	}

	/************************************************************************************/

	/**
	 * Prints the board to console
	 */

	public static void printBoard() {
		int row = board.length;
		int col = board[1].length;

		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				System.out.print(board[i][str] + "");
			}
			System.out.println("");
		}
	}

	/************************************************************************************/

	/**
	 * Turns the dual string array into a single string so it can be printed
	 * more easily
	 *
	 * @return String version of the array
	 */

	public static String arrayToString() {
		int row = board.length;
		int col = board[1].length;
		String s = "";

		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				s = s + board[i][str] + " ";
			}
			s = s + "\n";
		}

		return s;
	}

	/************************************************************************************/

	/**
	 *
	 * Return the board array
	 *
	 * @return board array
	 */

	public static String[][] getBoard() {
		return board;
	}

}
