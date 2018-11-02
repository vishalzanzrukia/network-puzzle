package vishal.network.puzzle;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import vishal.network.puzzle.exception.NetworkPuzzleException;

/**
 * The test cases for class <b>{@link Network}</b>.
 */
public class NetworkTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testNetworkWithHappyScenario() {
		final Network network = new Network(8);
		network.connect(1, 2);
		network.connect(1, 6);
		network.connect(2, 4);
		network.connect(2, 6);
		network.connect(5, 8);
		network.connect(4, 8);
		Assert.assertTrue(network.query(1, 4));
		Assert.assertTrue(network.query(1, 5));
		Assert.assertFalse(network.query(1, 3));
	}

	@Test
	public void testNetworkWithInvalidConstructorAgument() {
		exception.expect(NetworkPuzzleException.class);
		exception.expectMessage("The total nodes can not be negative or zero");
		new Network(0);

		exception.expect(NetworkPuzzleException.class);
		exception.expectMessage("The total nodes can not be negative or zero");
		new Network(-1);
	}

	@Test
	public void testNetworkWithInvalidTotalNodesWhileConnect() {
		final Network network = new Network(5);

		// test invalid source with negative or zero
		exception.expect(NetworkPuzzleException.class);
		exception.expectMessage("Source nodes in the set can not be negative or zero.");

		network.connect(-1, 8);

		// test invalid destination with negative or zero
		exception.expect(NetworkPuzzleException.class);
		exception.expectMessage("Source nodes in the set can not be negative or zero.");

		network.connect(2, 0);

		// test invalid source with higher than total number of nodes
		exception.expect(NetworkPuzzleException.class);
		exception.expectMessage(CoreMatchers.containsString("Source or Destination node in the set can not be greater than"));

		network.connect(6, 2);

		// test invalid destination with higher than total number of nodes
		exception.expect(NetworkPuzzleException.class);
		exception.expectMessage(CoreMatchers.containsString("Source or Destination node in the set can not be greater than"));

		network.connect(2, 6);
	}

	@Test
	public void testNetworkWithInvalidTotalNodesWhileQuery() {
		final Network network = new Network(5);

		// test invalid source with negative or zero
		exception.expect(NetworkPuzzleException.class);
		exception.expectMessage("Source nodes in the set can not be negative or zero.");

		network.query(-1, 8);

		// test invalid destination with negative or zero
		exception.expect(NetworkPuzzleException.class);
		exception.expectMessage("Source nodes in the set can not be negative or zero.");

		network.query(2, 0);

		// test invalid source with higher than total number of nodes
		exception.expect(NetworkPuzzleException.class);
		exception.expectMessage(CoreMatchers.containsString("Source or Destination node in the set can not be greater than"));

		network.query(6, 2);

		// test invalid destination with higher than total number of nodes
		exception.expect(NetworkPuzzleException.class);
		exception.expectMessage(CoreMatchers.containsString("Source or Destination node in the set can not be greater than"));

		network.query(2, 6);
	}
}
