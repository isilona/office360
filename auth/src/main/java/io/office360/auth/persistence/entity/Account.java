package io.office360.auth.persistence.entity;

import com.google.common.base.MoreObjects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class Account extends NamedBaseEntity implements UserDetails {

    @Column(unique = true)
    private String username;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany( /* cascade = { CascadeType.REMOVE }, */fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}
    )
    private Set<Role> roles;

    @Column
    private boolean accountNonExpired;

    @Column
    private boolean accountNonLocked;

    @Column
    private boolean credentialsNonExpired;

    @Column
    private boolean enabled;

    public Account() {
        super();

        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public Account(final String nameToSet, final String passwordToSet, final Set<Role> rolesToSet) {
        super();

        name = nameToSet;
        password = passwordToSet;
        roles = rolesToSet;
    }

    public Account(final String usernameToSet, final String nameToSet, final String passwordToSet, final Set<Role> rolesToSet) {
        this();

        username = usernameToSet;
        name = nameToSet;
        password = passwordToSet;
        roles = rolesToSet;
    }

    // API

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String passwordToSet) {
        password = passwordToSet;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> rolesToSet) {
        roles = rolesToSet;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void grantAuthority(Role authority) {
        if (roles == null) roles = new HashSet<>();
        roles.add(authority);
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role ->
                role.getPrivileges().forEach(privilege ->
                        authorities.add(new SimpleGrantedAuthority(privilege.getName()))));
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNonExpired == account.accountNonExpired &&
                accountNonLocked == account.accountNonLocked &&
                credentialsNonExpired == account.credentialsNonExpired &&
                enabled == account.enabled &&
                Objects.equals(id, account.id) &&
                Objects.equals(username, account.username) &&
                Objects.equals(name, account.name) &&
                Objects.equals(email, account.email) &&
                Objects.equals(password, account.password) &&
                Objects.equals(roles, account.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, email, password, roles,
                accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("username", username)
                .add("name", name)
                .add("email", email)
                .add("password", password)
                .toString();
    }
}
