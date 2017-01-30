package com.aexample.persistence.repositories;

import com.aexample.persistence.model.UserPasswordResetToken;
import com.aexample.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

public interface IUserPasswordResetTokenRepository extends JpaRepository<UserPasswordResetToken, Long> {

    UserPasswordResetToken findByToken(String token);

    UserPasswordResetToken findByUser(User user);

    Stream<UserPasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from UserPasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
