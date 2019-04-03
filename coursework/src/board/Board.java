package board;

/**
 * @author Matthew
 *
 */


public class Board {

	private static ReadAndGenerateBoard br;
	private static String[][] board;
	private static String boardFile;


	public Board(String file)throws Exception {

		boardFile = file;
		br = new ReadAndGenerateBoard();

		Board.board = br.generateBoard(boardFile);

		//br.printArrayList();

		//printBoard();
	}


	/************************************************************************************/

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

	public String getBoardSize() {

		String Row = Integer.toString(board.length);
		String Col = Integer.toString(board[1].length);

		return Col + Row;
	}

	/************************************************************************************/

	public static String[][] getBoard(){
		return board;
	}

}
