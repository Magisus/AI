package othello;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddChild() {
		Node root = new Node();
		Node child = new Node();
		root.addChild(23, child);
		assertEquals(1, root.getChildren().size());
		assertEquals(child, root.getChildren().get(23));
	}

}
