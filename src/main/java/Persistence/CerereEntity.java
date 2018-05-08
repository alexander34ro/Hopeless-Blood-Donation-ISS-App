package Persistence;

import javax.persistence.*;

@Entity
@Table(name = "Cerere", schema = "main", catalog = "")
public class CerereEntity {
    private short id;
    private String data;
    private CentruTransfuziiEntity centruTransfuziiByCentruTransfuzii;
    private MedicEntity medicByMedic;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CerereEntity that = (CerereEntity) o;

        if (id != that.id) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
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
}
