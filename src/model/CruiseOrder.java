package model;
/**
 * Class CruiseOrder ~ represent a single CruiseOrder
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class CruiseOrder {
	// ------------------------------- Class Members ------------------------------
	private String cruiseID;
	private String cruiseShipID;
	private String roomNumber;
	private String personID;

	// -------------------------------- Constructors ------------------------------
	public CruiseOrder(String cruiseID, String cruiseShipID, String roomNumber) {
		this.cruiseID = cruiseID;
		this.cruiseShipID = cruiseShipID;
		this.roomNumber = roomNumber;
	}

	public CruiseOrder(String cruiseID, String cruiseShipID, String roomNumber, String personID) {
		this.cruiseID = cruiseID;
		this.cruiseShipID = cruiseShipID;
		this.roomNumber = roomNumber;
		this.personID = personID;
	}

	// ----------------------------- Getters & Setters ----------------------------
	public String getCruiseID() {
		return cruiseID;
	}

	public String getCruiseShipID() {
		return cruiseShipID;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public String getPersonID() {
		return personID;
	}

	public void setCruiseID(String cruiseID) {
		this.cruiseID = cruiseID;
	}

	public void setCruiseShipID(String cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	// ----------------------------- HashCode & Equals ----------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cruiseID == null) ? 0 : cruiseID.hashCode());
		result = prime * result + ((cruiseShipID == null) ? 0 : cruiseShipID.hashCode());
		result = prime * result + ((roomNumber == null) ? 0 : roomNumber.hashCode());
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
		CruiseOrder other = (CruiseOrder) obj;
		if (cruiseID == null) {
			if (other.cruiseID != null)
				return false;
		} else if (!cruiseID.equals(other.cruiseID))
			return false;
		if (cruiseShipID == null) {
			if (other.cruiseShipID != null)
				return false;
		} else if (!cruiseShipID.equals(other.cruiseShipID))
			return false;
		if (roomNumber == null) {
			if (other.roomNumber != null)
				return false;
		} else if (!roomNumber.equals(other.roomNumber))
			return false;
		return true;
	}

	// ---------------------------------- toString --------------------------------
	@Override
	public String toString() {
		return "CruiseOrder [cruiseID=" + cruiseID + ", cruiseShipID=" + cruiseShipID + ", roomNumber=" + roomNumber
				+ ", personID=" + personID + "]";
	}
}
