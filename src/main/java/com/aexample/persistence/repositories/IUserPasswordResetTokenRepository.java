package com.aexample.persistence.repositories;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.aexample.persistence.model.Privileges;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserPasswordResetToken;

public interface IUserPasswordResetTokenRepository extends JpaRepository<UserPasswordResetToken, Long> {
			//all CRUD operations are in IBaseRepository

//	void delete(String token);
	
	/* (non-Javadoc)
	 * @see com.aexample.persistence.repositories.IBaseRepository#delete(com.aexample.persistence.model.Privileges)
	 */
	//@Override
	default void delete(Privileges privileges) {
		// TODO Auto-generated method stub
		
	}

	void delete(UserPasswordResetToken token);
	
	public List<UserPasswordResetToken> findAll();
	
	public UserPasswordResetToken findOne(Long id);
	
	public UserPasswordResetToken save(UserPasswordResetToken persisted);
	
    UserPasswordResetToken findByToken(String token);

    UserPasswordResetToken findByUser(UserAccount user);

    Stream<UserPasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from UserPasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiryDateSince(Date now);
}
