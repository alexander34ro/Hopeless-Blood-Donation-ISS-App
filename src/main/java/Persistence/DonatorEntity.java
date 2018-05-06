package Persistence;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Donator", schema = "main", catalog = "")
public class DonatorEntity implements java.io.Serializable {
    private short id;
    private String username;
    private String parola;
    private String nume;
    private String prenume;
    private String email;
    private String telefon;
    private String dataNastere;
    private String oras;
    private String regiune;
    private String adresa;
    private String orasResedintegera;
    private String regiuneResedintegera;
    private String adresaResedintegera;
    private String tipSange;
    private Collection<DonatieEntity> donatiesById;
    private Collection<NotificareEntity> notificaresById;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "parola")
    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "telefon")
    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Basic
    @Column(name = "dataNastere")
    public String getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(String dataNastere) {
        this.dataNastere = dataNastere;
    }

    @Basic
    @Column(name = "oras")
    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    @Basic
    @Column(name = "regiune")
    public String getRegiune() {
        return regiune;
    }

    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }

    @Basic
    @Column(name = "adresa")
    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Basic
    @Column(name = "orasResedintegera")
    public String getOrasResedintegera() {
        return orasResedintegera;
    }

    public void setOrasResedintegera(String orasResedintegera) {
        this.orasResedintegera = orasResedintegera;
    }

    @Basic
    @Column(name = "regiuneResedintegera")
    public String getRegiuneResedintegera() {
        return regiuneResedintegera;
    }

    public void setRegiuneResedintegera(String regiuneResedintegera) {
        this.regiuneResedintegera = regiuneResedintegera;
    }

    @Basic
    @Column(name = "adresaResedintegera")
    public String getAdresaResedintegera() {
        return adresaResedintegera;
    }

    public void setAdresaResedintegera(String adresaResedintegera) {
        this.adresaResedintegera = adresaResedintegera;
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

        DonatorEntity that = (DonatorEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (parola != null ? !parola.equals(that.parola) : that.parola != null) return false;
        if (nume != null ? !nume.equals(that.nume) : that.nume != null) return false;
        if (prenume != null ? !prenume.equals(that.prenume) : that.prenume != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (telefon != null ? !telefon.equals(that.telefon) : that.telefon != null) return false;
        if (dataNastere != null ? !dataNastere.equals(that.dataNastere) : that.dataNastere != null) return false;
        if (oras != null ? !oras.equals(that.oras) : that.oras != null) return false;
        if (regiune != null ? !regiune.equals(that.regiune) : that.regiune != null) return false;
        if (adresa != null ? !adresa.equals(that.adresa) : that.adresa != null) return false;
        if (orasResedintegera != null ? !orasResedintegera.equals(that.orasResedintegera) : that.orasResedintegera != null)
            return false;
        if (regiuneResedintegera != null ? !regiuneResedintegera.equals(that.regiuneResedintegera) : that.regiuneResedintegera != null)
            return false;
        if (adresaResedintegera != null ? !adresaResedintegera.equals(that.adresaResedintegera) : that.adresaResedintegera != null)
            return false;
        if (tipSange != null ? !tipSange.equals(that.tipSange) : that.tipSange != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (parola != null ? parola.hashCode() : 0);
        result = 31 * result + (nume != null ? nume.hashCode() : 0);
        result = 31 * result + (prenume != null ? prenume.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telefon != null ? telefon.hashCode() : 0);
        result = 31 * result + (dataNastere != null ? dataNastere.hashCode() : 0);
        result = 31 * result + (oras != null ? oras.hashCode() : 0);
        result = 31 * result + (regiune != null ? regiune.hashCode() : 0);
        result = 31 * result + (adresa != null ? adresa.hashCode() : 0);
        result = 31 * result + (orasResedintegera != null ? orasResedintegera.hashCode() : 0);
        result = 31 * result + (regiuneResedintegera != null ? regiuneResedintegera.hashCode() : 0);
        result = 31 * result + (adresaResedintegera != null ? adresaResedintegera.hashCode() : 0);
        result = 31 * result + (tipSange != null ? tipSange.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "donatorByDonator")
    public Collection<DonatieEntity> getDonatiesById() {
        return donatiesById;
    }

    public void setDonatiesById(Collection<DonatieEntity> donatiesById) {
        this.donatiesById = donatiesById;
    }

    @OneToMany(mappedBy = "donatorByDonator")
    public Collection<NotificareEntity> getNotificaresById() {
        return notificaresById;
    }

    public void setNotificaresById(Collection<NotificareEntity> notificaresById) {
        this.notificaresById = notificaresById;
    }
}
