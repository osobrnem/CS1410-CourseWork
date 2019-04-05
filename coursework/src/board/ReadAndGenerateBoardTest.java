package board;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReadAndGenerateBoardTest {

	private ReadAndGenerateBoard gb;
	private String fileTypeWrong = "TEST.txt";
	private String fileTypeCorrect = "TEST.BRD";


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		gb = new ReadAndGenerateBoard();
	}

	/**
	 *
	 * Test that an exception is thrown
	 *
	 * @throws Exception
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testWrongFileType() throws Exception {
		gb.checkFileType(fileTypeWrong);
	}

	/**
	 *
	 * Test that no exception is thrown
	 *
	 * @throws Exception
	 */
	@Test(expected=Test.None.class)
	public void testCorrectFileType() throws Exception {
		gb.checkFileType(fileTypeCorrect);
	}




	}

