/**
 * @author Fabian
 *
 */
public class HeapNode implements Comparable<HeapNode> {
	private HeapNode left;
	private HeapNode right;
	private Passenger passenger;

	/**
	 * sets the passenger
	 * 
	 * @param passenger to be set for the new heap node
	 */
	public HeapNode(Passenger passenger) {
		this.passenger = passenger;
	}

	/**
	 * @return the left
	 */
	public HeapNode getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(HeapNode left) {
		this.left = left;
	}

	/**
	 * @return the right child
	 */
	public HeapNode getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(HeapNode right) {
		this.right = right;
	}


	/**
	 * @return the passenger
	 */
	public Passenger getPassenger() {
		return passenger;
	}

	/**
	 * @param passenger the passenger to set
	 */
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	@Override
	public int compareTo(HeapNode o) {
		// TODO Auto-generated method stub
		if (o.getPassenger().getPoints() < this.getPassenger().getPoints()) {
			return -1;
		}
		if (o.getPassenger().getPoints() > this.getPassenger().getPoints()) {
			return 1;
		}
		return 0;
	}

	/**
	 * returns the node corresponding to the index index = 0 returns the root
	 * index = 1 returns the left child of the root index = 2 returns the right
	 * child of the root etc
	 * 
	 * @param index
	 * @return the reference to the corresponding heap node
	 */
	public HeapNode getNode(int index) {
		HeapNode pointer = this;

		String binaryString = Integer.toBinaryString(index + 1);
		int stringLength = binaryString.length() - 1;

		int counter = 1;
		while (counter <= stringLength) {
			if (binaryString.charAt(counter) == '1') {
				if (pointer.getRight() != null) {
					pointer = pointer.getRight();
				} else {
					break;
				}
			} else {
				if (pointer.getLeft() != null) {
					pointer = pointer.getLeft();
				} else {
					break;
				}
			}
			counter++;
		}
		return pointer;
	}
}

