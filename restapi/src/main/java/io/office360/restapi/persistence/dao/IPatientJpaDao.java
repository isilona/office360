package io.office360.restapi.persistence.dao;

import io.office360.common.interfaces.IByNameApi;
import io.office360.restapi.persistence.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IPatientJpaDao extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient>, IByNameApi<Patient> {
    //
}