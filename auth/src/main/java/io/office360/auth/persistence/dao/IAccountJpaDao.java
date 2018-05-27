package io.office360.auth.persistence.dao;

import io.office360.auth.persistence.entity.Account;
import io.office360.common.interfaces.IByNameApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAccountJpaDao extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account>, IByNameApi<Account> {
    //
}
