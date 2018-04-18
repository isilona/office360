package io.office360.restapi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public final class Office360User extends User {

    private long id;

    public Office360User(final String username, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Office360User(final String name, final String password, final List<GrantedAuthority> auths, final long userId) {
        super(name, password, auths);

        id = userId;
    }

    // API

    public final long getId() {
        return id;
    }

    public final void setId(final long id) {
        this.id = id;
    }

}
