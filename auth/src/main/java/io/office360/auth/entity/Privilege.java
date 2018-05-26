package io.office360.auth.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Privilege extends NamedBaseEntity {


    @Column
    private String description;

    public Privilege() {
        super();
    }

    public Privilege(final String nameToSet) {
        super();
        name = nameToSet;
    }

    // API

    public String getDescription() {
        return description;
    }

    public void setDescription(final String descriptionToSet) {
        description = descriptionToSet;
    }

    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privilege privilege = (Privilege) o;
        return Objects.equals(id, privilege.id) &&
                Objects.equals(name, privilege.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }
}
