package io.office360.restapi.persistence.setup;


import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import io.office360.restapi.persistence.model.Account;
import io.office360.restapi.persistence.model.Patient;
import io.office360.restapi.persistence.model.Privilege;
import io.office360.restapi.persistence.model.Role;
import io.office360.restapi.service.IPatientService;
import io.office360.restapi.service.IPrivilegeService;
import io.office360.restapi.service.IRoleService;
import io.office360.restapi.service.IUserService;
import io.office360.restapi.util.Office360Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPrivilegeService privilegeService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));


        logger.info("Executing Setup");

        createPrivileges();
        createRoles();
        createUsers();
        createPatientRecords();

        logger.info("Setup Done");
    }

    private void createPrivileges() {
        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_PATIENT_RECORD_READ);
        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_PATIENT_RECORD_WRITE);

        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_PRIVILEGE_READ);
        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_PRIVILEGE_WRITE);

        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_ROLE_READ);
        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_ROLE_WRITE);

        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_USER_READ);
        createPrivilegeIfNotExisting(Office360Constants.Privileges.CAN_USER_WRITE);

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
        final Privilege canPatientRecordRead = privilegeService.findByName(Office360Constants.Privileges.CAN_PATIENT_RECORD_READ);
        final Privilege canPatientRecordWrite = privilegeService.findByName(Office360Constants.Privileges.CAN_PATIENT_RECORD_WRITE);
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

        createRoleIfNotExisting(Office360Constants.Roles.ROLE_USER, Sets.<Privilege>newHashSet(canUserRead, canRoleRead, canPrivilegeRead));
        createRoleIfNotExisting(Office360Constants.Roles.ROLE_ADMIN, Sets.<Privilege>newHashSet(canUserRead, canUserWrite, canRoleRead, canRoleWrite, canPrivilegeRead, canPrivilegeWrite));
        createRoleIfNotExisting(Office360Constants.Roles.ROLE_NURSE, Sets.<Privilege>newHashSet(canPatientRecordRead));
        createRoleIfNotExisting(Office360Constants.Roles.ROLE_DOCTOR, Sets.<Privilege>newHashSet(canPatientRecordRead, canPatientRecordWrite));

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
        final Role roleAdmin = roleService.findByName(Office360Constants.Roles.ROLE_ADMIN);
        final Role roleUser = roleService.findByName(Office360Constants.Roles.ROLE_USER);
        final Role roleDoctor = roleService.findByName(Office360Constants.Roles.ROLE_DOCTOR);
        final Role roleNurse = roleService.findByName(Office360Constants.Roles.ROLE_NURSE);

        createUserIfNotExisting(Office360Constants.ADMIN_EMAIL, passwordEncoder.encode(Office360Constants.ADMIN_PASS), Sets.<Role>newHashSet(roleAdmin, roleDoctor));
        createUserIfNotExisting(Office360Constants.USER_EMAIL, passwordEncoder.encode(Office360Constants.USER_PASS), Sets.<Role>newHashSet(roleUser, roleNurse));
    }

    final void createUserIfNotExisting(final String loginName, final String pass, final Set<Role> roles) {
        final Account entityByName = userService.findByName(loginName);
        if (entityByName == null) {
            final Account entity = new Account(loginName, pass, roles);
            userService.create(entity);
        }
    }

    // patient records
    public void createPatientRecords() {
        createPatientRecordIfNotexist("john");
        createPatientRecordIfNotexist("jane");
    }

    void createPatientRecordIfNotexist(String name) {
        final Patient entity = new Patient();
        entity.setName(name);

        if (patientService.findByName(name) == null) {
            patientService.create(entity);
        }
    }
}