package io.office360.restapi.web.controller.data.response;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.office360.common.interfaces.IDto;

public class PatientDto implements IDto {

    private Long id;

    private String description;


    public PatientDto() {
        super();
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        if (o == null || getClass() != o.getClass()) return false;
        PatientDto that = (PatientDto) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, description);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("description", description)
                .toString();
    }
}
