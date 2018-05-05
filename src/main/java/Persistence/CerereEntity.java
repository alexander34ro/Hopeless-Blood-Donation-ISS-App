package Persistence;

import javax.persistence.*;

@Entity
@Table(name = "Cerere", schema = "main", catalog = "")
public class CerereEntity {
    private short id;
    private short centruTransfuzii;
    private short medic;
    private String data;

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
}
