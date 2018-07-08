package io.office360.auth.web.controller;

import io.office360.auth.persistence.entity.Role;
import io.office360.auth.service.IRoleService;
import io.office360.auth.util.Office360AuthMappings;
import io.office360.common.util.QueryConstants;
import io.office360.common.web.controller.AbstractController;
import io.office360.common.web.controller.ISortingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static io.office360.auth.util.Office360AuthConstants.Privileges;

@Controller
@RequestMapping(value = Office360AuthMappings.ROLES)
public class RoleController extends AbstractController<Role> implements ISortingController<Role> {

    private final IRoleService service;

    @Autowired
    public RoleController(IRoleService service) {
        super(Role.class);
        this.service = service;
    }

    // API

    // find - all/paginated

    @Override
    @GetMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY})
    @ResponseBody
    @Secured(Privileges.CAN_ROLE_READ)
    public List<Role> findAllPaginatedAndSorted(
            @RequestParam(value = QueryConstants.PAGE) final int page,
            @RequestParam(value = QueryConstants.SIZE) final int size,
            @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
            @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder, uriBuilder, response);
    }

    @Override
    @GetMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE})
    @ResponseBody
    @Secured(Privileges.CAN_ROLE_READ)
    public List<Role> findAllPaginated(
            @RequestParam(value = QueryConstants.PAGE) final int page,
            @RequestParam(value = QueryConstants.SIZE) final int size,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findPaginatedInternal(page, size, uriBuilder, response);
    }

    @Override
    @GetMapping(params = {QueryConstants.SORT_BY})
    @ResponseBody
    @Secured(Privileges.CAN_ROLE_READ)
    public List<Role> findAllSorted(
            @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
            @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    @GetMapping
    @ResponseBody
    @Secured(Privileges.CAN_ROLE_READ)
    public List<Role> findAll(
            final HttpServletRequest request,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findAllInternal(request, uriBuilder, response);
    }

    // find - one

    @GetMapping(value = "/{id}")
    @ResponseBody
    @Secured(Privileges.CAN_ROLE_READ)
    public Role findOne(
            @PathVariable("id") final Long id,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findOneInternal(id, uriBuilder, response);
    }

    // create

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured(Privileges.CAN_ROLE_WRITE)
    public void create(
            @RequestBody @Valid final Role resource,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        createInternal(resource, uriBuilder, response);
    }

    // update

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured(Privileges.CAN_ROLE_WRITE)
    public void update(
            @PathVariable("id") final Long id,
            @RequestBody @Valid final Role resource) {
        updateInternal(id, resource);
    }

    // delete

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured(Privileges.CAN_ROLE_WRITE)
    public void delete(
            @PathVariable("id") final Long id) {
        deleteInternal(id);
    }

    // Spring

    @Override
    protected final IRoleService getService() {
        return service;
    }

}

