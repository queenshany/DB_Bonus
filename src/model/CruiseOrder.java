package model;

public class CruiseOrder {

    private CruiseSailing cruiseID;
    private CruiseShip cruiseShipID;
    private Room roomNumber;
    private Person personID;

    public CruiseOrder(CruiseSailing cruiseID, CruiseShip cruiseShipID, Room roomNumber, Person personID) {
        this.cruiseID = cruiseID;
        this.cruiseShipID = cruiseShipID;
        this.roomNumber = roomNumber;
        this.personID = personID;
    }

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
}
