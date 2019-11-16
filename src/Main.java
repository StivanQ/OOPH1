

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Fabian
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Run run;
		ArrayList<Passenger> array = new ArrayList<Passenger>();

		try {
			run = new Run("queue.in");
		} catch (FileNotFoundException ex) {
			System.out.println("something is wrong");
			ex.printStackTrace();
			return;
		} catch (IOException ex) {
			System.out.println("something is wrong");
			ex.printStackTrace();
			return;
		}

		array = run.firstArray();

		// you can uncomment the following line and comment the one after and
		// the program will work just the same
		// PrQueueArray priorityQueue = null;
		PrQueue priorityQueue = null;
		try {
			priorityQueue = new PrQueue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}

		run.readInstructions(priorityQueue, array);
	}

}
