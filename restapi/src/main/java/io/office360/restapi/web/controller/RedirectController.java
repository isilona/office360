package io.office360.restapi.web.controller;

import io.office360.restapi.util.Office360Mappings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class RedirectController {

    public RedirectController() {
        super();
    }

    // API

    @RequestMapping(value = Office360Mappings.Singular.PRIVILEGE)
    public ResponseEntity<Void> privilegeToPrivileges(final HttpServletRequest request) {
        return singularToPlural(request);
    }

    @RequestMapping(value = Office360Mappings.Singular.ROLE)
    public ResponseEntity<Void> roleToRoles(final HttpServletRequest request) {
        return singularToPlural(request);
    }

    @RequestMapping(value = Office360Mappings.Singular.USER)
    public ResponseEntity<Void> userToUsers(final HttpServletRequest request) {
        return singularToPlural(request);
    }

    // util

    private final ResponseEntity<Void> singularToPlural(final HttpServletRequest request) {
        final String correctUri = request.getRequestURL().toString() + "s";
        final HttpHeaders responseHeaders = new org.springframework.http.HttpHeaders();
        responseHeaders.add(org.apache.http.HttpHeaders.LOCATION, correctUri);

        final ResponseEntity<Void> redirectResponse = new ResponseEntity<Void>(responseHeaders, HttpStatus.MOVED_PERMANENTLY);
        return redirectResponse;
    }

}
