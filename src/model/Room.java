package model;

public class Room {

    private String cruiseShipID;
    private String roomNumber;
    private int bedsAmount;
    private String roomType;
    private int price;

    public Room(String cruiseShipID, String roomNumber, int bedsAmount, String roomType, int price) {
        this.cruiseShipID = cruiseShipID;
        this.roomNumber = roomNumber;
        this.bedsAmount = bedsAmount;
        this.roomType = roomType;
        this.price = price;
    }

    public String getCruiseShipID() {
        return cruiseShipID;
    }

    public String getRoomNumber() {
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
}
