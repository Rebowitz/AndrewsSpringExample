/**
 * 
 */
package com.aexample.security;

import javax.servlet.http.HttpServletRequest;
/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

import org.slf4j.Logger;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.aexample.annotations.ILogger;

/**
 * @author Kamill Sokol
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String itemId;
	
//	Logger logger = LoggerFactory.getLogger(CustomWebAuthenticationDetails.class);
	private static @ILogger Logger logger;	
	
	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		itemId = request.getParameter("itemId");
	}

	public String getItemId() {
		return itemId;
	}

	@Override
	public int hashCode() {
		int code = super.hashCode();

		if (this.itemId != null) {
			code = code * (this.itemId.hashCode() % 7);
		}

		return code;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = super.equals(obj);

		if(obj instanceof CustomWebAuthenticationDetails) {
			CustomWebAuthenticationDetails rhs = (CustomWebAuthenticationDetails) obj;

			if ((itemId == null) && (rhs.getItemId() != null)) {
				return false;
			}

			if ((itemId != null) && (rhs.getItemId() == null)) {
				return false;
			}

			if (itemId != null) {
				if (!itemId.equals(rhs.getItemId())) {
					return false;
				}
			}

			return equals && true;
		}

		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("RemoteIpAddress: ").append(this.getRemoteAddress()).append("; ");
		sb.append("SessionId: ").append(this.getSessionId());
		sb.append("itemId: ").append(this.getItemId());

		return sb.toString();
	}
}

