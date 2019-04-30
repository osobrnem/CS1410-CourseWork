package coursework.model;

import static org.junit.Assert.*;

import org.junit.Before;

/**
 * @author Muhammad Khan
 * @version 28/04/2019
 *
 */

import org.junit.Test;

public class FlagTest {

	Flag f1;
	int x = 1;
	int iD = x;

	@Before
	public void setup(){
		f1 = new Flag(x);
	}

	@Test
	public void testGetID(){

		assertEquals(iD,f1.getID());

	}
}
