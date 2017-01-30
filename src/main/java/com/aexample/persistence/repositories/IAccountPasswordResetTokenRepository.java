/**
 * 
 */
package com.aexample.persistence.repositories;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.aexample.persistence.model.AccountPasswordResetToken;
import com.aexample.persistence.model.Accounts;


/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */
public interface IAccountPasswordResetTokenRepository extends JpaRepository<AccountPasswordResetToken, Long> {


    AccountPasswordResetToken findByToken(String token);

    AccountPasswordResetToken findByAccount(Accounts account);

    Stream<AccountPasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from AccountPasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
	
}
