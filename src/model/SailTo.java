package model;

import java.util.Date;

public class SailTo {

    private String countryName;
    private String portName;
    private String sailingID;
    private Date arrivalTime;
    private Date leavingTime;

    public SailTo(String countryName, String portName, String sailingID, Date arrivalTime, Date leavingTime) {
        this.countryName = countryName;
        this.portName = portName;
        this.sailingID = sailingID;
        this.arrivalTime = arrivalTime;
        this.leavingTime = leavingTime;
    }

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
}
