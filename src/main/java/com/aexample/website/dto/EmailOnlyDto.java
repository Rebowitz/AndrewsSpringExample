package com.aexample.website.dto;


public class EmailOnlyDto {
	
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailOnlyDto [email=" + email + "]";
	}

}
