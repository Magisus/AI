package othello;

import static othello.State.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class MctsPlayerTest {

	private MctsPlayer player;
	
	private State state;
	
	@Before
	public void setUp() throws Exception {
		player = new MctsPlayer(500);
		state = new State();
	}

	@Test
	public void testToString() {
		assertEquals("Monte Carlo 500", player.toString());
		assertEquals("Monte Carlo 1000", new MctsPlayer(1000).toString());		
	}

	@Test
	public void testFinishPlayout() {
		char[][] board = {
				"OOOOOOOO".toCharArray(),
				"XXOO.OXX".toCharArray(),
				"OOOOOOOO".toCharArray(),
				"OOOXXOOO".toCharArray(),
				"OXOXXOOX".toCharArray(),
				".OOOXOXO".toCharArray(),
				"XXXXXXOO".toCharArray(),
				"XXXX.XOO".toCharArray(),
		};
		state = new State(board, 'X');
		double wins = 0;
		for (int i = 0; i < 10000; i++) {
			List<Integer> moves = new ArrayList<Integer>();
			wins += player.finishPlayout(state.copy(), moves);
			// Due to a forced pass, some playouts are longer than others
			assertTrue(moves.size() >= 3 && moves.size() <= 4);
			// The first move should be legal from state
			assertTrue(state.legalMoves().contains(moves.get(0)));
		}
		System.out.println(wins);
		assertTrue(wins > 7000);
		assertTrue(wins < 8000);
	}

	@Test
	public void testPlayout() {
		char[][] board = {
				"OOOOOXXX".toCharArray(),
				"OOOOOXXX".toCharArray(),
				"OOOOXXXX".toCharArray(),
				"XOOOXXXX".toCharArray(),
				"OOOOXXXX".toCharArray(),
				"OOOOXXXX".toCharArray(),
				"OOOOXXXX".toCharArray(),
				".XOOXXO.".toCharArray(),
		};
		state = new State(board, 'X');
		player.setRoot(new Node());
		for (int i = 0; i < 100; i++) {
			player.performPlayout(state.copy(), player.getRoot());
		}
		assertEquals("<root>\t(100 playouts)\n\t56: 1.000\t(99 playouts)\n\t56: \t63: 0.000\t(98 playouts)\n\t63: 0.000\t(1 playouts)\n", player.getRoot().toString());
	}

	@Test
	public void testMove1() {
		char[][] board = {
				"OOOOOXXX".toCharArray(),
				"OOOOOXXX".toCharArray(),
				"OOOOXXXX".toCharArray(),
				"XOOOXXXX".toCharArray(),
				"OOOOXXXX".toCharArray(),
				"OOOOXXXX".toCharArray(),
				"OOOOXXXX".toCharArray(),
				".XOOXXO.".toCharArray(),
		};
		state = new State(board, 'X');
		assertEquals(56, player.move(state));
	}

	@Test
	public void testMove2() {
		// NOTE: This one takes several seconds to run
		char[][] board = {
				".OXX....".toCharArray(),
				"........".toCharArray(),
				"........".toCharArray(),
				"...XO...".toCharArray(),
				"...OX...".toCharArray(),
				"........".toCharArray(),
				"........".toCharArray(),
				".OXXX...".toCharArray(),
		};
		state = new State(board, 'O');
		int[] counts = new int[WIDTH * WIDTH];
		for (int i = 0; i < 100; i++) {
			counts[player.move(state)]++;
		}
		assertTrue(counts[61] > 50);
	}

}
