package model;
/**
 * Class SixQuery ~ represent the 6th query in the DB
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class SixQuery {

	// ------------------------------- Class Members ------------------------------
	private int cruiseID;
	private int numOfSuits;

	// -------------------------------- Constructors ------------------------------
	public SixQuery(int cruiseID, int numOfSuits) {
		this.cruiseID = cruiseID;
		this.numOfSuits = numOfSuits;
	}

	public SixQuery(String cruiseID) {
		try {
			this.cruiseID = Integer.parseInt(cruiseID);
		} catch (NumberFormatException e) {
			System.out.println("Num format exception in SixQuery");
		}

	}

	// ----------------------------- Getters & Setters ----------------------------
	public int getCruiseID() {
		return cruiseID;
	}

	public void setCruiseID(int cruiseID) {
		this.cruiseID = cruiseID;
	}

	public int getNumOfSuits() {
		return numOfSuits;
	}

	public void setNumOfSuits(int numOfSuits) {
		this.numOfSuits = numOfSuits;
	}

	// ----------------------------- HashCode & Equals ----------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cruiseID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SixQuery other = (SixQuery) obj;
		if (cruiseID != other.cruiseID)
			return false;
		return true;
	}

	// ---------------------------------- toString --------------------------------
	@Override
	public String toString() {
		return "SixQuery [cruiseID=" + cruiseID + ", numOfSuits=" + numOfSuits + "]";
	}
}
