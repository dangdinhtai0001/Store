package entity;

import java.util.Date;

public class Employee {
	private int employeeId;
	private String name, gender, adress, image, position;
	private Date dateOfBirth, dateOfBegin;

	public Employee(int employeeId, String name, String gender, Date dateOfBirth, String adress, String position,
			Date dateOfBegin, String image) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.gender = gender;
		this.adress = adress;
		this.image = image;
		this.position = position;
		this.dateOfBirth = dateOfBirth;
		this.dateOfBegin = dateOfBegin;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getAdress() {
		return adress;
	}

	public String getImage() {
		return image;
	}

	public String getPosition() {
		return position;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Date getDateOfBegin() {
		return dateOfBegin;
	}

}
