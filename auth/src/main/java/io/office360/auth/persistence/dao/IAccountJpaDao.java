package io.office360.auth.persistence.dao;

import io.office360.auth.persistence.entity.Account;
import io.office360.common.interfaces.IByNameApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountJpaDao extends
        JpaRepository<Account, Long>,
        IByNameApi<Account> {
    //

    Account findByUsername(String username);
}
