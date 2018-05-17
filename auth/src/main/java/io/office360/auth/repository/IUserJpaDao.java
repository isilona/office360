package io.office360.auth.repository;

import io.office360.auth.entity.Account;
import io.office360.common.interfaces.IByNameApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserJpaDao extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account>, IByNameApi<Account> {
    //
}
