import java.util.ArrayList;

/**
 * @author Fabian
 *
 */

public abstract class Passenger implements Comparable<Passenger> {

	/**
	 * calculates the points
	 */
	public abstract void calculatePoints();

	/**
	 * sets the points
	 * 
	 * @param points to set
	 */
	public abstract void setPoints(int points);

	/**
	 * @return the points
	 */
	public abstract int getPoints();

	/**
	 * @return the id
	 */
	public abstract String getId();

	/**
	 * @param s to be deleted
	 * @return true if the member was found and deleted
	 */
	public abstract boolean deleteMember(Single s);

	/**
	 * @return the members
	 */
	public abstract ArrayList<Single> getMembers();

	/**
	 * @return the name
	 */
	public abstract String getName();

	@Override
	public int compareTo(Passenger o) {
		// TODO Auto-generated method stub

		if (this.getPoints() - o.getPoints() > 0) {
			return 1;
		} else if (this.getPoints() - o.getPoints() < 0) {
			return -1;
		}
		return 0;
	}

}
