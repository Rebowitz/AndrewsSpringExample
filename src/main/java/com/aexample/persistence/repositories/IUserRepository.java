package com.aexample.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aexample.persistence.model.UserAccount;

public interface IUserRepository extends JpaRepository<UserAccount, Long> {
	

	//userAccount name is expected as UserAccount in these type queries
	//it be weird
	
	@Query("Select u from UserAccount u WHERE u.email = :email")
	UserAccount findByEmail(@Param("email") String email);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE UserAccount u SET u.accountNonLocked = false WHERE u.email = :email")
    int lockUserAccount(@Param("email") String email);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE UserAccount u SET u.accountNonExpired = false WHERE u.email = :email")
    void expireUserAccount(@Param("email") String email);
    
	@Modifying(clearAutomatically = true)
    @Query("UPDATE UserAccount u SET u.credentialsNonExpired = false WHERE u.email = :email")
    void expireUserCredentials(@Param("email") String email);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE UserAccount u SET u.enabled = false WHERE u.email = :email")
    void disableUserAccount(@Param("email") String email);	
    
	@Modifying(clearAutomatically = true)
    @Query("UPDATE UserAccount u SET u.accountNonLocked = true WHERE u.email = :email")
    void unlockUserAccount(@Param("email") String email);
    
	@Modifying(clearAutomatically = true)
    @Query("UPDATE UserAccount u SET u.accountNonExpired = true WHERE u.email = :email")
    void reactivateUserAccount(@Param("email") String email);
 
	@Modifying(clearAutomatically = true)
    @Query("UPDATE UserAccount u SET u.credentialsNonExpired = true WHERE u.email = :email")
    void reactivateUserCredentials(@Param("email") String email);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE UserAccount u SET u.enabled = true WHERE u.email = :email")
    void enableUserAccount(@Param("email") String email);	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE UserAccount u SET u.encryptedPassword = :encryptedPassword WHERE u.email = :email")
    void updateEncryptedPassword(@Param("email") String email, @Param("encryptedPassword") String encryptedPassword);
}
