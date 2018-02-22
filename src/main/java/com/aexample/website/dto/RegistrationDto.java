/**
 * 
 */
package com.aexample.website.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aexample.annotations.IValidEmail;
import com.aexample.annotations.IValidPassword;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@IValidPassword
public class RegistrationDto {


    @NotNull
    int id;

    @NotNull
    @Size(min = 1)
    String organizationName;

    @NotNull
    @Size(min = 1)
    String organizationKey;

    @IValidEmail
    @NotNull
    @Size(min = 1)
    String email;

    String password;

    Boolean registered;

    @NotNull
    @Size(min = 1)
    String deviceId;


    public String getOrganizationName(){
        return organizationName;
    }

    public void setOrganizationName(String organizationName){
        this.organizationName = organizationName;
    }

    public String getOrganizationKey(){
        return organizationKey;
    }

    public void setOrganizationKey(String organizationKey){
        this.organizationKey = organizationKey;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Boolean getRegistered(){
        return registered;
    }

    public void setRegistered(Boolean registered){
        this.registered = registered;
    }

    public String getDeviceId(){
        return deviceId;
    }

    public void setDeviceId(String deviceId){
        this.deviceId = deviceId;
    }

    
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((organizationKey == null) ? 0 : organizationKey.hashCode());
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((registered == null) ? 0 : registered.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationDto other = (RegistrationDto) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (organizationKey == null) {
			if (other.organizationKey != null)
				return false;
		} else if (!organizationKey.equals(other.organizationKey))
			return false;
		if (organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!organizationName.equals(other.organizationName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registered == null) {
			if (other.registered != null)
				return false;
		} else if (!registered.equals(other.registered))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegistrationDto [id=" + id + ", organizationName=" + organizationName + ", organizationKey="
				+ organizationKey + ", email=" + email + ", password=" + password + ", registered=" + registered
				+ ", deviceId=" + deviceId + "]";
	}


}
