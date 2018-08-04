package io.office360.auth.web.role;

import io.office360.auth.web.privilege.PrivilegeDto;
import io.office360.common.web.response.NamedBaseDto;

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
