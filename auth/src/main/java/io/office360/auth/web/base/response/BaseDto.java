package io.office360.auth.web.base.response;

import io.office360.common.interfaces.IDto;

import java.util.Objects;

public class BaseDto implements IDto {

    protected Long id;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDto)) return false;
        BaseDto that = (BaseDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
