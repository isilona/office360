package io.office360.auth.util;


import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import io.office360.auth.persistence.entity.Account;
import io.office360.auth.persistence.entity.Privilege;
import io.office360.auth.persistence.entity.Role;
import io.office360.auth.service.IAccountService;
import io.office360.auth.service.IPrivilegeService;
import io.office360.auth.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

import static io.office360.auth.util.Office360AuthConstants.Privileges;
import static io.office360.auth.util.Office360AuthConstants.Roles;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    private final IAccountService accountService;

    private final IRoleService roleService;

    private final IPrivilegeService privilegeService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CommandLineAppStartupRunner(
            IAccountService accountService,
            IRoleService roleService,
            IPrivilegeService privilegeService,
            PasswordEncoder passwordEncoder
    ) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (logger.isInfoEnabled()) {
            logger.info("Application started with command-line arguments: {} .", Arrays.toString(args));
            logger.info("To kill this application, press Ctrl + C.");
        }


        logger.info("Executing Setup");

        createPrivileges();
        createRoles();
        createUsers();

        logger.info("Setup Done");
    }

    private void createPrivileges() {

        createPrivilegeIfNotExisting(Privileges.CAN_PRIVILEGE_READ);
        createPrivilegeIfNotExisting(Privileges.CAN_PRIVILEGE_WRITE);

        createPrivilegeIfNotExisting(Privileges.CAN_ROLE_READ);
        createPrivilegeIfNotExisting(Privileges.CAN_ROLE_WRITE);

        createPrivilegeIfNotExisting(Privileges.CAN_USER_READ);
        createPrivilegeIfNotExisting(Privileges.CAN_USER_WRITE);

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
        final Privilege canPrivilegeRead = privilegeService.findByName(Privileges.CAN_PRIVILEGE_READ);
        final Privilege canPrivilegeWrite = privilegeService.findByName(Privileges.CAN_PRIVILEGE_WRITE);
        final Privilege canRoleRead = privilegeService.findByName(Privileges.CAN_ROLE_READ);
        final Privilege canRoleWrite = privilegeService.findByName(Privileges.CAN_ROLE_WRITE);
        final Privilege canUserRead = privilegeService.findByName(Privileges.CAN_USER_READ);
        final Privilege canUserWrite = privilegeService.findByName(Privileges.CAN_USER_WRITE);

        Preconditions.checkNotNull(canPrivilegeRead);
        Preconditions.checkNotNull(canPrivilegeWrite);
        Preconditions.checkNotNull(canRoleRead);
        Preconditions.checkNotNull(canRoleWrite);
        Preconditions.checkNotNull(canUserRead);
        Preconditions.checkNotNull(canUserWrite);

        createRoleIfNotExisting(Roles.ROLE_USER, Sets.newHashSet(canUserRead, canRoleRead, canPrivilegeRead));
        createRoleIfNotExisting(Roles.ROLE_ADMIN, Sets.newHashSet(canUserRead, canUserWrite, canRoleRead, canRoleWrite, canPrivilegeRead, canPrivilegeWrite));

    }

    final void createRoleIfNotExisting(final String name, final Set<Privilege> privileges) {
        final Role entityByName = roleService.findByName(name);
        if (entityByName == null) {
            final Role entity = new Role(name);
            entity.setPrivileges(privileges);
            roleService.create(entity);
        }
    }

    // Account/Account

    final void createUsers() {
        final Role roleAdmin = roleService.findByName(Roles.ROLE_ADMIN);
        final Role roleUser = roleService.findByName(Roles.ROLE_USER);

        createUserIfNotExisting(
                Office360AuthConstants.ADMIN_USERNAME,
                Office360AuthConstants.ADMIN_EMAIL,
                passwordEncoder.encode(Office360AuthConstants.ADMIN_PASS),
                Sets.newHashSet(roleAdmin));
        createUserIfNotExisting(
                Office360AuthConstants.USER_USERNAME,
                Office360AuthConstants.USER_EMAIL,
                passwordEncoder.encode(Office360AuthConstants.USER_PASS),
                Sets.newHashSet(roleUser));
    }

    final void createUserIfNotExisting(final String username, final String loginName, final String pass, final Set<Role> roles) {
        final Account entityByName = accountService.findByName(loginName);
        if (entityByName == null) {
            final Account entity = new Account(username, loginName, pass, roles);
            accountService.create(entity);
        }
    }
}
