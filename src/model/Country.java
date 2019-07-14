package model;
/**
 * Class Country ~ represent a single Country
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class Country {
	// ------------------------------- Class Members ------------------------------
	private String countryName;

	// -------------------------------- Constructors ------------------------------
	public Country(String countryName) {
		this.countryName = countryName;
	}

	// ----------------------------- Getters & Setters ----------------------------
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	// ------------------------------- More Methods -------------------------------
	//TODO

	// ----------------------------- HashCode & Equals ----------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
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
		Country other = (Country) obj;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equalsIgnoreCase(other.countryName))
			return false;
		return true;
	}

	// ---------------------------------- toString --------------------------------
	@Override
	public String toString() {
		return countryName;
	}
}
