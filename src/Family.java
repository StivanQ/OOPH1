
import java.util.ArrayList;

/**
 * @author Fabian
 *
 */
public class Family extends Passenger {
	private String id;
	private int basePoints = 10;
	private int points = basePoints;

	ArrayList<Single> members = new ArrayList<Single>();

	/**
	 * @param id
	 */
	public Family(String id) {
		this.id = id;
	}

	/**
	 * @return the members
	 */
	public ArrayList<Single> getMembers() {
		return members;
	}

	/**
	 * @param members to be set
	 */
	public void setMembers(ArrayList<Single> mermbers) {
		this.members = mermbers;
	}


	/**
	 * adds the members
	 * 
	 * @param single to be added
	 */
	public void addMember(Single single) {
		members.add(single);
		points += single.getPoints();
	}


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
	 * does nothing
	 */
	public void calculatePoints() {
	}

	/**
	 * is should not be called
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
	 * @param points the points to set
	 * @return the points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
}
