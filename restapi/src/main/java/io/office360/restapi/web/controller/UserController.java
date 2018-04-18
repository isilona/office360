package io.office360.restapi.web.controller;

import io.office360.common.security.SpringSecurityUtil;
import io.office360.common.util.QueryConstants;
import io.office360.common.web.controller.AbstractController;
import io.office360.common.web.controller.ISortingController;
import io.office360.restapi.persistence.model.Account;
import io.office360.restapi.service.IUserService;
import io.office360.restapi.util.Office360Mappings;
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

import static io.office360.restapi.util.Office360Constants.Privileges;

@Controller
@RequestMapping(value = Office360Mappings.USERS)
public class UserController extends AbstractController<Account> implements ISortingController<Account> {

    @Autowired
    private IUserService service;

    public UserController() {
        super(Account.class);
    }

    // API

    // find - all/paginated

    @Override
    @RequestMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY}, method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<Account> findAllPaginatedAndSorted(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
                                                   @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder, uriBuilder, response);
    }

    @Override
    @RequestMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE}, method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<Account> findAllPaginated(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findPaginatedInternal(page, size, uriBuilder, response);
    }

    @Override
    @RequestMapping(params = {QueryConstants.SORT_BY}, method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<Account> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<Account> findAll(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findAllInternal(request, uriBuilder, response);
    }

    // find - one

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public Account findOne(@PathVariable("id") final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findOneInternal(id, uriBuilder, response);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public Account current() {
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

    // create

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final Account resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        createInternal(resource, uriBuilder, response);
    }

    // update

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Secured(Privileges.CAN_USER_WRITE)
    public void update(@PathVariable("id") final Long id, @RequestBody @Valid final Account resource) {
        updateInternal(id, resource);
    }

    // delete

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured(Privileges.CAN_USER_WRITE)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }

    // Spring

    @Override
    protected final IUserService getService() {
        return service;
    }

}
