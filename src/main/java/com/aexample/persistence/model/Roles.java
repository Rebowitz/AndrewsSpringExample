/**
 * 
 */
package com.aexample.persistence.model;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @NotNull
	private String name;
    
    @ManyToMany(mappedBy = "roles")  //this is the mapped name in the UserAccount class
    private Collection<UserAccount> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privileges> privileges;

    public Roles() {
        super();
    }

    public Roles(final String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Collection<UserAccount> getUsers() {
        return users;
    }

    public void setUsers(final Set<UserAccount> users) {
        this.users = users;
    }

    public Collection<Privileges> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(final Collection<Privileges> privileges) {
        this.privileges = privileges;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        final Roles roles = (Roles) obj;
        if (!roles.equals(roles.name)) {
            return false;
        }
        return true;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Roles [id=" + id + ", name=" + name + ", users=" + users + ", privileges=" + privileges + "]";
	}


}
