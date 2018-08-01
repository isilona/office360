package io.office360.auth.web.privilege;

import com.google.common.base.MoreObjects;
import io.office360.auth.web.base.response.NamedBaseDto;

import java.util.Objects;

public class PrivilegeDto extends NamedBaseDto {

    private String description;

    public PrivilegeDto() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrivilegeDto)) return false;
        if (!super.equals(o)) return false;
        PrivilegeDto that = (PrivilegeDto) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), description);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("description", description)
                .add("id", id)
                .toString();
    }
}
