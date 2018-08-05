package io.office360.restapi.web.controller;

import com.querydsl.core.types.Predicate;
import io.office360.common.util.QueryConstants;
import io.office360.common.web.controller.AbstractController;
import io.office360.common.web.controller.IPagingAndSortingOperationsController;
import io.office360.restapi.persistence.dao.IPatientJpaDao;
import io.office360.restapi.persistence.model.Patient;
import io.office360.restapi.service.IPatientService;
import io.office360.restapi.util.Office360Mappings;
import io.office360.restapi.web.controller.data.response.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = Office360Mappings.PATIENTS)
public class PatientRecordController extends AbstractController implements IPagingAndSortingOperationsController {

    private final IPatientService service;

    private final IPatientJpaDao patientRepository;

    @Autowired
    public PatientRecordController(IPatientService service, IPatientJpaDao patientRepository) {
        super(PatientDto.class);
        this.service = service;
        this.patientRepository = patientRepository;
    }

    // API

    // CREATE

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestBody @Valid final PatientDto resource,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        createInternal(resource, uriBuilder, response);
    }

    // READ

    @GetMapping(value = "/{id}")
    @ResponseBody
    //    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public PatientDto findOne(
            @PathVariable("id") final Long id,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findOneInternal(id, uriBuilder, response);
    }

    @GetMapping
    @ResponseBody
    //    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public List<PatientDto> findAll(
            final HttpServletRequest request,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findAllInternal(request, uriBuilder, response);
    }

    // UPDATE

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    //    @Secured(Privileges.CAN_PATIENT_RECORD_WRITE)
    public void update(@PathVariable("id") final Long id, @RequestBody @Valid final PatientDto resource) {
        updateInternal(id, resource);
    }

    // DELETE

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //    @Secured(Privileges.CAN_PATIENT_RECORD_WRITE)
    public void delete(@PathVariable("id") final Long id) {
        deleteInternal(id);
    }

    // API - PAGING & SORTING

    @GetMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY})
    @ResponseBody
    //    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public List<PatientDto> findAllPaginatedAndSorted(
            @RequestParam(value = QueryConstants.PAGE) final int page,
            @RequestParam(value = QueryConstants.SIZE) final int size,
            @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
            @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder, uriBuilder, response);
    }

    @GetMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE})
    @ResponseBody
    //    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public List<PatientDto> findAllPaginated(
            @RequestParam(value = QueryConstants.PAGE) final int page,
            @RequestParam(value = QueryConstants.SIZE) final int size,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findPaginatedInternal(page, size, uriBuilder, response);
    }

    @GetMapping(params = {QueryConstants.SORT_BY})
    @ResponseBody
    //    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public List<PatientDto> findAllSorted(
            @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
            @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findSortedInternal(sortBy, sortOrder);
    }

    @GetMapping(value = "/q")
    @ResponseBody
    public Iterable<Patient> findAllByWebQuerydsl(
            @QuerydslPredicate(root = Patient.class) Predicate predicate) {
        return patientRepository.findAll(predicate);
    }

    @GetMapping(value = "/search")
    @ResponseBody
    //    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public PatientDto findOneByName(@RequestParam("name") final String name) {
        return service.findByName(name);
    }

    // Spring

    @Override
    protected final IPatientService getService() {
        return service;
    }

}
