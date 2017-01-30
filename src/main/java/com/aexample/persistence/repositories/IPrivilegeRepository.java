package com.aexample.persistence.repositories;

import com.aexample.persistence.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
