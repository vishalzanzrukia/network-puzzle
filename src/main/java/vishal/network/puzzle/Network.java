package vishal.network.puzzle;

import java.util.ArrayList;
import java.util.List;

import vishal.network.puzzle.exception.NetworkPuzzleException;

/**
 * The <b>Network</b> class.
 */
public class Network {

	/** class members */
	private final int totalNodes;
	private final boolean graph[][];

	/**
	 * The constructor which instantiates a new network graph
	 *
	 * @param totalNodes
	 *            the total nodes
	 * @throws NetworkPuzzleException
	 *             if total number of node is less or equal to zero
	 */
	public Network(int totalNodes) {
		if (totalNodes < 1) {
			throw new NetworkPuzzleException("The total nodes can not be negative or zero");
		}
		this.totalNodes = totalNodes;
		this.graph = new boolean[totalNodes][totalNodes];
		this.initGraph();
	}

	/**
	 * Initialize the graph
	 */
	private void initGraph() {
		// setting up default graph
		for (int i = 0; i < totalNodes; i++) {
			for (int j = 0; j < totalNodes; j++) {
				if (i == j) {
					// every node is connected with itself
					graph[i][j] = true;
				} else {
					// every node is not connected with others
					graph[i][j] = false;
				}
			}
		}
	}

	/**
	 * Connect.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 */
	public void connect(int source, int destination) {
		if (source < 1) {
			throw new NetworkPuzzleException("Source nodes in the set can not be negative or zero.");
		}
		if (destination < 1) {
			throw new NetworkPuzzleException("Destination nodes in the set can not be negative or zero.");
		}
		validateNode(source);
		validateNode(destination);
		graph[source - 1][destination - 1] = true;
		graph[destination - 1][source - 1] = true;

	}

	/**
	 * Validate the Node
	 *
	 * @param node
	 *            the node
	 */
	private void validateNode(int node) {
		if (node > totalNodes) {
			throw new NetworkPuzzleException("Source or Destination node in the set can not be greater than " + totalNodes);
		}
	}

	/**
	 * Query.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @return true, if successful
	 */
	public boolean query(int source, int destination) {
		if (source < 1) {
			throw new NetworkPuzzleException("Source nodes in the set can not be negative or zero.");
		}
		if (destination < 1) {
			throw new NetworkPuzzleException("Destination nodes in the set can not be negative or zero.");
		}
		validateNode(source);
		validateNode(destination);
		return isPathExist(source, destination);
	}

	/**
	 * Checks if is path exist.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @return true, if is path exist
	 */
	private boolean isPathExist(int source, int destination) {

		List<Integer> queue = new ArrayList<>();
		queue.add(source - 1);

		// iterating through queue until it's empty
		for (int index = 0; index < queue.size(); index++) {
			int nextNode = queue.get(index);

			// iterating through every nodes to check connectivity with all other nodes
			for (int i = 0; i < totalNodes; i++) {

				// try to check connectivity with every other nodes
				if (graph[nextNode][i]) {

					// found connected, so checking is that destination?
					if (i == destination - 1) {
						// destination found!
						return true;
					} else if (!queue.contains(i)) {
						// if it's not destination then add in queue to check it in next iterations
						queue.add(i);
					}
				}
			}
		}
		return false;
	}
}
