package model;

public class Port {

    private String countryName;
    private String portName;

    public Port(String countryName, String portName) {
        this.countryName = countryName;
        this.portName = portName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getPortName() {
        return portName;
    }
}
