package com.aexample.website.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aexample.annotations.IValidEmail;
import com.aexample.annotations.IValidPassword;


@IValidPassword
public class UserDto {
	
    @NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;

    //@IValidPassword
    @NotNull
    @Size(min = 8, max=16)    
    private String password;

    @NotNull
    @Size(min = 8, max=16)
    private String matchingPassword;

    @IValidEmail
    @NotNull
    @Size(min = 1)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
    

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=").append(firstName).append(", lastName=").append(lastName).append(", password=").append(password).append(", matchingPassword=").append(matchingPassword).append(", email=").append(email)
                .append("]");
        return builder.toString();
    }

}
