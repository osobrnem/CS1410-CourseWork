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

	/**
	 *
	 * Creates a <code> board </code> from a file.
	 *
	 *
	 * @param args
	 * @throws Exception
	 *
	 *
	 * @link #Board
	 */

	public Game(String file) throws Exception {


		if(!checkFileType(file).equalsIgnoreCase("BRD")){
			throw new IllegalArgumentException("File type is wrong");
		}

		new Board(file);
	}

	/************************************************************************************/

	/************************************************************************************/

	public static String checkFileType(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}

		return extension;
	}

}
