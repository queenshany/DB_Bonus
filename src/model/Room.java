package model;
/**
 * Class Room ~ represent a single Room
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class Room {
	// ------------------------------- Class Members ------------------------------
	private String cruiseShipID;
	private int roomNumber;
	private int bedsAmount;
	private String roomType;
	private int price;

	// -------------------------------- Constructors ------------------------------
	public Room(String cruiseShipID, int roomNumber, int bedsAmount, String roomType, int price) {
		this.cruiseShipID = cruiseShipID;
		this.roomNumber = roomNumber;
		this.bedsAmount = bedsAmount;
		this.roomType = roomType;
		this.price = price;
	}

	public Room(String cruiseShipID, int roomNumber) {
		this.cruiseShipID = cruiseShipID;
		this.roomNumber = roomNumber;
	}

	public Room(String cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}
	
	// ----------------------------- Getters & Setters ----------------------------
	public String getCruiseShipID() {
		return cruiseShipID;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public int getBedsAmount() {
		return bedsAmount;
	}

	public String getRoomType() {
		return roomType;
	}

	public int getPrice() {
		return price;
	}

	public void setCruiseShipID(String cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setBedsAmount(int bedsAmount) {
		this.bedsAmount = bedsAmount;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// ------------------------------- More Methods -------------------------------
	//TODO

	// ----------------------------- HashCode & Equals ----------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cruiseShipID == null) ? 0 : cruiseShipID.hashCode());
		result = prime * result + roomNumber;
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
		Room other = (Room) obj;
		if (cruiseShipID == null) {
			if (other.cruiseShipID != null)
				return false;
		} else if (!cruiseShipID.equals(other.cruiseShipID))
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}

	// ---------------------------------- toString --------------------------------
	@Override
	public String toString() {
		return "Room [cruiseShipID=" + cruiseShipID + ", roomNumber=" + roomNumber + ", bedsAmount=" + bedsAmount
				+ ", roomType=" + roomType + ", price=" + price + "]";
	}
}
