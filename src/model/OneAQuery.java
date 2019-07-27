package model;

import java.util.Objects;
/**
 * Class OneAQuery ~ represent the 1A query in the DB
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class OneAQuery {
	// ------------------------------- Class Members ------------------------------
	private int cruiseID;
	private int cruiseShipID;
	private String shipName;
	
	// -------------------------------- Constructors ------------------------------
	public OneAQuery(int cruiseID, int cruiseShipID, String shipName) {
		this.cruiseID = cruiseID;
		this.cruiseShipID = cruiseShipID;
		this.shipName = shipName;
	}

	// ----------------------------- Getters & Setters ----------------------------
	public int getCruiseID() {
		return cruiseID;
	}

	public void setCruiseID(int cruiseID) {
		this.cruiseID = cruiseID;
	}

	public int getCruiseShipID() {
		return cruiseShipID;
	}

	public void setCruiseShipID(int cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	// ----------------------------- HashCode & Equals ----------------------------
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OneAQuery oneAQuery = (OneAQuery) o;
		return cruiseID == oneAQuery.cruiseID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cruiseID);
	}
}
