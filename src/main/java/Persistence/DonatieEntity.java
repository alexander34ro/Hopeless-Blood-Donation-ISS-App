package Persistence;

import javax.persistence.*;

@Entity
@Table(name = "Donatie", schema = "main", catalog = "")
public class DonatieEntity implements java.io.Serializable {
    private short id;
    private short greutate;
    private short puls;
    private short tensiune;
    private short aSuferitInterventii;
    private short insarcinataLauzieMenstruatie;
    private short aConsumatAlcool;
    private String numePacient;
    private String stadiu;
    private short respins;
    private String data;
    private short aSuferitBoli;
    private short subTratament;
    private DonatorEntity donatorByDonator;
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
    @Column(name = "greutate")
    public short getGreutate() {
        return greutate;
    }

    public void setGreutate(short greutate) {
        this.greutate = greutate;
    }

    @Basic
    @Column(name = "puls")
    public short getPuls() {
        return puls;
    }

    public void setPuls(short puls) {
        this.puls = puls;
    }

    @Basic
    @Column(name = "tensiune")
    public short getTensiune() {
        return tensiune;
    }

    public void setTensiune(short tensiune) {
        this.tensiune = tensiune;
    }

    @Basic
    @Column(name = "aSuferitInterventii")
    public short getaSuferitInterventii() {
        return aSuferitInterventii;
    }

    public void setaSuferitInterventii(short aSuferitInterventii) {
        this.aSuferitInterventii = aSuferitInterventii;
    }

    @Basic
    @Column(name = "insarcinataLauzieMenstruatie")
    public short getInsarcinataLauzieMenstruatie() {
        return insarcinataLauzieMenstruatie;
    }

    public void setInsarcinataLauzieMenstruatie(short insarcinataLauzieMenstruatie) {
        this.insarcinataLauzieMenstruatie = insarcinataLauzieMenstruatie;
    }

    @Basic
    @Column(name = "aConsumatAlcool")
    public short getaConsumatAlcool() {
        return aConsumatAlcool;
    }

    public void setaConsumatAlcool(short aConsumatAlcool) {
        this.aConsumatAlcool = aConsumatAlcool;
    }

    @Basic
    @Column(name = "numePacient")
    public String getNumePacient() {
        return numePacient;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    @Basic
    @Column(name = "stadiu")
    public String getStadiu() {
        return stadiu;
    }

    public void setStadiu(String stadiu) {
        this.stadiu = stadiu;
    }

    @Basic
    @Column(name = "respins")
    public short getRespins() {
        return respins;
    }

    public void setRespins(short respins) {
        this.respins = respins;
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
    @Column(name = "aSuferitBoli")
    public short getaSuferitBoli() {
        return aSuferitBoli;
    }

    public void setaSuferitBoli(short aSuferitBoli) {
        this.aSuferitBoli = aSuferitBoli;
    }

    @Basic
    @Column(name = "subTratament")
    public short getSubTratament() {
        return subTratament;
    }

    public void setSubTratament(short subTratament) {
        this.subTratament = subTratament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DonatieEntity that = (DonatieEntity) o;

        if (id != that.id) return false;
        if (greutate != that.greutate) return false;
        if (puls != that.puls) return false;
        if (tensiune != that.tensiune) return false;
        if (aSuferitInterventii != that.aSuferitInterventii) return false;
        if (insarcinataLauzieMenstruatie != that.insarcinataLauzieMenstruatie) return false;
        if (aConsumatAlcool != that.aConsumatAlcool) return false;
        if (respins != that.respins) return false;
        if (aSuferitBoli != that.aSuferitBoli) return false;
        if (subTratament != that.subTratament) return false;
        if (numePacient != null ? !numePacient.equals(that.numePacient) : that.numePacient != null) return false;
        if (stadiu != null ? !stadiu.equals(that.stadiu) : that.stadiu != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (int) greutate;
        result = 31 * result + (int) puls;
        result = 31 * result + (int) tensiune;
        result = 31 * result + (int) aSuferitInterventii;
        result = 31 * result + (int) insarcinataLauzieMenstruatie;
        result = 31 * result + (int) aConsumatAlcool;
        result = 31 * result + (numePacient != null ? numePacient.hashCode() : 0);
        result = 31 * result + (stadiu != null ? stadiu.hashCode() : 0);
        result = 31 * result + (int) respins;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (int) aSuferitBoli;
        result = 31 * result + (int) subTratament;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "donator", referencedColumnName = "id", nullable = false)
    public DonatorEntity getDonatorByDonator() {
        return donatorByDonator;
    }

    public void setDonatorByDonator(DonatorEntity donatorByDonator) {
        this.donatorByDonator = donatorByDonator;
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
