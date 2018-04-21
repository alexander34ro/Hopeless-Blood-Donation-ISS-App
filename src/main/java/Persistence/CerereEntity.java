package Persistence;

import javax.persistence.*;

@Entity
@Table(name = "Cerere", schema = "main", catalog = "")
public class CerereEntity {
    private short id;
    private short trombociteNecesare;
    private short globuleRosiiNecesare;
    private short plasmaNecesara;
    private CentruTransfuziiEntity centruTransfuziiByCentruTransfuzii;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "trombociteNecesare")
    public short getTrombociteNecesare() {
        return trombociteNecesare;
    }

    public void setTrombociteNecesare(short trombociteNecesare) {
        this.trombociteNecesare = trombociteNecesare;
    }

    @Basic
    @Column(name = "globuleRosiiNecesare")
    public short getGlobuleRosiiNecesare() {
        return globuleRosiiNecesare;
    }

    public void setGlobuleRosiiNecesare(short globuleRosiiNecesare) {
        this.globuleRosiiNecesare = globuleRosiiNecesare;
    }

    @Basic
    @Column(name = "plasmaNecesara")
    public short getPlasmaNecesara() {
        return plasmaNecesara;
    }

    public void setPlasmaNecesara(short plasmaNecesara) {
        this.plasmaNecesara = plasmaNecesara;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CerereEntity that = (CerereEntity) o;

        if (id != that.id) return false;
        if (trombociteNecesare != that.trombociteNecesare) return false;
        if (globuleRosiiNecesare != that.globuleRosiiNecesare) return false;
        if (plasmaNecesara != that.plasmaNecesara) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (int) trombociteNecesare;
        result = 31 * result + (int) globuleRosiiNecesare;
        result = 31 * result + (int) plasmaNecesara;
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
}
