package model;

import java.sql.Date;
/**
 * Class SailTo ~ represent a single SailTo
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class SailTo {
	// ------------------------------- Class Members ------------------------------
	private String countryName;
	private String portName;
	private String sailingID;
	private Date arrivalTime;
	private Date leavingTime;

	// -------------------------------- Constructors ------------------------------
	public SailTo(String countryName, String portName, String sailingID, Date arrivalTime, Date leavingTime) {
		this.countryName = countryName;
		this.portName = portName;
		this.sailingID = sailingID;
		this.arrivalTime = arrivalTime;
		this.leavingTime = leavingTime;
	}

	public SailTo(String countryName, String portName, String sailingID) {
		this.countryName = countryName;
		this.portName = portName;
		this.sailingID = sailingID;
	}
	
	public SailTo(String sailingID) {
		this.sailingID = sailingID;
	}

	// ----------------------------- Getters & Setters ----------------------------
	public String getCountryName() {
		return countryName;
	}

	public String getPortName() {
		return portName;
	}

	public String getSailingID() {
		return sailingID;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public Date getLeavingTime() {
		return leavingTime;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public void setSailingID(String sailingID) {
		this.sailingID = sailingID;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setLeavingTime(Date leavingTime) {
		this.leavingTime = leavingTime;
	}

	// ----------------------------- HashCode & Equals ----------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((portName == null) ? 0 : portName.hashCode());
		result = prime * result + ((sailingID == null) ? 0 : sailingID.hashCode());
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
		SailTo other = (SailTo) obj;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (portName == null) {
			if (other.portName != null)
				return false;
		} else if (!portName.equals(other.portName))
			return false;
		if (sailingID == null) {
			if (other.sailingID != null)
				return false;
		} else if (!sailingID.equals(other.sailingID))
			return false;
		return true;
	}

	// ---------------------------------- toString --------------------------------
	@Override
	public String toString() {
		return "Sail To: " + countryName + ", " + portName + " | Sailing ID: " + sailingID
				+ " | Arrival: " + arrivalTime + " | Leaving: " + leavingTime;
	}

}
