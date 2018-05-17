package io.office360.auth;

public final class Office360Constants {

    /**
     * Privileges <br/>
     * - note: the fact that these Privileges are prefixed with `ROLES` is a Spring convention (which can be overriden if needed)
     */
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASS = "adminpass";
    public static final String ADMIN_EMAIL = "admin@fake.com";

    public static final String USER_USERNAME = "user";
    public static final String USER_PASS = "userpass";
    public static final String USER_EMAIL = "user@fake.com";

    public static final String NAME = ADMIN_USERNAME;
    public static final String PASS = ADMIN_PASS;
    public static final String EMAIL = ADMIN_EMAIL;

    // privileges

    private Office360Constants() {
        throw new AssertionError();
    }

    public static final class Privileges {

        // Account
        public static final String CAN_USER_READ = "ROLE_USER_READ";
        public static final String CAN_USER_WRITE = "ROLE_USER_WRITE";

        // Role
        public static final String CAN_ROLE_READ = "ROLE_ROLE_READ";
        public static final String CAN_ROLE_WRITE = "ROLE_ROLE_WRITE";

        // Privilege
        public static final String CAN_PRIVILEGE_READ = "ROLE_PRIVILEGE_READ";
        public static final String CAN_PRIVILEGE_WRITE = "ROLE_PRIVILEGE_WRITE";

        // Patient Record Privilege
        public static final String CAN_PATIENT_RECORD_READ = "ROLE_PATIENT_RECORD_READ";
        public static final String CAN_PATIENT_RECORD_WRITE = "ROLE_PATIENT_RECORD_WRITE";

    }

    public static final class Roles {

        /**
         * A placeholder role for administrator.
         */
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_USER = "ROLE_ENDUSER";
        public static final String ROLE_DOCTOR = "ROLE_DOCTOR";
        public static final String ROLE_NURSE = "ROLE_NURSE";

    }

}
