package model;

import java.sql.Timestamp;
/**
 * Class CruiseSailing ~ represent a single CruiseSailing
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class CruiseSailing {
	// ------------------------------- Class Members ------------------------------
	private String cruiseID;
	private String cruiseShipID;
	private Timestamp leavingTime;
	private Timestamp returnTime;

	// -------------------------------- Constructors ------------------------------
	public CruiseSailing(String cruiseID, String cruiseShipID, Timestamp leavingTime, Timestamp returnTime) {
		this.cruiseID = cruiseID;
		this.cruiseShipID = cruiseShipID;
		this.leavingTime = leavingTime;
		this.returnTime = returnTime;
	}

	public CruiseSailing(String cruiseID) {
		this.cruiseID = cruiseID;
	}

	// ----------------------------- Getters & Setters ----------------------------
	public String getCruiseID() {
		return cruiseID;
	}

	public String getCruiseShipID() {
		return cruiseShipID;
	}

	public Timestamp getLeavingTime() {
		return leavingTime;
	}

	public Timestamp getReturnTime() {
		return returnTime;
	}

	public void setCruiseID(String cruiseID) {
		this.cruiseID = cruiseID;
	}

	public void setCruiseShipID(String cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}

	public void setLeavingTime(Timestamp leavingTime) {
		this.leavingTime = leavingTime;
	}

	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}

	// ------------------------------- More Methods -------------------------------
	//TODO

	// ----------------------------- HashCode & Equals ----------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cruiseID == null) ? 0 : cruiseID.hashCode());
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
		CruiseSailing other = (CruiseSailing) obj;
		if (cruiseID == null) {
			if (other.cruiseID != null)
				return false;
		} else if (!cruiseID.equals(other.cruiseID))
			return false;
		return true;
	}

	// ---------------------------------- toString --------------------------------
	@Override
	public String toString() {
		return "Cruise ID: " + cruiseID + " | Ship ID: " + cruiseShipID + " | Leaving: " + leavingTime
				+ " | Return: " + returnTime;
	}
}
