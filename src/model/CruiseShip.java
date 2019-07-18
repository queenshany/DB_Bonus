package model;

import java.sql.Date;
/**
 * Class CruiseShip ~ represent a single CruiseShip
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class CruiseShip {
	// ------------------------------- Class Members ------------------------------
	private String cruiseShipID;
	private String shipName;
	private Date manufacturingDate;
	private int maxCapacity;
	private int maxNumberOfPeople;

	// -------------------------------- Constructors ------------------------------
	public CruiseShip(String cruiseShipID, String shipName, Date manufacturingDate, int maxCapacity, int maxNumberOfPeople) {
		this.cruiseShipID = cruiseShipID;
		this.shipName = shipName;
		this.manufacturingDate = manufacturingDate;
		this.maxCapacity = maxCapacity;
		this.maxNumberOfPeople = maxNumberOfPeople;
	}

	public CruiseShip(String cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}
	
	// ----------------------------- Getters & Setters ----------------------------
	public String getCruiseShipID() {
		return cruiseShipID;
	}

	public String getShipName() {
		return shipName;
	}

	public Date getManufacturingDate() {
		return manufacturingDate;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public int getMaxNumberOfPeople() {
		return maxNumberOfPeople;
	}

	public void setCruiseShipID(String cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public void setMaxNumberOfPeople(int maxNumberOfPeople) {
		this.maxNumberOfPeople = maxNumberOfPeople;
	}

	// ------------------------------- More Methods -------------------------------
	//TODO

	// ----------------------------- HashCode & Equals ----------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cruiseShipID == null) ? 0 : cruiseShipID.hashCode());
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
		CruiseShip other = (CruiseShip) obj;
		if (cruiseShipID == null) {
			if (other.cruiseShipID != null)
				return false;
		} else if (!cruiseShipID.equals(other.cruiseShipID))
			return false;
		return true;
	}

	// ---------------------------------- toString --------------------------------
	@Override
	public String toString() {
		return "Ship ID: " + cruiseShipID + " | Name: " + shipName + " | Manufacturing Date: "
				+ manufacturingDate + " | Max Capacity: " + maxCapacity + " | Max Number of People: " + maxNumberOfPeople;
	}
}
