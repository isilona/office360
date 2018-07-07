package io.office360.auth.web.controller;

import com.google.common.collect.Sets;
import io.office360.auth.persistence.entity.Account;
import io.office360.auth.persistence.entity.Privilege;
import io.office360.auth.persistence.entity.Role;
import io.office360.auth.util.Office360AuthMappings;
import io.office360.common.security.SpringSecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.stream.Collectors;


/**
 * - note: this controller will start working with the User model and, if necessary, will move to a Authentication resource (which is the way it should work)
 */
@Controller
public class AuthenticationController {

    public AuthenticationController() {
        super();
    }

    // API

    @GetMapping(value = Office360AuthMappings.AUTHENTICATION)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Account createAuthentication() {
        final Authentication authenticationInSpring = SpringSecurityUtil.getCurrentAuthentication();

        Collection<Privilege> privileges = authenticationInSpring.getAuthorities().stream().map(authority ->
                new Privilege(authority.getAuthority())
        ).collect(Collectors.toList());

        final Role defaultRole = new Role("defaultRole", Sets.<Privilege>newHashSet(privileges));

        return new Account(authenticationInSpring.getName(),
                (String) authenticationInSpring.getCredentials(),
                Sets.<Role>newHashSet(defaultRole)
        );
    }

}
