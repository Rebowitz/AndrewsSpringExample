/**
 * 
 */
package com.aexample.persistence.repositories;

import com.aexample.persistence.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Main Login
 * $Rev$
 * $Date$
 *
 */

public interface IRegistrationRepository  extends JpaRepository<Accounts, Long>{
    Accounts findByEmail(String email);

    @Override
    void delete(Accounts account);
}
