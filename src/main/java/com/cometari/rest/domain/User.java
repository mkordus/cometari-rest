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

	public User setFirstName(String firstName) {
    	this.firstName = firstName;

    	return this;
	}

	public String getFirstName() {    
		return firstName;
	}

	public User setLastName(String lastName) {
    	this.lastName = lastName;

    	return this;
	}

	public String getLastName() {    
		return lastName;
	}

	public User setEmailAddress(String emailAddress) {
    	this.emailAddress = emailAddress;

    	return this;
	}

	public String getEmailAddress() {    
		return emailAddress;
	}

	public User setActivated(Boolean isActivated) {
    	this.isActivated = isActivated;

    	return this;
	}

	public Boolean getIsActivated() {    
		return isActivated;
	}

	public User setPasswordHash(String passwordHash) {
    	this.passwordHash = passwordHash;

    	return this;
	}

	public String getPasswordHash() {    
		return passwordHash;
	}

}
