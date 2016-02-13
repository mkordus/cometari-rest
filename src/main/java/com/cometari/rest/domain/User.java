package com.cometari.rest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Boolean isActivated;
	private String passwordHash;

	public User() {}

    public User(
        String firstName, 
        String lastName, 
        String emailAddress, 
        Boolean isActivated, 
        String passwordHash
    ) { 
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.isActivated = isActivated;
		this.passwordHash = passwordHash;
    }

    public long getId() {
        return this.id;
    }

	public void setFirstName(String firstName) {
    	this.firstName = firstName;
	}

	public String getFirstName() {    
		return firstName;
	}

	public void setLastName(String lastName) {
    	this.lastName = lastName;
	}

	public String getLastName() {    
		return lastName;
	}

	public void setEmailAddress(String emailAddress) {
    	this.emailAddress = emailAddress;
	}

	public String getEmailAddress() {    
		return emailAddress;
	}

	public void setIsActivated(Boolean isActivated) {
    	this.isActivated = isActivated;
	}

	public Boolean getIsActivated() {    
		return isActivated;
	}

	public void setPasswordHash(String passwordHash) {
    	this.passwordHash = passwordHash;
	}

	public String getPasswordHash() {    
		return passwordHash;
	}

}
