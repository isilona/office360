package io.office360.auth.util;

public final class Office360AuthMappings {

    public static final String USERS = "users";
    public static final String PRIVILEGES = "privileges";
    public static final String ROLES = "roles";
    public static final String PATIENTS = "patients";

    // discoverability
    public static final String AUTHENTICATION = "authentication";

    private Office360AuthMappings() {
        throw new AssertionError();
    }

    public static final class Singular {

        public static final String USER = "user";
        public static final String PRIVILEGE = "privilege";
        public static final String ROLE = "role";
        public static final String PATIENT = "patient";

    }


    // API

}
