package coursework;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * Test for the ReadFile class
 *
 * @author Matthew Osborne
 *
 */
public class ReadFileTest {

	private ReadFile rf;
	private String fileTypeWrong = "TEST.txt";
	private String fileTypeCorrect = "TEST.BRD";

	@Before
	public void setUp() throws Exception {
		rf = new ReadFile();
	}

	/**
	 *
	 * Test that an exception is thrown
	 *
	 * @throws Exception
	 *             Illegal Argument Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongFileType() throws Exception {
		rf.read(fileTypeWrong, 1);
	}

	/**
	 *
	 * Test that no exception is thrown
	 *
	 * @throws Exception
	 *             Illegal Argument Exception
	 *
	 */
	public void testCorrectFileType() throws Exception {
		rf.read(fileTypeCorrect, 1);
	}

}
