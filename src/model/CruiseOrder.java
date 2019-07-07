package model;

public class CruiseOrder {

    private String cruiseID;
    private String cruiseShipID;
    private String roomNumber;
    private String personID;

    public CruiseOrder(String cruiseID, String cruiseShipID, String roomNumber, String personID) {
        this.cruiseID = cruiseID;
        this.cruiseShipID = cruiseShipID;
        this.roomNumber = roomNumber;
        this.personID = personID;
    }

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
}
