package Persistence;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Cerere", schema = "main", catalog = "")
public class CerereEntity implements java.io.Serializable {
    private short id;
    private short centruTransfuzii;
    private short medic;
    private String data;
    private CentruTransfuziiEntity centruTransfuziiByCentruTransfuzii;
    private MedicEntity medicByMedic;
    private PacientEntity pacientByMedic;
    private Collection<DetaliiCerereEntity> detaliiCereresById;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CerereEntity that = (CerereEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        return result;
    }

    @Basic
    @Column(name = "centruTransfuzii")
    public short getCentruTransfuzii() {
        return centruTransfuzii;
    }

    public void setCentruTransfuzii(short centruTransfuzii) {
        this.centruTransfuzii = centruTransfuzii;
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
    @Column(name = "data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @ManyToOne
    @JoinColumn(name = "centruTransfuzii", referencedColumnName = "id", nullable = false)
    public CentruTransfuziiEntity getCentruTransfuziiByCentruTransfuzii() {
        return centruTransfuziiByCentruTransfuzii;
    }

    public void setCentruTransfuziiByCentruTransfuzii(CentruTransfuziiEntity centruTransfuziiByCentruTransfuzii) {
        this.centruTransfuziiByCentruTransfuzii = centruTransfuziiByCentruTransfuzii;
    }

    @ManyToOne
    @JoinColumn(name = "medic", referencedColumnName = "id", nullable = false)
    public MedicEntity getMedicByMedic() {
        return medicByMedic;
    }

    public void setMedicByMedic(MedicEntity medicByMedic) {
        this.medicByMedic = medicByMedic;
    }

    @ManyToOne
    @JoinColumn(name = "medic", referencedColumnName = "id", nullable = false)
    public PacientEntity getPacientByMedic() {
        return pacientByMedic;
    }

    public void setPacientByMedic(PacientEntity pacientByMedic) {
        this.pacientByMedic = pacientByMedic;
    }

    @OneToMany(mappedBy = "cerereByCerere")
    public Collection<DetaliiCerereEntity> getDetaliiCereresById() {
        return detaliiCereresById;
    }

    public void setDetaliiCereresById(Collection<DetaliiCerereEntity> detaliiCereresById) {
        this.detaliiCereresById = detaliiCereresById;
    }
}
