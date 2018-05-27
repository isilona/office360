package io.office360.auth.persistence.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role extends NamedBaseEntity {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRIV_ID", referencedColumnName = "ID")}
    )
    private Set<Privilege> privileges;


    public Role() {
        super();
    }

    public Role(final String nameToSet) {
        super();
        name = nameToSet;
    }

    public Role(final String nameToSet, final Set<Privilege> privilegesToSet) {
        super();
        name = nameToSet;
        privileges = privilegesToSet;
    }

    // API

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(final Set<Privilege> privilegesToSet) {
        privileges = privilegesToSet;
    }

    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name) &&
                Objects.equals(privileges, role.privileges);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, privileges);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("privileges", privileges)
                .toString();
    }
}
