package io.office360.auth.persistence.dao;

import io.office360.auth.persistence.entity.Privilege;
import io.office360.common.interfaces.IByNameApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrivilegeJpaDao extends
        JpaRepository<Privilege, Long>,
        IByNameApi<Privilege> {
    //
}
