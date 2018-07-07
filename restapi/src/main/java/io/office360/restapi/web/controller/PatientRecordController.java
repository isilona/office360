package io.office360.restapi.web.controller;

import com.querydsl.core.types.Predicate;
import io.office360.common.util.QueryConstants;
import io.office360.common.web.controller.AbstractController;
import io.office360.common.web.controller.ISortingController;
import io.office360.restapi.persistence.dao.IPatientJpaDao;
import io.office360.restapi.persistence.model.Patient;
import io.office360.restapi.service.IPatientService;
import io.office360.restapi.util.Office360Mappings;
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
public class PatientRecordController extends AbstractController<Patient> implements ISortingController<Patient> {

    private final IPatientService service;

    private final IPatientJpaDao patientRepository;

    @Autowired
    public PatientRecordController(IPatientService service, IPatientJpaDao patientRepository) {
        super(Patient.class);
        this.service = service;
        this.patientRepository = patientRepository;
    }

    // API

    @GetMapping(value = "/q")
    @ResponseBody
    public Iterable<Patient> findAllByWebQuerydsl(
            @QuerydslPredicate(root = Patient.class) Predicate predicate) {
        return patientRepository.findAll(predicate);
    }

    // find - all/paginated

    @Override
    @GetMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY})
    @ResponseBody
//    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public List<Patient> findAllPaginatedAndSorted(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
                                                   @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder, uriBuilder, response);
    }

    @Override
    @GetMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE})
    @ResponseBody
//    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public List<Patient> findAllPaginated(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findPaginatedInternal(page, size, uriBuilder, response);
    }

    @Override
    @GetMapping(params = {QueryConstants.SORT_BY})
    @ResponseBody
//    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public List<Patient> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    @GetMapping
    @ResponseBody
//    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public List<Patient> findAll(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findAllInternal(request, uriBuilder, response);
    }

    // find - one

    @GetMapping(value = "/{id}")
    @ResponseBody
//    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public Patient findOne(@PathVariable("id") final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findOneInternal(id, uriBuilder, response);
    }

    // find - one by name

    @GetMapping(value = "/search")
    @ResponseBody
//    @Secured(Privileges.CAN_PATIENT_RECORD_READ)
    public Patient findOneByName(@RequestParam("name") final String name) {
        return service.findByName(name);
    }

    // create

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final Patient resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        createInternal(resource, uriBuilder, response);
    }

    // update

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @Secured(Privileges.CAN_PATIENT_RECORD_WRITE)
    public void update(@PathVariable("id") final Long id, @RequestBody @Valid final Patient resource) {
        updateInternal(id, resource);
    }

    // delete

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @Secured(Privileges.CAN_PATIENT_RECORD_WRITE)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }

    // Spring

    @Override
    protected final IPatientService getService() {
        return service;
    }

}
