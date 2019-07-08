package model;

import java.util.Date;

public class SailTo {

    private Country countryName;
    private Port portName;
    private CruiseSailing sailingID;
    private Date arrivalTime;
    private Date leavingTime;

    public SailTo(Country countryName, Port portName, CruiseSailing sailingID, Date arrivalTime, Date leavingTime) {
        this.countryName = countryName;
        this.portName = portName;
        this.sailingID = sailingID;
        this.arrivalTime = arrivalTime;
        this.leavingTime = leavingTime;
    }

    public Country getCountryName() {
        return countryName;
    }

    public Port getPortName() {
        return portName;
    }

    public CruiseSailing getSailingID() {
        return sailingID;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getLeavingTime() {
        return leavingTime;
    }
}
