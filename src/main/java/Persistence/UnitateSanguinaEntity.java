package Persistence;

import javax.persistence.*;

@Entity
@Table(name = "UnitateSanguina", schema = "main", catalog = "")
public class UnitateSanguinaEntity implements java.io.Serializable {
    private short id;
    private String categorie;
    private String tipSange;
    private String expiraLa;
    private CentruTransfuziiEntity centruTransfuziiByCentruTransfuzii;
    private short centruTransfuzii;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "categorie")
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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
    @Column(name = "expiraLa")
    public String getExpiraLa() {
        return expiraLa;
    }

    public void setExpiraLa(String expiraLa) {
        this.expiraLa = expiraLa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnitateSanguinaEntity that = (UnitateSanguinaEntity) o;

        if (id != that.id) return false;
        if (categorie != null ? !categorie.equals(that.categorie) : that.categorie != null) return false;
        if (tipSange != null ? !tipSange.equals(that.tipSange) : that.tipSange != null) return false;
        if (expiraLa != null ? !expiraLa.equals(that.expiraLa) : that.expiraLa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (categorie != null ? categorie.hashCode() : 0);
        result = 31 * result + (tipSange != null ? tipSange.hashCode() : 0);
        result = 31 * result + (expiraLa != null ? expiraLa.hashCode() : 0);
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

    @Basic
    @Column(name = "centruTransfuzii")
    public short getCentruTransfuzii() {
        return centruTransfuzii;
    }

    public void setCentruTransfuzii(short centruTransfuzii) {
        this.centruTransfuzii = centruTransfuzii;
    }
}
