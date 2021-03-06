package com.aexample.persistence.model;
// Generated Aug 19, 2017 12:41:47 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RolesPrivileges generated by hbm2java
 */

public class RolesPrivileges implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long role_id;
	private Long privilege_id;



	public RolesPrivileges() {
	}

	public Long getRole_id() {
		return this.role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public Long getPrivilege_id() {
		return privilege_id;
	}

	public void setPrivilege_id(Long privilege_id) {
		this.privilege_id = privilege_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((privilege_id == null) ? 0 : privilege_id.hashCode());
		result = prime * result + ((role_id == null) ? 0 : role_id.hashCode());
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
		RolesPrivileges other = (RolesPrivileges) obj;
		if (privilege_id == null) {
			if (other.privilege_id != null)
				return false;
		} else if (!privilege_id.equals(other.privilege_id))
			return false;
		if (role_id == null) {
			if (other.role_id != null)
				return false;
		} else if (!role_id.equals(other.role_id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RolesPrivileges [role_id=" + role_id + ", privilege_id=" + privilege_id + "]";
	}	
	
}
