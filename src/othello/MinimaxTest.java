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
				"........".toCharArray(),
				".OOX....".toCharArray(),
				"..O.....".toCharArray(),
				"..O.....".toCharArray(),
				"..X.....".toCharArray(),
				"..O.....".toCharArray() },
				'X');
		MinimaxPlayer playerX = new MinimaxPlayer(1);
		assertEquals(18, playerX.move(state));
		playerX = new MinimaxPlayer(2);
		assertEquals(24, playerX.move(state));
	}

}
