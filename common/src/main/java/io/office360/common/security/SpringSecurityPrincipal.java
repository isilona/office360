package io.office360.common.security;

import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public final class SpringSecurityPrincipal extends User {

    private final String uuid;

    public SpringSecurityPrincipal(final String username, final String password, final boolean enabled, final Collection<? extends GrantedAuthority> authorities, final String uuidToSet) {
        super(username, password, enabled, true, true, true, authorities);

        uuid = uuidToSet;
    }

    // API

    public final String getUuid() {
        return uuid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpringSecurityPrincipal that = (SpringSecurityPrincipal) o;
        return Objects.equal(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), uuid);
    }

}

