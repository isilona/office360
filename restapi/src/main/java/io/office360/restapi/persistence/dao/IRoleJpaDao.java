package io.office360.restapi.persistence.dao;

import io.office360.common.interfaces.IByNameApi;
import io.office360.restapi.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRoleJpaDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>, IByNameApi<Role> {
    //
}
