package coursework;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReadBoardTest {

	private ReadBoard rb;
	private String fileTypeWrong = "TEST.txt";
	private String fileTypeCorrect = "TEST.BRD";

	@Before
	public void setUp() throws Exception {
		rb = new ReadBoard(1);
	}

	/**
	 *
	 * Test that an exception is thrown
	 *
	 * @throws Exception
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testWrongFileType() throws Exception {
		rb.checkFileType(fileTypeWrong);
	}

	/**
	 *
	 * Test that no exception is thrown
	 *
	 * @throws Exception
	 */
	@Test(expected=Test.None.class)
	public void testCorrectFileType() throws Exception {
		rb.checkFileType(fileTypeCorrect);
	}




	}

