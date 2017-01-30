package com.aexample.persistence.repositories;


import com.aexample.persistence.model.User;
import com.aexample.persistence.model.UserVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

public interface IUserVerificationTokenRepository extends JpaRepository<UserVerificationToken, Long> {

    UserVerificationToken findByToken(String token);

    UserVerificationToken findByUser(User user);

    Stream<UserVerificationToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from UserVerificationToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
