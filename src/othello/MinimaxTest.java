package othello;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MinimaxTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void depthTest() {
		State state = new State(new char[][] {
				"........".toCharArray(),
				"........".toCharArray(),
				"..X.....".toCharArray(),
				".XOO....".toCharArray(),
				"..O.....".toCharArray(),
				"..O.....".toCharArray(),
				"........".toCharArray(),
				"..O.....".toCharArray() },
				'X');
		MinimaxPlayer playerX = new MinimaxPlayer(1, 'X');
		assertEquals(50, playerX.move(state));
		playerX = new MinimaxPlayer(3, 'X');
		assertEquals(28, playerX.move(state));
	}

}
