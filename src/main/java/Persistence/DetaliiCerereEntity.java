package Persistence;

import javax.persistence.*;

@Entity
@Table(name = "DetaliiCerere", schema = "main", catalog = "")
public class DetaliiCerereEntity implements java.io.Serializable {
    private short id;
    private String produsSange;
    private String tipSange;
    private String prioritate;
    private short completata;
    private String dataCompletare;
    private short cantitate;
    private CerereEntity cerereByCerere;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
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

    @Basic
    @Column(name = "prioritate")
    public String getPrioritate() {
        return prioritate;
    }

    public void setPrioritate(String prioritate) {
        this.prioritate = prioritate;
    }

    @Basic
    @Column(name = "completata")
    public short getCompletata() {
        return completata;
    }

    public void setCompletata(short completata) {
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
    @Column(name = "cantitate")
    public short getCantitate() {
        return cantitate;
    }

    public void setCantitate(short cantitate) {
        this.cantitate = cantitate;
    }

    public String getNumeSpital(){
        return cerereByCerere.getMedicByMedic().getSpitalBySpital().getNume();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetaliiCerereEntity that = (DetaliiCerereEntity) o;

        if (id != that.id) return false;
        if (completata != that.completata) return false;
        if (cantitate != that.cantitate) return false;
        if (produsSange != null ? !produsSange.equals(that.produsSange) : that.produsSange != null) return false;
        if (tipSange != null ? !tipSange.equals(that.tipSange) : that.tipSange != null) return false;
        if (prioritate != null ? !prioritate.equals(that.prioritate) : that.prioritate != null) return false;
        if (dataCompletare != null ? !dataCompletare.equals(that.dataCompletare) : that.dataCompletare != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (produsSange != null ? produsSange.hashCode() : 0);
        result = 31 * result + (tipSange != null ? tipSange.hashCode() : 0);
        result = 31 * result + (prioritate != null ? prioritate.hashCode() : 0);
        result = 31 * result + (int) completata;
        result = 31 * result + (dataCompletare != null ? dataCompletare.hashCode() : 0);
        result = 31 * result + (int) cantitate;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cerere", referencedColumnName = "id", nullable = false)
    public CerereEntity getCerereByCerere() {
        return cerereByCerere;
    }

    public void setCerereByCerere(CerereEntity cerereByCerere) {
        this.cerereByCerere = cerereByCerere;
    }
}
