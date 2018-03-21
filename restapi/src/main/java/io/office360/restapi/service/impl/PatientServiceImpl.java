package io.office360.restapi.service.impl;

import io.office360.common.persistence.service.AbstractService;
import io.office360.restapi.persistence.dao.IPatientJpaDao;
import io.office360.restapi.persistence.model.Patient;
import io.office360.restapi.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl extends AbstractService<Patient> implements IPatientService {

    @Autowired
    private IPatientJpaDao dao;

    public PatientServiceImpl() {
        super();
    }

    // API

    // find

    @Override
    @Transactional(readOnly = true)
    public Patient findByName(final String name) {
        return dao.findByName(name);
    }

    // Spring

    @Override
    protected final IPatientJpaDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<Patient> getSpecificationExecutor() {
        return dao;
    }

}
