package model;

public class FiveQuery {

    private int year;
    private String countryName;
    private String portName;
    private int numOfPersons;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getNumOfPersons() {
        return numOfPersons;
    }

    public void setNumOfPersons(int numOfPersons) {
        this.numOfPersons = numOfPersons;
    }

    public FiveQuery(int year, String countryName, String portName, int numOfPersons) {
        this.year = year;
        this.countryName = countryName;
        this.portName = portName;
        this.numOfPersons = numOfPersons;
    }
}
