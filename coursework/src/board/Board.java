package board;

/**
 * @author Matthew
 *
 */

import java.util.ArrayList;

public class Board {

	private static ReadAndGenerateBoard br;
	private static String[][] board;
	private static ArrayList<String> boardFiles;
	private static String boardFile;
	

	public Board()throws Exception {
		
		boardFiles = new ArrayList<>();

		boardFiles.add("D:\\Desktop\\New folder (2)\\boards\\Big.BRD");
		boardFiles.add("D:\\Desktop\\New folder (2)\\boards\\empty-0x0.BRD");
		boardFiles.add("D:\\Desktop\\New folder (2)\\boards\\empty-1x1.BRD");
		boardFiles.add("D:\\Desktop\\New folder (2)\\boards\\empty-1x2.BRD");
		boardFiles.add("D:\\Desktop\\New folder (2)\\boards\\empty-2x1.BRD");
		boardFiles.add("D:\\Desktop\\New folder (2)\\boards\\empty-file.BRD");
		boardFiles.add("D:\\Desktop\\New folder (2)\\boards\\invalid-character.BRD");
		boardFiles.add("D:\\Desktop\\New folder (2)\\boards\\mismatched-rows-cols.BRD");
		boardFiles.add("D:\\Desktop\\New folder (2)\\boards\\only-flags.BRD");
		boardFiles.add("D:\\Desktop\\New folder (2)\\boards\\only-starting.BRD");
		
		
		boardFile = boardFiles.get(0);
		br = new ReadAndGenerateBoard();
		
		Board.board = br.generateBoard(boardFile);
		
		
		//br.printArrayList();
		
		printBoard();
	}


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

	public String getBoardSize() {

		String Row = Integer.toString(board.length);
		String Col = Integer.toString(board[1].length);

		return Col + Row;
	}

	
	public static String[][] getBoard(){
		return board;
	}

}
