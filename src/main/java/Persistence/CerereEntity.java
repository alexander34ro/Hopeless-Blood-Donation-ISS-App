package Persistence;

import javax.persistence.*;

@Entity
@Table(name = "Cerere", schema = "main", catalog = "")
public class CerereEntity {
    private short id;
    private String data;
    private Short cantitate;
    private Short completata;
    private String dataCompletare;
    private Short prioritate;
    private String produsSange;
    private String tipSange;
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

    @Basic
    @Column(name = "cantitate")
    public Short getCantitate() {
        return cantitate;
    }

    public void setCantitate(Short cantitate) {
        this.cantitate = cantitate;
    }

    @Basic
    @Column(name = "completata")
    public Short getCompletata() {
        return completata;
    }

    public void setCompletata(Short completata) {
        this.completata = completata;
    }

    @Basic
    @Column(name = "dataCompletare")
    public String getDataCompletare() {
        return dataCompletare;
    }

    public void setDataCompletare(String dataCompletare) {
        this.dataCompletare = dataCompletare;
    }

    @Basic
    @Column(name = "prioritate")
    public Short getPrioritate() {
        return prioritate;
    }

    public void setPrioritate(Short prioritate) {
        this.prioritate = prioritate;
    }

    @Basic
    @Column(name = "produsSange")
    public String getProdusSange() {
        return produsSange;
    }

    public void setProdusSange(String produsSange) {
        this.produsSange = produsSange;
    }

    @Basic
    @Column(name = "tipSange")
    public String getTipSange() {
        return tipSange;
    }

    public void setTipSange(String tipSange) {
        this.tipSange = tipSange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CerereEntity that = (CerereEntity) o;

        if (id != that.id) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (cantitate != null ? !cantitate.equals(that.cantitate) : that.cantitate != null) return false;
        if (completata != null ? !completata.equals(that.completata) : that.completata != null) return false;
        if (dataCompletare != null ? !dataCompletare.equals(that.dataCompletare) : that.dataCompletare != null)
            return false;
        if (prioritate != null ? !prioritate.equals(that.prioritate) : that.prioritate != null) return false;
        if (produsSange != null ? !produsSange.equals(that.produsSange) : that.produsSange != null) return false;
        if (tipSange != null ? !tipSange.equals(that.tipSange) : that.tipSange != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (cantitate != null ? cantitate.hashCode() : 0);
        result = 31 * result + (completata != null ? completata.hashCode() : 0);
        result = 31 * result + (dataCompletare != null ? dataCompletare.hashCode() : 0);
        result = 31 * result + (prioritate != null ? prioritate.hashCode() : 0);
        result = 31 * result + (produsSange != null ? produsSange.hashCode() : 0);
        result = 31 * result + (tipSange != null ? tipSange.hashCode() : 0);
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
