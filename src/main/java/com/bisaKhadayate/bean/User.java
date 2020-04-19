package com.bisaKhadayate.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class User  implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="firstname" ,nullable = false)
	private String firstName;
	
	@Column(name="lastname" ,nullable = false)
	private String lastName;
	
	@Column(name="emailid" , nullable = false , unique=true)
	private String emailId;
	
	@Column(name="userid" , nullable = false , unique=true)
	private String userId;
	
	@Column
	private String password;
	
	@Column(nullable = false)
	private String dob;
	
	@Column
	private String time;
	
	@Column
	private String height;
	
	@Column(name="placeofbirth")
	private String placeOfBirth;
	
	@Column(name="currentaddress",length = 300)
	private String currentAddress;
	
	@Column(name="parmanentaddress",length = 300)
	private String permanentAddress;
	
	@Column(name="education")
	private String highestEducation;
	
	@Column
	private String caste;
	
	@Column(length = 300)
	private String occupation;
	
	@Column(name="familydetails",length = 500)
	private String familyDetails;
	
	@Column(name="contactnumber")
	private String contactNumber;
	
	@Column
	private String colour;
	
	@Column(name="isadmin" , columnDefinition = "boolean default false" )
	private Boolean isAdmin;
	
	@Column(name="isactives" , columnDefinition = "boolean default true")
	private Boolean isActive;
	
	@Column
	private Character gender;
	
	@Column
	private String gotra;
	
	@Transient
	private List<String> fileNameList;
	
	@Column(name="displaypic")
	private String displayPic;
	
	
	
	public String getDisplayPic() {
		return displayPic;
	}

	public void setDisplayPic(String displayPic) {
		this.displayPic = displayPic;
	}

	public List<String> getFileNameList() {
		return fileNameList;
	}

	public void setFileNameList(List<String> fileNameList) {
		this.fileNameList = fileNameList;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGotra() {
		return gotra;
	}

	public void setGotra(String gotra) {
		this.gotra = gotra;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getEducation() {
		return highestEducation;
	}

	public void setEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getFamilyDetails() {
		return familyDetails;
	}

	public void setFamilyDetails(String familyDetails) {
		this.familyDetails = familyDetails;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
	

}
