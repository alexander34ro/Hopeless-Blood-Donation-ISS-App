package Persistence;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Pacient", schema = "main", catalog = "")
public class PacientEntity implements Serializable {
    private short id;
    private String nume;
    private String prenume;
    private short prioritate;
    private MedicEntity medicByMedic;
    private Short trombociteNecesare;
    private Short globuleRosiiNecesare;
    private Short plasmaNecesara;
    private Short sangeNecesar;

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
    @Column(name = "trombociteNecesare")
    public Short getTrombociteNecesare() {
        return trombociteNecesare;
    }

    public void setTrombociteNecesare(Short trombociteNecesare) {
        this.trombociteNecesare = trombociteNecesare;
    }

    @Basic
    @Column(name = "globuleRosiiNecesare")
    public Short getGlobuleRosiiNecesare() {
        return globuleRosiiNecesare;
    }

    public void setGlobuleRosiiNecesare(Short globuleRosiiNecesare) {
        this.globuleRosiiNecesare = globuleRosiiNecesare;
    }

    @Basic
    @Column(name = "plasmaNecesara")
    public Short getPlasmaNecesara() {
        return plasmaNecesara;
    }

    public void setPlasmaNecesara(Short plasmaNecesara) {
        this.plasmaNecesara = plasmaNecesara;
    }

    @Basic
    @Column(name = "sangeNecesar")
    public Short getSangeNecesar() {
        return sangeNecesar;
    }

    public void setSangeNecesar(Short sangeNecesar) {
        this.sangeNecesar = sangeNecesar;
    }
}
