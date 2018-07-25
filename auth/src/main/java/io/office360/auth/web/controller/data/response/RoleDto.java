package io.office360.auth.web.controller.data.response;

import java.util.Set;

public class RoleDto extends NamedBaseDto {

    private Set<PrivilegeDto> privileges;

    public RoleDto() {
        super();
    }

    public Set<PrivilegeDto> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<PrivilegeDto> privileges) {
        this.privileges = privileges;
    }
}
