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
}
