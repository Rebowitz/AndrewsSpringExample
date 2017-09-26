package com.aexample.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aexample.persistence.model.UserAccount;

public interface IUserRepository extends JpaRepository<UserAccount, Long> {
	
	
//	@Query("Select u from UserAccount u WHERE u.email = :email")
//	UserAccount findByEmail(@Param("email") String email);
	UserAccount findByEmail(String email);

    List<UserAccount> findAll();
  
    //query incorrect from cut and paste operation
    @Query("SELECT t FROM UserAccount t WHERE " +
            "LOWER(t.firstName) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(t.lastName) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " +
            "ORDER BY t.lastName ASC")
    List<UserAccount> findBySearchTerm(@Param("searchTerm") String searchTerm);

    UserAccount findOne(Long id);

    void flush();

    @SuppressWarnings("unchecked")
	UserAccount save(UserAccount persisted);
    
    //accountLocked 1/yes = locked no = 0
    //accountNonLocked 1 = unlocked, 0 = locked
    
    @Query("UPDATE UserAccount u SET u.accountNonLocked = 0 WHERE u.email = :email")
    void lockUserAccount(@Param("email") String email);
}
