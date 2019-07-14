package model;
/**
 * Class Port ~ represent a single Port
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class Port {
	// ------------------------------- Class Members ------------------------------
	private String countryName;
	private String portName;

	// -------------------------------- Constructors ------------------------------
	public Port(String countryName, String portName) {
		this.countryName = countryName;
		this.portName = portName;
	}

	// ----------------------------- Getters & Setters ----------------------------
	public String getCountryName() {
		return countryName;
	}

	public String getPortName() {
		return portName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	// ------------------------------- More Methods -------------------------------
	//TODO

	// ----------------------------- HashCode & Equals ----------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((portName == null) ? 0 : portName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Port other = (Port) obj;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equalsIgnoreCase(other.countryName))
			return false;
		if (portName == null) {
			if (other.portName != null)
				return false;
		} else if (!portName.equals(other.portName))
			return false;
		return true;
	}

	// ---------------------------------- toString --------------------------------
	@Override
	public String toString() {
		return portName + ", " +countryName;
	}


}
