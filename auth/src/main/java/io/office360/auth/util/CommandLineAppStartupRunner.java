package io.office360.auth.util;


import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import io.office360.auth.persistence.entity.Account;
import io.office360.auth.persistence.entity.Privilege;
import io.office360.auth.persistence.entity.Role;
import io.office360.auth.service.IAccountService;
import io.office360.auth.service.IPrivilegeService;
import io.office360.auth.service.IRoleService;
import io.office360.auth.web.account.AccountDto;
import io.office360.auth.web.account.AccountMapper;
import io.office360.auth.web.privilege.PrivilegeDto;
import io.office360.auth.web.privilege.PrivilegeMapper;
import io.office360.auth.web.role.RoleDto;
import io.office360.auth.web.role.RoleMapper;
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

    private final AccountMapper accountMapper;

    private final PrivilegeMapper privilegeMapper;

    private final RoleMapper roleMapper;

    @Autowired
    public CommandLineAppStartupRunner(
            IAccountService accountService,
            IRoleService roleService,
            IPrivilegeService privilegeService,
            PasswordEncoder passwordEncoder,
            AccountMapper accountMapper,
            PrivilegeMapper privilegeMapper,
            RoleMapper roleMapper) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
        this.passwordEncoder = passwordEncoder;
        this.accountMapper = accountMapper;
        this.privilegeMapper = privilegeMapper;
        this.roleMapper = roleMapper;
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
        final PrivilegeDto entityByName = privilegeService.findByName(name);
        if (entityByName == null) {
            final Privilege entity = new Privilege(name);
            PrivilegeDto dto = privilegeMapper.entityToDto(entity);
            privilegeService.create(dto);
        }
    }

    // Role

    private void createRoles() {
        final PrivilegeDto canPrivilegeRead = privilegeService.findByName(Privileges.CAN_PRIVILEGE_READ);
        final PrivilegeDto canPrivilegeWrite = privilegeService.findByName(Privileges.CAN_PRIVILEGE_WRITE);
        final PrivilegeDto canRoleRead = privilegeService.findByName(Privileges.CAN_ROLE_READ);
        final PrivilegeDto canRoleWrite = privilegeService.findByName(Privileges.CAN_ROLE_WRITE);
        final PrivilegeDto canUserRead = privilegeService.findByName(Privileges.CAN_USER_READ);
        final PrivilegeDto canUserWrite = privilegeService.findByName(Privileges.CAN_USER_WRITE);

        Preconditions.checkNotNull(canPrivilegeRead);
        Preconditions.checkNotNull(canPrivilegeWrite);
        Preconditions.checkNotNull(canRoleRead);
        Preconditions.checkNotNull(canRoleWrite);
        Preconditions.checkNotNull(canUserRead);
        Preconditions.checkNotNull(canUserWrite);

        createRoleIfNotExisting(
                Roles.ROLE_USER,
                Sets.newHashSet(
                        privilegeMapper.dtoToEntity(canUserRead),
                        privilegeMapper.dtoToEntity(canRoleRead),
                        privilegeMapper.dtoToEntity(canPrivilegeRead))
        );
        createRoleIfNotExisting(
                Roles.ROLE_ADMIN,
                Sets.newHashSet(
                        privilegeMapper.dtoToEntity(canUserRead),
                        privilegeMapper.dtoToEntity(canUserWrite),
                        privilegeMapper.dtoToEntity(canRoleRead),
                        privilegeMapper.dtoToEntity(canRoleWrite),
                        privilegeMapper.dtoToEntity(canPrivilegeRead),
                        privilegeMapper.dtoToEntity(canPrivilegeWrite)));

    }

    final void createRoleIfNotExisting(final String name, final Set<Privilege> privileges) {
        final RoleDto entityByName = roleService.findByName(name);
        if (entityByName == null) {
            final Role entity = new Role(name);
            entity.setPrivileges(privileges);
            RoleDto dto = roleMapper.entityToDto(entity);
            roleService.create(dto);
        }
    }

    // Account/Account

    final void createUsers() {
        final RoleDto roleAdmin = roleService.findByName(Roles.ROLE_ADMIN);
        final RoleDto roleUser = roleService.findByName(Roles.ROLE_USER);

        createUserIfNotExisting(
                Office360AuthConstants.ADMIN_USERNAME,
                passwordEncoder.encode(Office360AuthConstants.ADMIN_PASS),
                Office360AuthConstants.ADMIN_EMAIL,
                Sets.newHashSet(roleMapper.dtoToEntity(roleAdmin)));
        createUserIfNotExisting(
                Office360AuthConstants.USER_USERNAME,
                passwordEncoder.encode(Office360AuthConstants.USER_PASS),
                Office360AuthConstants.USER_EMAIL,
                Sets.newHashSet(roleMapper.dtoToEntity(roleUser)));
    }

    final void createUserIfNotExisting(final String username, final String pass, final String email, final Set<Role> roles) {
        final AccountDto entityByName = accountService.findByUsername(username);
        if (entityByName == null) {
            final Account entity =
                    new Account.Builder(username, pass).
                            setEmail(email).
                            setRoles(roles).
                            setName(username).
                            build();
            AccountDto dto = accountMapper.entityToDto(entity);
            accountService.create(dto);
        }
    }
}
