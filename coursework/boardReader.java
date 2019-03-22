package coursework;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class boardReader {

	public static void main(String[] args) throws Exception {
	}

	public ArrayList<String> getArraylist(String boardFile) throws Exception {
		ArrayList<String> board = new ArrayList<>();
		
		
		
		try (Scanner s = new Scanner(new FileReader(boardFile))) {
			while (s.hasNext()) {
				board.add(s.next());
			}
		}
	
		return board;
	}

	public void printArrayList(String boardFile) throws Exception {
		
		ArrayList<String> board = getArraylist(boardFile);
		
		for (int i = 0; i < board.size(); i++) {
			for (int str = 0; str < board.get(i).length(); str++) {
				System.out.print(board.get(i).charAt(str));
			}
			System.out.println("");
		}
	}

}
