package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class User {
    @Id
    private String fullName;
    private String phoneNumber;
    private String username;
    private String password;
    private String role;

    public User(String fullName,String phoneNumber,String username, String password, String role) {
    	this.fullName=fullName;
    	this.phoneNumber=phoneNumber;
        this.username = username;
        this.password = password;
        this.role = role;
    }


	public User() {
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
		this.fullName = fullName;
	}
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return
				"fullName='" + fullName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				'\n';
	}
}
