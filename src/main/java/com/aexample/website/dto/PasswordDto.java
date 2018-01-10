package com.aexample.website.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aexample.annotations.IValidEmail;
import com.aexample.annotations.IValidPassword;

@IValidPassword
public class PasswordDto {

    private String oldPassword;

    private String newPassword;
    
    private String confirmPassword;
    
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public String getConfirmPassword() {
        return newPassword;
    }

    public void setConfirmPassword(String newPassword) {
        this.confirmPassword = confirmPassword;
    }    

}
