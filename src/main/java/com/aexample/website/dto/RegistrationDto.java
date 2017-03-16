/**
 * 
 */
package com.aexample.website.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aexample.website.validator.IValidEmail;
import com.aexample.website.validator.IValidPassword;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
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

    @IValidPassword
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

    public String getdeviceId(){
        return deviceId;
    }

    public void setdeviceId(String deviceId){
        this.deviceId = deviceId;
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
