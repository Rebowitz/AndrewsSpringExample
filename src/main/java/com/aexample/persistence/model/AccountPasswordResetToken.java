/**
 * 
 */
package com.aexample.persistence.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Entity
public class AccountPasswordResetToken {
	 private static final int EXPIRATION = 60 * 24;

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String token;

	    @OneToOne(targetEntity = Accounts.class, fetch = FetchType.EAGER)
	    @JoinColumn(nullable = false, name = "user_id")
	    private Accounts account;

	    private Date expiryDate;

	    public AccountPasswordResetToken() {
	        super();
	    }

	    public AccountPasswordResetToken(final String token) {
	        super();

	        this.token = token;
	        this.expiryDate = calculateExpiryDate(EXPIRATION);
	    }

	    public AccountPasswordResetToken(final String token, final Accounts account) {
	        super();

	        this.token = token;
	        this.account = account;
	        this.expiryDate = calculateExpiryDate(EXPIRATION);
	    }

	    //
	    public Long getId() {
	        return id;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(final String token) {
	        this.token = token;
	    }

	    public Accounts getAccount() {
	        return account;
	    }

	    public void setUser(final Accounts account) {
	        this.account = account;
	    }

	    public Date getExpiryDate() {
	        return expiryDate;
	    }

	    public void setExpiryDate(final Date expiryDate) {
	        this.expiryDate = expiryDate;
	    }

	    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
	        final Calendar cal = Calendar.getInstance();
	        cal.setTimeInMillis(new Date().getTime());
	        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
	        return new Date(cal.getTime().getTime());
	    }

	    public void updateToken(final String token) {
	        this.token = token;
	        this.expiryDate = calculateExpiryDate(EXPIRATION);
	    }

	    //

	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
	        result = prime * result + ((token == null) ? 0 : token.hashCode());
	        result = prime * result + ((account == null) ? 0 : account.hashCode());
	        return result;
	    }

	    @Override
	    public boolean equals(final Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final AccountPasswordResetToken other = (AccountPasswordResetToken) obj;
	        if (expiryDate == null) {
	            if (other.expiryDate != null) {
	                return false;
	            }
	        } else if (!expiryDate.equals(other.expiryDate)) {
	            return false;
	        }
	        if (token == null) {
	            if (other.token != null) {
	                return false;
	            }
	        } else if (!token.equals(other.token)) {
	            return false;
	        }
	        if (account == null) {
	            if (other.account != null) {
	                return false;
	            }
	        } else if (!account.equals(other.account)) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        final StringBuilder builder = new StringBuilder();
	        builder.append("Token [String=").append(token).append("]").append("[Expires").append(expiryDate).append("]");
	        return builder.toString();
	    }
}
