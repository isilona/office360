package io.office360.auth.web.controller.data.response;

import java.util.Objects;

public class NamedBaseDto extends BaseDto {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedBaseDto)) return false;
        if (!super.equals(o)) return false;
        NamedBaseDto that = (NamedBaseDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name);
    }
}
