/**
 *
 */
package coursework.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Akeel Saleem, Uzair Akhter, Muhammad Khan
 *
 */
public class RobotTest {

	/**
	 * @throws java.lang.Exception
	 */

	Robot r1 = new Robot('A', 0, 0);
	boolean isAlive;
	boolean isDead;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		isDead = false;
		isAlive = true;
	}

	@Test
	public void testSetDead() {
		r1.setDead();
		assertEquals(isDead, r1.getIsAlive());

	}

	@Test
	public void testSetAlive(){
		r1.setAlive();
		assertEquals(isAlive, r1.getIsAlive());


	}
	@Test
	public void testGetStartingRow(){

	}

	@Test
	public void testGetStartingCol(){

	}


}
