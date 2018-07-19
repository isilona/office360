package io.office360.restapi.web.controller.data.mapping;

import io.office360.common.web.controller.data.mapping.IMapper;
import io.office360.restapi.persistence.model.Patient;
import io.office360.restapi.web.controller.data.response.PatientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper extends IMapper<PatientDto, Patient> {

    PatientDto entityToDto(Patient entity);

    Patient dtoToEntity(PatientDto dto);

}
