import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Fabian
 */

// the arrayList implementation of the priority queue

public class PrQueueArray {
	private ArrayList<Passenger> array;
	private FileWriter output;
	StringBuilder stringBuilder;

	PrQueueArray() throws Exception {
		array = new ArrayList<Passenger>();
		try {
			output = new FileWriter("queue.out");
		} catch (Exception ex) {
			throw ex;
		}
		stringBuilder = new StringBuilder();
	}

	/**
	 * max heapifies from i to the end
	 * 
	 * @param i
	 */
	public void heapify(int i) {
		int max = i;
		int left = i * 2 + 1;
		int right = i * 2 + 2;

		if (left < array.size()
				&& (array.get(left).compareTo(array.get(i)) == 1)) {
			max = left;
		}

		if (right < array.size()
				&& (array.get(right).compareTo(array.get(max)) == 1)) {
			max = right;
		}

		if (max != i) {
			Collections.swap(array, max, i);
			heapify(max);
		}
	}

	/**
	 * inserts the passenger p with the points priority to the priority queue
	 * 
	 * @param p
	 * @param priority
	 */
	public void insert(Passenger p, int priority) {
		p.setPoints(priority);
		array.add(p);
		int child = array.size() - 1;
		int parent = (child - 1) / 2;
		Passenger aux = null;

		while (parent >= 0) {
			if (array.get(child).compareTo(array.get(parent)) == 1) {
				aux = array.get(parent);
				array.set(parent, array.get(child));
				array.set(child, aux);
				child = parent;
				parent = (child - 1) / 2;
			} else {
				break; /// may have to commment this line idk tho
			}
		}
	}

	/**
	 * prints the heap to the file
	 * 
	 * @param array
	 * @param index
	 */
	public void listArray(ArrayList<Passenger> array, int index) {
		if (index >= array.size()) {
			return;
		}
		stringBuilder.append(array.get(index).getId() + " ");
		listArray(array, index * 2 + 1);
		listArray(array, index * 2 + 2);

	}

	/**
	 * lists the priority queue in pre-order somehow this is the order for the
	 * list even though it means nothing to the reader because this is a
	 * priority queue
	 */
	public void list() {
		listArray(array, 0);
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
		array.set(0, array.get(array.size() - 1));
		array.remove(array.size() - 1);
		heapify(0);
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
		for(Passenger i : array) {
			if (id == null) {
				if (i.getId().contentEquals(nume)) {
					System.out.println("plmmmm");
					return i;
				}
			} else if (i.getId().contentEquals(id)) {
				System.out.println("aiaic" + i.getId() + nume);
				ArrayList<Single> membri = i.getMembers();
				for(Passenger j : membri) {
					if(j.getName().contentEquals(nume)) {
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
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) == p) {
					Collections.swap(array, i, array.size() - 1);
					array.remove(array.size() - 1);
					heapify(i);
					return;
				}
			}
		} else if (p.getId().contains("g") || p.getId().contains("f")) {
			if (p.getMembers() != null) {
				for (int i = 0; i < array.size(); i++) {
					if (array.get(i).getId().contentEquals(p.getId())) {
						Collections.swap(array, i, array.size() - 1);
						array.remove(array.size() - 1);
						heapify(i);
						return;
					}
				}
			} else {
				for (int i = 0; i < array.size(); i++) {
					if (array.get(i).deleteMember((Single) p)) {
						heapify(i);
						return;
					}
				}
			}
		}
	}

}
