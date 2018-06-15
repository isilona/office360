package io.office360.auth.persistence.dao;

import io.office360.auth.persistence.entity.Role;
import io.office360.common.interfaces.IByNameApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleJpaDao extends
        JpaRepository<Role, Long>,
        IByNameApi<Role> {
    //
}
