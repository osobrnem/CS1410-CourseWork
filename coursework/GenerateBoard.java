package coursework;

import java.util.ArrayList;

public class GenerateBoard {

	private static boardReader br;
	private static String[][] board;
	private static ArrayList<String> boardArray;
	private static String boardFile;
	private static int row;
	private static int col;

	public static void main(String[] args) throws Exception {
		boardFile = "D:\\Desktop\\New folder (2)\\boards\\BIG.BRD";
		br = new boardReader();
		getBoardArray();
		
		

		row = getNumberOfRows();
		col = getNumberOfColumns(boardArray.size() - 1);

		System.out.println("Rows = " + row);
		System.out.println("Col = " + col);
		
		board = new String[row][col];

		fileToBoard();
		printBoard();
	}

	private static void getBoardArray() throws Exception {
		boardArray = br.getArraylist(boardFile);
	}

	private static void fileToBoard() {
		for (int i = 2; i < row; i++) {
			for (int str = 0; str < getNumberOfColumns(i); str++) {
				if (boardArray.get(i).charAt(str) == '.') {
					board[i][str] = "|  Empty  |";
				} 
				else if (boardArray.get(i).charAt(str) == '1') {
					board[i][str] = "|  Flag 1 |";
				} 
				else if (boardArray.get(i).charAt(str) == '2') {
					board[i][str] = "|  Flag 2 |";
				} 
				else if (boardArray.get(i).charAt(str) == '3') {
					board[i][str] = "|  Flag 3 |";
				} 
				else if (boardArray.get(i).charAt(str) == '4') {
					board[i][str] = "|  Flag 4 |";
				} 
				else if (boardArray.get(i).charAt(str) == 'A') {
					board[i][str] = "| Player1 |";
				} 
				else if (boardArray.get(i).charAt(str) == 'B') {
					board[i][str] = "| Player2 |";
				} 
				else if (boardArray.get(i).charAt(str) == 'C') {
					board[i][str] = "| Player3 |";
				} 
				else if (boardArray.get(i).charAt(str) == 'D') {
					board[i][str] = "| Player4 |";
				} 
				else {
					board[i][str] = "|Not empty|";
				}
			}
		}
	}

	public static void printBoard() {
		for (int i = 2; i < row; i++) {
			for (int str = 0; str < getNumberOfColumns(i); str++) {
				System.out.print(board[i][str] + "");
			}
			System.out.println("");
		}
	}

	public String getBoardSize() {

		String Row = Integer.toString(boardArray.size());
		String Col = Integer.toString(boardArray.get(boardArray.size() - 1).length());

		return Col + Row;
	}

	private static int getNumberOfRows() {
		return boardArray.size();
	}

	private static int getNumberOfColumns(int i) {
		return boardArray.get(i).length();
	}

}
