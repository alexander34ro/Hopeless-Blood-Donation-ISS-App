package Persistence;

import javax.persistence.*;

@Entity
@Table(name = "Pacient", schema = "main", catalog = "")
public class PacientEntity {
    private short id;
    private String nume;
    private String prenume;
    private short prioritate;
    private MedicEntity medicByMedic;
    private short medic;
    private short cantitate;

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
    @Column(name = "prenume")
    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    @Basic
    @Column(name = "prioritate")
    public short getPrioritate() {
        return prioritate;
    }

    public void setPrioritate(short prioritate) {
        this.prioritate = prioritate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PacientEntity that = (PacientEntity) o;

        if (id != that.id) return false;
        if (prioritate != that.prioritate) return false;
        if (nume != null ? !nume.equals(that.nume) : that.nume != null) return false;
        if (prenume != null ? !prenume.equals(that.prenume) : that.prenume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (nume != null ? nume.hashCode() : 0);
        result = 31 * result + (prenume != null ? prenume.hashCode() : 0);
        result = 31 * result + (int) prioritate;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "medic", referencedColumnName = "id", nullable = false)
    public MedicEntity getMedicByMedic() {
        return medicByMedic;
    }

    public void setMedicByMedic(MedicEntity medicByMedic) {
        this.medicByMedic = medicByMedic;
    }

    @Basic
    @Column(name = "medic")
    public short getMedic() {
        return medic;
    }

    public void setMedic(short medic) {
        this.medic = medic;
    }

    @Basic
    @Column(name = "cantitate")
    public short getCantitate() {
        return cantitate;
    }

    public void setCantitate(short cantitate) {
        this.cantitate = cantitate;
    }
}
