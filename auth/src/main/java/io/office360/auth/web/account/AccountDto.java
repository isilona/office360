package io.office360.auth.web.account;

import com.google.common.base.MoreObjects;
import io.office360.auth.web.base.response.NamedBaseDto;
import io.office360.auth.web.role.RoleDto;

import java.util.Objects;
import java.util.Set;

public class AccountDto extends NamedBaseDto {

    private String email;

    private String password;

    private Set<RoleDto> roles;

    public AccountDto() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDto)) return false;
        if (!super.equals(o)) return false;
        AccountDto that = (AccountDto) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), email, password);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("email", email)
                .add("id", id)
                .toString();
    }
}
