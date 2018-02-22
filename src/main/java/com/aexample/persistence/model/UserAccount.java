package com.aexample.persistence.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.jboss.aerogear.security.otp.api.Base32;


@Entity
@Table(name = "userAccount")
public class UserAccount{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    //mysql boolean translation is int
	@NotNull
    private Boolean accountNonExpired;

	@NotNull
	private Boolean accountNonLocked; 

	@NotNull
	@Column(name = "createDate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
	@NotNull
    private Boolean credentialsNonExpired;
    
	private String deviceId;   

    @NotNull
    private String email;
    
    @NotNull
    private Boolean enabled;
    
    @NotNull
    private String encryptedPassword;
    
	@NotNull
	private String firstName;

    @NotNull    
    private String lastName;

    @Transient
    private String password;

    private String secret;
        
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Roles> roles;

    public UserAccount() {
        super();
        this.secret = Base32.random();
        this.enabled = false;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(final String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<Roles> roles) {
        this.roles = roles;
    }
    
    public void addRole(Roles roles){
        if(!this.roles.contains(roles)){
            this.roles.add(roles);
        }
 
        if(!roles.getUsers().contains(this)){
            roles.getUsers().add(this);
        }
    }
 
    public void removeRole(Roles roles){
        this.roles.remove(roles);
        roles.getUsers().remove(this);
    }    

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    /**
	 * @return the accountNonExpired
	 */
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @param accountNonExpired the accountNonExpired to set
	 */
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @param accountNonLocked the accountNonLocked to set
	 */
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @param credentialsNonExpired the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	 


	
}