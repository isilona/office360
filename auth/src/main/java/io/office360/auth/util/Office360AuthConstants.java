package io.office360.auth.util;

public final class Office360AuthConstants {

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


    private Office360AuthConstants() {
        throw new IllegalStateException();
    }

    public static final class Token {

        public static final String FILE_NAME = "ms-auth.jks";
        public static final String FILE_PASSWORD = "ms-auth-pass";
        public static final String KEY_PAIR_ALIAS = "ms-auth";
        public static final Long ACCESS_TOKEN_VALIDITY_1_DAY = Long.valueOf(86400);
        public static final Long REFRESH_TOKEN_VALIDITY = Long.valueOf(2592000);
        public static final String CLIENT_ID = "trusted-app";
    }

    // privileges
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

        private Privileges() {
            throw new IllegalStateException();
        }

    }

    public static final class Roles {

        /**
         * A placeholder role for administrator.
         */
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_USER = "ROLE_ENDUSER";

        private Roles() {
            throw new IllegalStateException();
        }
    }
}
