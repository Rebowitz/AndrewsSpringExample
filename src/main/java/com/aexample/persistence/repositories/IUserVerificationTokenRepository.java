package com.aexample.persistence.repositories;


import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.aexample.persistence.model.Privileges;
import com.aexample.persistence.model.UserAccount;
import com.aexample.persistence.model.UserVerificationToken;

public interface IUserVerificationTokenRepository  extends JpaRepository<UserVerificationToken, Long> {

//	void delete(String token);
	
	/* (non-Javadoc)
	 * @see com.aexample.persistence.repositories.IBaseRepository#delete(com.aexample.persistence.model.Privileges)
	 */
	//@Override
	default void delete(Privileges privileges) {
		// TODO Auto-generated method stub
		
	}

	void delete(UserVerificationToken token);
	
	public List<UserVerificationToken> findAll();
	
	public UserVerificationToken findOne(Long id);
	
	@SuppressWarnings("unchecked")
	public UserVerificationToken save(UserVerificationToken persisted);
	
	public UserVerificationToken findByToken(String token);

	public UserVerificationToken findByUser(UserAccount user);
	
    @Query("select t from UserVerificationToken t where t.previousToken = ?1")
	public UserVerificationToken findByPreviousToken(String verificationToken);

	public Stream<UserVerificationToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from UserVerificationToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);


}
