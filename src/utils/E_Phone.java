package utils;
/**
 * This enum class represents a start of a phone in the system
 * @author Shany Klein & Guy Levy
 *
 */
public enum E_Phone {
	c050("050"),
	c052("052"),
	c053("053"),
	c054("054"),
	c055("055"),
	c057("057");
	
	private String text;
	
	E_Phone(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public static E_Phone getPhone(String text) {
		for (E_Phone phone : E_Phone.values())
			if (phone.text.equalsIgnoreCase(text))
				return phone;
		return null;
	}
}
