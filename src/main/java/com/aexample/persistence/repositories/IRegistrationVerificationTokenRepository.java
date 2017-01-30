/**
 * 
 */
package com.aexample.persistence.repositories;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
	import com.aexample.persistence.model.Accounts;
	import com.aexample.persistence.model.RegistrationVerificationToken;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Modifying;
	import org.springframework.data.jpa.repository.Query;

	import java.util.Date;
	import java.util.stream.Stream;

	public interface IRegistrationVerificationTokenRepository extends JpaRepository<RegistrationVerificationToken, Long> {

	    RegistrationVerificationToken findByToken(String token);

	    RegistrationVerificationToken findByRegistration(Accounts account);

	    Stream<RegistrationVerificationToken> findAllByExpiryDateLessThan(Date now);

	    void deleteByExpiryDateLessThan(Date now);

	    
	    @Modifying
	    @Query("delete from RegistrationVerificationToken t where t.expiryDate <= ?1")
	    void deleteAllExpiredSince(Date now);
	}


