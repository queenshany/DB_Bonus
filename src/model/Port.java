package model;

public class Port {

    private Country countryName;
    private String portName;

    public Port(Country countryName, String portName) {
        this.countryName = countryName;
        this.portName = portName;
    }

    public Country getCountryName() {
        return countryName;
    }

    public String getPortName() {
        return portName;
    }
}
