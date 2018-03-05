package io.office360.restapi.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import io.office360.restapi.persistence.model.Privilege;
import io.office360.restapi.persistence.model.Role;
import io.office360.restapi.persistence.model.User;
import io.office360.restapi.util.Office360Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * This simple setup class will run during the bootstrap process of Spring and will create some setup data <br>
 * The main focus here is creating some standard privileges, then roles and finally some default principals/users
 */
@Component
public class SecuritySetup implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(SecuritySetup.class);

    private boolean setupDone;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPrivilegeService privilegeService;

    public SecuritySetup() {
        super();
    }

    //

    /**
     * - note that this is a compromise - the flag makes this bean statefull which can (and will) be avoided in the future by a more advanced mechanism <br>
     * - the reason for this is that the context is refreshed more than once throughout the lifecycle of the deployable <br>
     * - alternatives: proper persisted versioning
     */
    @Override
    public final void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!setupDone) {
            logger.info("Executing Setup");

            createPrivileges();
            createRoles();
            createUserss();

            setupDone = true;
            logger.info("Setup Done");
        }
    }

    // Privilege

    private void createPrivileges() {
        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_PRIVILEGE_READ);
        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_PRIVILEGE_WRITE);

        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_ROLE_READ);
        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_ROLE_WRITE);

        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_USER_READ);
        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_USER_WRITE);
    }

    final void createPrivilegeIfNotExisting(final String name) {
        final Privilege entityByName = privilegeService.findByName(name);
        if (entityByName == null) {
            final Privilege entity = new Privilege(name);
            privilegeService.create(entity);
        }
    }

    // Role

    private void createRoles() {
        final Privilege canPrivilegeRead = privilegeService.findByName(Office360Constants.Privileges.CAN_PRIVILEGE_READ);
        final Privilege canPrivilegeWrite = privilegeService.findByName(Office360Constants.Privileges.CAN_PRIVILEGE_WRITE);
        final Privilege canRoleRead = privilegeService.findByName(Office360Constants.Privileges.CAN_ROLE_READ);
        final Privilege canRoleWrite = privilegeService.findByName(Office360Constants.Privileges.CAN_ROLE_WRITE);
        final Privilege canUserRead = privilegeService.findByName(Office360Constants.Privileges.CAN_USER_READ);
        final Privilege canUserWrite = privilegeService.findByName(Office360Constants.Privileges.CAN_USER_WRITE);

        Preconditions.checkNotNull(canPrivilegeRead);
        Preconditions.checkNotNull(canPrivilegeWrite);
        Preconditions.checkNotNull(canRoleRead);
        Preconditions.checkNotNull(canRoleWrite);
        Preconditions.checkNotNull(canUserRead);
        Preconditions.checkNotNull(canUserWrite);

        createRoleIfNotExisting(Office360Constants.Roles.ROLE_ADMIN, Sets.<Privilege>newHashSet(canUserRead, canUserWrite, canRoleRead, canRoleWrite, canPrivilegeRead, canPrivilegeWrite));
    }

    final void createRoleIfNotExisting(final String name, final Set<Privilege> privileges) {
        final Role entityByName = roleService.findByName(name);
        if (entityByName == null) {
            final Role entity = new Role(name);
            entity.setPrivileges(privileges);
            roleService.create(entity);
        }
    }

    // User/User

    final void createUserss() {
        final Role roleAdmin = roleService.findByName(Office360Constants.Roles.ROLE_ADMIN);

        // createUserIfNotExisting(SecurityConstants.ADMIN_USERNAME, SecurityConstants.ADMIN_PASS, Sets.<Role> newHashSet(roleAdmin));
        createUserIfNotExisting(Office360Constants.ADMIN_EMAIL, Office360Constants.ADMIN_PASS, Sets.<Role>newHashSet(roleAdmin));
    }

    final void createUserIfNotExisting(final String loginName, final String pass, final Set<Role> roles) {
        final User entityByName = userService.findByName(loginName);
        if (entityByName == null) {
            final User entity = new User(loginName, pass, roles);
            userService.create(entity);
        }
    }

}
