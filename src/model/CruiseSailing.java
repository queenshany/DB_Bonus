package model;

import java.util.Date;

public class CruiseSailing {

    private String cruiseID;
    private String cruiseShipID;
    private Date leavingTime;
    private Date returnTime;

    public CruiseSailing(String cruiseID, String cruiseShipID, Date leavingTime, Date returnTime) {
        this.cruiseID = cruiseID;
        this.cruiseShipID = cruiseShipID;
        this.leavingTime = leavingTime;
        this.returnTime = returnTime;
    }

    public String getCruiseID() {
        return cruiseID;
    }

    public String getCruiseShipID() {
        return cruiseShipID;
    }

    public Date getLeavingTime() {
        return leavingTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }
}
