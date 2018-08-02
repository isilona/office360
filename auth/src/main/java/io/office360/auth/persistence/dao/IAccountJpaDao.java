package io.office360.auth.persistence.dao;

import io.office360.auth.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountJpaDao extends
        JpaRepository<Account, Long> {
    //

    Account findByUsername(String username);
}
