import java.util.ArrayList;

/**
 * @author Fabian
 *
 */

public class Single extends Passenger {
	private String name;
	private int age;
	private char ticket;
	private String id;
	private boolean priorityEmbark;
	private boolean disabled;
	private int points = 0;

	/**
	 * returns the single that has these parameters
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @param ticket
	 * @param priorityEmbark
	 * @param disabled
	 */
	public Single(String id, String name, int age, char ticket,
			boolean priorityEmbark, boolean disabled) {
		this.name = name;
		this.age = age;
		this.ticket = ticket;
		this.id = id;
		this.priorityEmbark = priorityEmbark;
		this.disabled = disabled;
		this.calculatePoints();
	}

	/**
	 * does nothing
	 */
	public ArrayList<Single> getMembers() {
		return null;
	}

	/**
	 * does nothing
	 */
	public boolean deleteMember(Single s) {
		return false;
	}

	/**
	 * calculates the points
	 */
	public void calculatePoints() {
		points = 0;
		if (disabled == true) {
			points += 100;
		}
		if (priorityEmbark == true) {
			points += 30;
		}
		switch (ticket) {
			case 'b':
				points += 35;
				break;
			case 'p':
				points += 20;
				break;
		default:
		}
		if (age < 2) {
			points += 20;
		} else if (age < 5) {
			points += 10;
		} else if (age < 10) {
			points += 5;
		} else if (age < 60) {
			points += 0;
		} else {
			points += 15;
		}
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
}
