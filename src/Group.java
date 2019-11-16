

import java.util.ArrayList;

/**
 * @author Fabian
 *
 */

public class Group extends Passenger {
	private String id;
	private int basePoints = 5;
	private int points = basePoints;

	ArrayList<Single> members = new ArrayList<Single>();

	/**
	 * sets the id
	 * 
	 * @param id
	 */
	public Group(String id) {
		this.id = id;
	}

	/**
	 * @param persoana
	 */
	public void addMember(Single member) {
		members.add(member);
		points += member.getPoints();
	}

	// TODO

	/**
	 * @param s to be deleted
	 * @return true if the member was found and deleted
	 */
	public boolean deleteMember(Single s) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getName().contentEquals(s.getName())) {
				setPoints(getPoints() - members.get(i).getPoints());
				members.remove(i);
				return true;
			}
		}
		return false;
	}


	/**
	 * @return the members
	 */
	public ArrayList<Single> getMembers() {
		return members;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * does nothing
	 */
	public void calculatePoints() {
	}

	/**
	 * should not be used
	 * 
	 * @return null
	 */
	public String getName() {
		return null;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
}
