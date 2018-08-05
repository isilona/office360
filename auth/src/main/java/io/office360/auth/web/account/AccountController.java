package io.office360.auth.web.account;

import io.office360.auth.persistence.entity.Account;
import io.office360.auth.service.IAccountService;
import io.office360.auth.util.Office360AuthMappings;
import io.office360.common.security.SpringSecurityUtil;
import io.office360.common.util.QueryConstants;
import io.office360.common.web.controller.AbstractController;
import io.office360.common.web.controller.IOperationsController;
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
@RequestMapping(value = Office360AuthMappings.USERS)
public class AccountController extends AbstractController implements IOperationsController {

    private final IAccountService service;

    @Autowired
    public AccountController(IAccountService service) {
        super(AccountDto.class);
        this.service = service;
    }

    // API

    // CREATE

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestBody @Valid final AccountRegisterDto resource,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        createInternal(resource, uriBuilder, response);
    }

    // READ

    @GetMapping(value = "/{id}")
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public AccountDto findOne(
            @PathVariable("id") final Long id,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findOneInternal(id, uriBuilder, response);
    }

    @GetMapping
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<AccountDto> findAll(
            final HttpServletRequest request,
            final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findAllInternal(request, uriBuilder, response);
    }

    // UPDATE

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured(Privileges.CAN_USER_WRITE)
    public void update(@PathVariable("id") final Long id, @RequestBody @Valid final AccountDto resource) {
        updateInternal(id, resource);
    }

    // DELETE

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured(Privileges.CAN_USER_WRITE)
    public void delete(@PathVariable("id") final Long id) {
        deleteInternal(id);
    }

    // API - PAGING & SORTING

    @GetMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY})
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<AccountDto> findAllPaginatedAndSorted(
            @RequestParam(value = QueryConstants.PAGE) final int page,
            @RequestParam(value = QueryConstants.SIZE) final int size,
            @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
            @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder,
            final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder, uriBuilder, response);
    }

    @GetMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE})
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<AccountDto> findAllPaginated(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findPaginatedInternal(page, size, uriBuilder, response);
    }

    @GetMapping(params = {QueryConstants.SORT_BY})
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<AccountDto> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findSortedInternal(sortBy, sortOrder);
    }


    // current


    @GetMapping(value = "/current")
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public AccountDto current() {
        final Account currentUser = (Account) SpringSecurityUtil.getCurrentUserDetails();
        if (currentUser == null) {
            return null;
        }
        return findOneInternal(currentUser.getId());
    }

    @RequestMapping("/user")
    public Account user(final Account user) {
        return user;
    }


    // Spring

    @Override
    protected final IAccountService getService() {
        return service;
    }

}
