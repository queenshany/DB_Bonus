package model;
/**
 * Class CruiseOrder ~ represent a single CruiseOrder
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class CruiseOrder {
	// ------------------------------- Class Members ------------------------------
	private CruiseSailing cruiseID;
	private CruiseShip cruiseShipID;
	private Room roomNumber;
	private Person personID;

	// -------------------------------- Constructors ------------------------------
	public CruiseOrder(CruiseSailing cruiseID, CruiseShip cruiseShipID, Room roomNumber) {
		this.cruiseID = cruiseID;
		this.cruiseShipID = cruiseShipID;
		this.roomNumber = roomNumber;
	}

	public CruiseOrder(CruiseSailing cruiseID, CruiseShip cruiseShipID, Room roomNumber, Person personID) {
		this.cruiseID = cruiseID;
		this.cruiseShipID = cruiseShipID;
		this.roomNumber = roomNumber;
		this.personID = personID;
	}

	// ----------------------------- Getters & Setters ----------------------------
	public CruiseSailing getCruiseID() {
		return cruiseID;
	}

	public CruiseShip getCruiseShipID() {
		return cruiseShipID;
	}

	public Room getRoomNumber() {
		return roomNumber;
	}

	public Person getPersonID() {
		return personID;
	}

	public void setCruiseID(CruiseSailing cruiseID) {
		this.cruiseID = cruiseID;
	}

	public void setCruiseShipID(CruiseShip cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}

	public void setRoomNumber(Room roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setPersonID(Person personID) {
		this.personID = personID;
	}

	// ------------------------------- More Methods -------------------------------
	//TODO

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
