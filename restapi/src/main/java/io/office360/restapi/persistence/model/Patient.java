package io.office360.restapi.persistence.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.office360.common.interfaces.INameableDto;
import io.office360.common.persistence.model.INameableEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Patient implements INameableEntity, INameableDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String diagnosis;

    private Date entryDate;

    private Date exitDate;

    //

    public Patient() {
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

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equal(id, patient.id) &&
                Objects.equal(name, patient.name) &&
                Objects.equal(diagnosis, patient.diagnosis) &&
                Objects.equal(entryDate, patient.entryDate) &&
                Objects.equal(exitDate, patient.exitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, diagnosis, entryDate, exitDate);
    }

    //

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("diagnosis", diagnosis)
                .add("entryDate", entryDate)
                .add("exitDate", exitDate)
                .toString();
    }
}
