package model;
/**
 * Class FiveQuery ~ represent the 5th query in the DB
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class FiveQuery {
	// ------------------------------- Class Members ------------------------------
	private int year;
	private String countryName;
	private String portName;
	private int numOfPersons;

	// -------------------------------- Constructors ------------------------------
	public FiveQuery(int year, String countryName, String portName, int numOfPersons) {
		this.year = year;
		this.countryName = countryName;
		this.portName = portName;
		this.numOfPersons = numOfPersons;
	}

	// ----------------------------- Getters & Setters ----------------------------
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

}
