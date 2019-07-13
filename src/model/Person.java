package model;

import java.sql.Date;
/**
 * Class Person ~ represent a single Person
 * 
 * @author Shany Klein
 * @author Guy Levy
 */
public class Person {
	// ------------------------------- Class Members ------------------------------
	private String personID;
	private String firstName;
	private String surName;
	private Date dateOfBirth;
	private String phone;
	private String email;
	private String pass;

	// -------------------------------- Constructors ------------------------------
	public Person(String personID, String firstName, String surName, Date dateOfBirth, String phone, String email, String pass) {
		this.personID = personID;
		this.firstName = firstName;
		this.surName = surName;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.email = email;
		this.pass = pass;
	}

	public Person(String personID) {
		this.personID = personID;
	}
	
	// ----------------------------- Getters & Setters ----------------------------
	public String getPersonID() {
		return personID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurName() {
		return surName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// ------------------------------- More Methods -------------------------------
	//TODO

	// ----------------------------- HashCode & Equals ----------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personID == null) ? 0 : personID.hashCode());
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
		Person other = (Person) obj;
		if (personID == null) {
			if (other.personID != null)
				return false;
		} else if (!personID.equals(other.personID))
			return false;
		return true;
	}

	// ---------------------------------- toString --------------------------------
	@Override
	public String toString() {
		return "Person [personID=" + personID + ", firstName=" + firstName + ", surName=" + surName + ", dateOfBirth="
				+ dateOfBirth + ", phone=" + phone + ", email=" + email + ", pass=" + pass + "]";
	}
}
