package io.office360.restapi.service.impl;

import io.office360.common.persistence.service.AbstractService;
import io.office360.common.web.controller.data.mapping.IMapper;
import io.office360.restapi.persistence.dao.IPatientJpaDao;
import io.office360.restapi.persistence.model.Patient;
import io.office360.restapi.service.IPatientService;
import io.office360.restapi.web.controller.data.mapping.PatientMapper;
import io.office360.restapi.web.controller.data.response.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl extends AbstractService<Patient, PatientDto> implements IPatientService {

    private final IPatientJpaDao dao;

    private final PatientMapper mapper;

    @Autowired
    public PatientServiceImpl(IPatientJpaDao dao, PatientMapper mapper) {
        super();
        this.dao = dao;
        this.mapper = mapper;
    }

    // API

    // find

    @Override
    @Transactional(readOnly = true)
    public Patient findByName(final String name) {
        return dao.findByName(name);
    }

    @Override
    protected IMapper getMapper() {
        return mapper;
    }

    // Spring

    @Override
    protected final IPatientJpaDao getDao() {
        return dao;
    }
}
