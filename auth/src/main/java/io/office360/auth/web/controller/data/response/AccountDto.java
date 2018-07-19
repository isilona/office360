package io.office360.auth.web.controller.data.response;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class AccountDto extends BaseDto {


    private String email;

    private String password;

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
