package Persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "CentruTransfuzii", schema = "main", catalog = "")
public class CentruTransfuziiEntity implements Serializable {
    private short id;
    private String nume;
    private String regiune;
    private String oras;
    private Collection<AsistentEntity> asistentsById;
    private Collection<CerereEntity> cereresById;
    private Collection<DonatieEntity> donatiesById;
    private Collection<UnitateSanguinaEntity> unitateSanguinasById;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nume")
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Basic
    @Column(name = "regiune")
    public String getRegiune() {
        return regiune;
    }

    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }

    @Basic
    @Column(name = "oras")
    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CentruTransfuziiEntity that = (CentruTransfuziiEntity) o;

        if (id != that.id) return false;
        if (nume != null ? !nume.equals(that.nume) : that.nume != null) return false;
        if (regiune != null ? !regiune.equals(that.regiune) : that.regiune != null) return false;
        if (oras != null ? !oras.equals(that.oras) : that.oras != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (nume != null ? nume.hashCode() : 0);
        result = 31 * result + (regiune != null ? regiune.hashCode() : 0);
        result = 31 * result + (oras != null ? oras.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "centruTransfuziiByCentruTransfuzii")
    public Collection<AsistentEntity> getAsistentsById() {
        return asistentsById;
    }

    public void setAsistentsById(Collection<AsistentEntity> asistentsById) {
        this.asistentsById = asistentsById;
    }

    @OneToMany(mappedBy = "centruTransfuziiByCentruTransfuzii")
    public Collection<CerereEntity> getCereresById() {
        return cereresById;
    }

    public void setCereresById(Collection<CerereEntity> cereresById) {
        this.cereresById = cereresById;
    }

    @OneToMany(mappedBy = "centruTransfuziiByCentruTransfuzii")
    public Collection<DonatieEntity> getDonatiesById() {
        return donatiesById;
    }

    public void setDonatiesById(Collection<DonatieEntity> donatiesById) {
        this.donatiesById = donatiesById;
    }

    @OneToMany(mappedBy = "centruTransfuziiByCentruTransfuzii")
    public Collection<UnitateSanguinaEntity> getUnitateSanguinasById() {
        return unitateSanguinasById;
    }

    public void setUnitateSanguinasById(Collection<UnitateSanguinaEntity> unitateSanguinasById) {
        this.unitateSanguinasById = unitateSanguinasById;
    }
}
