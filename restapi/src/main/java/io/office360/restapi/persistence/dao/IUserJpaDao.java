package io.office360.restapi.persistence.dao;

import io.office360.common.interfaces.IByNameApi;
import io.office360.restapi.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserJpaDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, IByNameApi<User> {
    //
}
