package model;

import java.util.Date;
/**
 * Class CruiseSailing ~ represent a single CruiseSailing
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class CruiseSailing {
	// ------------------------------- Class Members ------------------------------
	private String cruiseID;
	private CruiseShip cruiseShipID;
	private Date leavingTime;
	private Date returnTime;

	// -------------------------------- Constructors ------------------------------
	public CruiseSailing(String cruiseID, CruiseShip cruiseShipID, Date leavingTime, Date returnTime) {
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

	public CruiseShip getCruiseShipID() {
		return cruiseShipID;
	}

	public Date getLeavingTime() {
		return leavingTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setCruiseID(String cruiseID) {
		this.cruiseID = cruiseID;
	}

	public void setCruiseShipID(CruiseShip cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}

	public void setLeavingTime(Date leavingTime) {
		this.leavingTime = leavingTime;
	}

	public void setReturnTime(Date returnTime) {
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
		return "CruiseSailing [cruiseID=" + cruiseID + ", cruiseShipID=" + cruiseShipID + ", leavingTime=" + leavingTime
				+ ", returnTime=" + returnTime + "]";
	}
}
