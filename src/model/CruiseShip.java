package model;

import java.util.Date;

public class CruiseShip {

    private String cruiseShipID;
    private String shipName;
    private Date manufacturingDate;
    private int maxCapacity;
    private int maxNumberOfPeople;

    public CruiseShip(String cruiseShipID, String shipName, Date manufacturingDate, int maxCapacity, int maxNumberOfPeople) {
        this.cruiseShipID = cruiseShipID;
        this.shipName = shipName;
        this.manufacturingDate = manufacturingDate;
        this.maxCapacity = maxCapacity;
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

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
}
