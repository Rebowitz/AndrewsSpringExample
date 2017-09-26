/**
 * 
 */
package com.aexample.security;

import org.springframework.stereotype.Component;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
@Component
public class AuthAttemptsLogger {
/*    @EventListener
    public void auditEventHappened(
      AuditApplicationEvent auditApplicationEvent) {
         
        AuditEvent auditEvent = auditApplicationEvent.getAuditEvent();
        System.out.println("Principal " + auditEvent.getPrincipal() 
          + " - " + auditEvent.getType());
 
        
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails)  auditEvent.getData().get("details");
//        WebAuthenticationDetails details = 
//          (WebAuthenticationDetails) auditEvent.getData().get("details");
        System.out.println("Remote IP address: "
          + details.getRemoteAddress());
        System.out.println("  Session Id: " + details.getSessionId());
    }
*/    
}
