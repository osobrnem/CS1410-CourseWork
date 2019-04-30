/**
 *
 */
package coursework.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Akeel Saleem, Uzair Akhter, Muhammad Khan
 *
 */
public class RobotTest {

	/**
	 * @throws java.lang.Exception
	 */

	Robot r1;
	boolean isAlive;
	boolean isAalive;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		isAlive = false;
		isAalive = true;
	}

	@Test
	public void testSetDead() {
		assertEquals(isAlive, r1.setDead());

	}

	@Test
	public void testSetAlive(){
		assertEquals(isAalive, r1.setAlive());


	}
	@Test
	public void testGetStartingRow(){

	}

	@Test
	public void testGetStartingCol(){

	}


}
