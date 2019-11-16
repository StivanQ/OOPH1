import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Fabian
 *
 */

public class PrQueue {

	private HeapNode root;
	private int numberOfNodes;
	private FileWriter output;
	StringBuilder stringBuilder;

	/**
	 * @throws Exception about fileWriter not creating outputfile
	 */
	PrQueue() throws Exception {
		// TODO things
		numberOfNodes = 0;
		try {
			output = new FileWriter("queue.out");
		} catch (Exception ex) {
			throw ex;
		}
		stringBuilder = new StringBuilder();
	}

	/**
	 * max heapifies form that node down
	 * 
	 * @param node
	 */
	public void heapify(HeapNode node) {
		HeapNode max = node;
		HeapNode left = node.getLeft();
		HeapNode right = node.getRight();
		
		if ((left != null) && (left.compareTo(max) < 0)) {
			max = left;
		}
		
		if ((right != null) && (right.compareTo(max) < 0)) {
			max = right;
		}
		
		if (max != node) {
			Passenger auxP = max.getPassenger();
			max.setPassenger(node.getPassenger());
			node.setPassenger(auxP);
			heapify(max);
		}
	}

	/**
	 * builds max heap
	 */
	public void buildMaxHeap() {
		for (int i = numberOfNodes / 2 - 1; i >= 0; i--) {
			heapify(root.getNode(i));
		}
	}

	/**
	 * @param i       the i-th parents of the lsat node
	 * @param switchy true if the true last node false if the node to be
	 *                introduced
	 * @return the parent
	 */
	public HeapNode getLastNode(int i, boolean switchy) {
		HeapNode lastNode = root;
		String nodeCountBinary;
		if (switchy == true) {
			nodeCountBinary = Integer.toBinaryString(numberOfNodes);
		} else {
			nodeCountBinary = Integer.toBinaryString(numberOfNodes + 1);
		}
		int stringLength = nodeCountBinary.length() - 1;
		int counter = 1;
		while (counter <= (stringLength - i)) {
			if (nodeCountBinary.charAt(counter) == '1') {
				lastNode = lastNode.getRight();
			} else if (nodeCountBinary.charAt(counter) == '0') {
				lastNode = lastNode.getLeft();
			}
			counter++;
		}
		return lastNode;
	}

	/**
	 * prints the heap to the file
	 * 
	 * @param parent
	 */
	public void printHeap(HeapNode parent) {
		if (parent == null) {
			return;
		}
		stringBuilder.append(parent.getPassenger().getId() + " ");
		printHeap(parent.getLeft());
		printHeap(parent.getRight());
	}

	/**
	 * inserts the passenger p with the points priority to the priority queue
	 * 
	 * @param p
	 * @param priority
	 */
	public void insert(Passenger p, int priority) {
		p.setPoints(priority);
		if (root == null) {
			root = new HeapNode(p);
			numberOfNodes++;
			return;
		}

		HeapNode insertNode = getLastNode(1, false);
		String nodeCountBinary = Integer.toBinaryString(numberOfNodes + 1);
		int stringLength = nodeCountBinary.length() - 1;
		if (nodeCountBinary.charAt(stringLength) == '1') {
			insertNode.setRight(new HeapNode(p));
		} else {
			insertNode.setLeft(new HeapNode(p));
		}
		numberOfNodes++;
		buildMaxHeap();
	}

	/**
	 * lists the priority queue in pre-order somehow this is the order for the
	 * list even though it means nothing to the reader because this is a
	 * priority queue
	 */
	public void list() {
		printHeap(root);
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append('\n');
		try {
			output.write(stringBuilder.toString());
			output.flush();
			stringBuilder.delete(0, stringBuilder.length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * embark
	 */
	public void embark() {
		HeapNode lastLeaf = getLastNode(0, true);
		if (lastLeaf == root) {
			root = null;
			numberOfNodes = 0;
			return;
		}
		root.setPassenger(lastLeaf.getPassenger());

		HeapNode lastLeafParent = getLastNode(1, true);
		String nodeCountBinary = Integer.toBinaryString(numberOfNodes);
		int stringLength = nodeCountBinary.length() - 1;
		if (nodeCountBinary.charAt(stringLength) == '1') {
			lastLeafParent.setRight(null);
		} else {
			lastLeafParent.setLeft(null);

		}
		numberOfNodes--;
		heapify(root);
	}

	/**
	 * if the id is set to null returns the family or group with the id in name
	 * if the id is not null then returns the single with the name name from the
	 * family/ group with the id id
	 * 
	 * @param id
	 * @param nume
	 * @return the passenger with the id and name specified
	 */
	public Passenger findPassenger(String id, String nume) {
		for (int i = 0; i < numberOfNodes; i++) {
			if (id == null) {
				if (root.getNode(i).getPassenger().getId()
						.contentEquals(nume)) {
					return root.getNode(i).getPassenger();
				}
			} else if (root.getNode(i).getPassenger().getId()
					.contentEquals(id)) {
				ArrayList<Single> membri = root.getNode(i).getPassenger()
						.getMembers();
				for (Passenger j : membri) {
					if (j.getName().contentEquals(nume)) {
						return j;
					}
				}
			}
		}
		return null;
	}

	/**
	 * deletes the passenger p from the priority queue it can be a single/
	 * family/ group of a single from a family of group; black magic
	 * 
	 * @param p to be deleted
	 */
	public void delete(Passenger p) {
		if (p.getId().contains("s")) {
			for (int i = 0; i < numberOfNodes; i++) {
				if (root.getNode(i).getPassenger() == p) {
					if (p == root.getPassenger()) {
						root = null;
						return;
					}
					root.getNode(i).setPassenger(
							root.getNode(numberOfNodes - 1).getPassenger());
					if (numberOfNodes % 2 == 0) {
						root.getNode(numberOfNodes / 2).setLeft(null);
					} else {
						root.getNode(numberOfNodes / 2).setRight(null);
					}
					numberOfNodes--;
					heapify(root);
					return;
				}
			}
		} else if (p.getId().contains("g") || p.getId().contains("f")) {
			if (p.getMembers() != null) {
				for (int i = 0; i < numberOfNodes; i++) {
					if (root.getNode(i).getPassenger().getId()
							.contentEquals(p.getId())) {
						root.getNode(i).setPassenger(
								root.getNode(numberOfNodes - 1).getPassenger());
						if (numberOfNodes % 2 == 0) {
							root.getNode((numberOfNodes / 2) - 1).setLeft(null);
						} else {
							root.getNode((numberOfNodes / 2) - 1)
									.setRight(null);
						}
						numberOfNodes--;
						heapify(root.getNode(i));
						return;
					}
				}
			} else {
				for (int i = 0; i < numberOfNodes; i++) {
					if (root.getNode(i).getPassenger()
							.deleteMember((Single) p)) {
						if (root.getNode(i).getPassenger().getMembers()
								.size() == 0) {
							root.getNode(i).setPassenger(root
									.getNode(numberOfNodes - 1).getPassenger());
							if (numberOfNodes % 2 == 0) {
								root.getNode((numberOfNodes / 2) - 1)
										.setLeft(null);
							} else {
								root.getNode((numberOfNodes / 2) - 1)
										.setRight(null);
							}
							numberOfNodes--;
							heapify(root.getNode(i));
							return;
						}
						heapify(root.getNode(i));
						return;
					}
				}
			}
		}
	}

}
