package io.office360.auth.persistence.entity;

import io.office360.common.persistence.model.INameableEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedBaseEntity extends BaseEntity implements INameableEntity {

    @Column(unique = true, nullable = false)
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String nameToSet) {
        name = nameToSet;
    }
}
