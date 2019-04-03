package board;

/**
 * @author Matthew
 *
 */

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAndGenerateBoard {

	private static String[][] board;
	private static ArrayList<String> boardArray;
	private static int row;
	private static int col;

	public ReadAndGenerateBoard() throws Exception {
	}

	/************************************************************************************/

	public String[][] generateBoard(String boardFile) throws Exception {

		if(!checkFileType(boardFile).equals("BRD")){
			throw new IllegalArgumentException("File type is wrong");
		}

		generateArraylist(boardFile);
		board = arrayToBoardArray(boardArray);
		return board;
	}


	/************************************************************************************/

	public static String checkFileType(String fileName) {
		String fileType = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileType = fileName.substring(i + 1);
		}

		return fileType;
	}


	/************************************************************************************/

	public void generateArraylist(String boardFile) throws Exception {
		ArrayList<String> Array = new ArrayList<>();

		try (Scanner s = new Scanner(new FileReader(boardFile))) {
			while (s.hasNext()) {
				Array.add(s.nextLine());

			}
			Array.remove("format 1");
		} catch (Exception e) {
		}

		checkColAndRowNumbers(Array);

		boardArray = Array;
	}

	/************************************************************************************/

	public void printArrayList() throws Exception {

		for (int i = 0; i < boardArray.size(); i++) {
			for (int str = 0; str < boardArray.get(i).length(); str++) {
				System.out.print(boardArray.get(i).charAt(str));
			}
			System.out.println("");
		}

	}

	/************************************************************************************/

	public boolean checkColAndRowNumbers(ArrayList<String> board) {
		for (int i = 0; i < board.size(); i++) {
			if (board.get(0).length() != board.get(i).length()) {
				System.out.println("NUMBER OF SPACES IN EACH ROW ARE NOT EQUAL!");
				return false;
			}
		}

		return true;

	}

	/************************************************************************************/

	public String[][] arrayToBoardArray(ArrayList<String> boardArray) throws Exception {

		row = getNumberOfRows();
		col = getNumberOfColumns();

		board = new String[row][col];

		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				if (boardArray.get(i).charAt(str) == '.') {
					board[i][str] = "|  Empty  |";
				} else if (boardArray.get(i).charAt(str) == '1') {
					board[i][str] = "|  Flag1  |";
				} else if (boardArray.get(i).charAt(str) == '2') {
					board[i][str] = "|  Flag2  |";
				} else if (boardArray.get(i).charAt(str) == '3') {
					board[i][str] = "|  Flag3  |";
				} else if (boardArray.get(i).charAt(str) == '4') {
					board[i][str] = "|  Flag4  |";
				} else if (boardArray.get(i).charAt(str) == 'A') {
					board[i][str] = "| Player1 |";
				} else if (boardArray.get(i).charAt(str) == 'B') {
					board[i][str] = "| Player2 |";
				} else if (boardArray.get(i).charAt(str) == 'C') {
					board[i][str] = "| Player3 |";
				} else if (boardArray.get(i).charAt(str) == 'D') {
					board[i][str] = "| Player4 |";
				} else {
					board[i][str] = "|Not empty|";
					System.out.println("BOARD HAS INVALID CHARACTER");
				}
			}
		}

		return board;
	}

	/************************************************************************************/

	public int getNumberOfRows() {
		return boardArray.size();
	}

	/************************************************************************************/

	public int getNumberOfColumns() {
		return boardArray.get(1).length();
	}

}
