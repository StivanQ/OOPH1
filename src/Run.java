
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Fabian
 *
 */

public class Run {
	private File input;
	private int numberOfPassengers;
	private Scanner scanner;

	private String id;
	private String name;
	private int age;
	private char ticket;
	private boolean priorityEmbark;
	private boolean disabled;
	private String line;
	private String[] things;

	/**
	 * reads the number of singles to be read
	 * 
	 * @param path of the input file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	Run(String path) throws FileNotFoundException, IOException {
		input = new File(path);
		try {
			scanner = new Scanner(input);
		} catch (FileNotFoundException exception) {
			System.out.println("no input file");
			throw exception;
		}
		numberOfPassengers = scanner.nextInt();
		scanner.nextLine();
	}



	/**
	 * @return the single from the line read
	 */
	public Single readSingles() {
		Single single = null;
		if (scanner.hasNextLine()) {
			line = scanner.nextLine();
			things = line.split(" ");
			id = things[0];
			name = things[1];
			age = Integer.parseInt(things[2]);
			ticket = (things[3].toCharArray())[0];
			if(things[4].equals("true")) {
				priorityEmbark = true;
			} else {
				priorityEmbark = false;
			}

			if(things[5].equals("true")) {
				disabled = true;
			} else {
				disabled = false;
			}
			single = new Single(id, name, age, ticket, priorityEmbark,
					disabled);
		}

		return single;
	}

	/**
	 * adds the single to the family/ group on spot
	 * 
	 * @return the array that contains all the passengers in chronological order
	 */
	public ArrayList<Passenger> firstArray() {

		String id = null;
		Single single = null;
		Family family = null;
		Group group = null;

		ArrayList<Passenger> array = new ArrayList<Passenger>();
		for (int i = 0; i < numberOfPassengers; i++) {
			single = readSingles();
			if (single != null) {
				if ((single.getId()).contains("s")) {
					array.add(single);
				} else if ((single.getId()).contains("f")) {
					id = single.getId();
					Passenger p = null;
					for (Passenger j : array) {
						if (id.contentEquals(j.getId())) {
							p = j;
							break;
						}
					}
					if (p == null) {
						family = new Family(id);
						family.addMember(single);
						array.add(family);
					} else {
						((Family) p).addMember(single);
					}
				} else if ((single.getId()).contains("g")) {
					id = single.getId();
					Passenger p = null;
					for (Passenger j : array) {
						if (id.contentEquals(j.getId())) {
							p = j;
							break;
						}
					}
					if (p == null) {
						group = new Group(id);
						group.addMember(single);
						array.add(group);
					} else {
						((Group) p).addMember(single);
					}
				}
			}
		}
		return array;
	}

	/**
	 * parses the instructions
	 * 
	 * @param priorityQueue used for insert/ embark/ list/ delete
	 * @param array         of passengers to be introduces into the priority
	 *                      queue
	 */
	public void readInstructions(PrQueue priorityQueue,
			ArrayList<Passenger> array) {
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.contains("insert")) {
				things = line.split(" ");
				Passenger p = null;
				for (Passenger i : array) {
					if (things[1].contentEquals(i.getId())) {
						p = i;
						break;
					}
				}
				if (p != null) {
					priorityQueue.insert(p, p.getPoints());
					if (array.remove(p) == false) {
						System.out.println("you should not see this");
					}
				}
			} else if (line.contains("embark")) {
				priorityQueue.embark();
			} else if (line.contains("list")) {
				priorityQueue.list();
			} else if (line.contains("delete")) {
				things = line.split(" ");
				if (things.length == 2) {
					priorityQueue.delete(
							priorityQueue.findPassenger(null, things[1]));
				} else if (things.length == 3) {
					priorityQueue.delete(
							priorityQueue.findPassenger(things[1], things[2]));
				}
			}
		}
		scanner.close();
	}

}
