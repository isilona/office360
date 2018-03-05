package io.office360.restapi.persistence.dao;

import io.office360.common.interfaces.IByNameApi;
import io.office360.restapi.persistence.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserJpaDao extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account>, IByNameApi<Account> {
    //
}
