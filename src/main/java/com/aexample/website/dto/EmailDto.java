/**
 * 
 */
package com.aexample.website.dto;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public class EmailDto {
	
	private String configname;
	private String filename;
	private String toaddress;
	private String fromaddress;
	private String subject;
	private String placeholder;
	private String urlsuffix;
	private Boolean tokenneeded;
	private Boolean multipart;
	private String mimetype;
	
	/**
	 * @return the filename
	 */
	public String getConfigname() {
		return configname;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setConfigname(String configname) {
		this.configname = configname;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the fromaddress
	 */
	public String getFromaddress() {
		return fromaddress;
	}
	/**
	 * @param fromaddress the fromaddress to set
	 */
	public void setFromaddress(String fromaddress) {
		this.fromaddress = fromaddress;
	}
	
	public String getToaddress() {
		return toaddress;
	}
	/**
	 * @param toaddress the toaddress to set
	 */
	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}
	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the placeholder
	 */
	public String getPlaceholder() {
		return placeholder;
	}
	/**
	 * @param placeholder the placeholder to set
	 */
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getUrlsuffix() {
		return urlsuffix;
	}
	/**
	 * @param placeholder the placeholder to set
	 */
	public void setUrlsuffix(String urlsuffix) {
		this.urlsuffix = urlsuffix;
	}
	
	public Boolean getTokenneeded() {
		return tokenneeded;
	}
	/**
	 * @param placeholder the placeholder to set
	 */
	public void setTokenneeded(Boolean tokenneeded) {
		this.tokenneeded = tokenneeded;
	}
	
	public Boolean getMultipart(){
		return multipart;
	}
	
	public void setMultipart(Boolean multipart){
		this.multipart = multipart;
	}
	
	public String getMimetype(){
		return mimetype;
	}
	
	public void setMimetype(String mimetype){
		this.mimetype = mimetype;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((configname == null) ? 0 : configname.hashCode());
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((fromaddress == null) ? 0 : fromaddress.hashCode());
		result = prime * result + ((mimetype == null) ? 0 : mimetype.hashCode());
		result = prime * result + ((multipart == null) ? 0 : multipart.hashCode());
		result = prime * result + ((placeholder == null) ? 0 : placeholder.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((toaddress == null) ? 0 : toaddress.hashCode());
		result = prime * result + ((tokenneeded == null) ? 0 : tokenneeded.hashCode());
		result = prime * result + ((urlsuffix == null) ? 0 : urlsuffix.hashCode());
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
		EmailDto other = (EmailDto) obj;
		if (configname == null) {
			if (other.configname != null)
				return false;
		} else if (!configname.equals(other.configname))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (fromaddress == null) {
			if (other.fromaddress != null)
				return false;
		} else if (!fromaddress.equals(other.fromaddress))
			return false;
		if (mimetype == null) {
			if (other.mimetype != null)
				return false;
		} else if (!mimetype.equals(other.mimetype))
			return false;
		if (multipart == null) {
			if (other.multipart != null)
				return false;
		} else if (!multipart.equals(other.multipart))
			return false;
		if (placeholder == null) {
			if (other.placeholder != null)
				return false;
		} else if (!placeholder.equals(other.placeholder))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (toaddress == null) {
			if (other.toaddress != null)
				return false;
		} else if (!toaddress.equals(other.toaddress))
			return false;
		if (tokenneeded == null) {
			if (other.tokenneeded != null)
				return false;
		} else if (!tokenneeded.equals(other.tokenneeded))
			return false;
		if (urlsuffix == null) {
			if (other.urlsuffix != null)
				return false;
		} else if (!urlsuffix.equals(other.urlsuffix))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailDto [configname=" + configname + ", filename=" + filename + ", toaddress=" + toaddress
				+ ", fromaddress=" + fromaddress + ", subject=" + subject + ", placeholder=" + placeholder
				+ ", urlsuffix=" + urlsuffix + ", tokenneeded=" + tokenneeded + ", mimetype=" + mimetype
				+ ", multipart=" + multipart + "]";
	}

	

}
