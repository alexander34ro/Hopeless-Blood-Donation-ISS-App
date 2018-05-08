package Persistence;

import javax.persistence.*;

@Entity
@Table(name = "Medic", schema = "main", catalog = "")
public class MedicEntity implements java.io.Serializable {
    private short id;
    private String username;
    private String parola;
    private String nume;
    private String prenume;
    private SpitalEntity spitalBySpital;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicEntity that = (MedicEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (parola != null ? !parola.equals(that.parola) : that.parola != null) return false;
        if (nume != null ? !nume.equals(that.nume) : that.nume != null) return false;
        if (prenume != null ? !prenume.equals(that.prenume) : that.prenume != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (parola != null ? parola.hashCode() : 0);
        result = 31 * result + (nume != null ? nume.hashCode() : 0);
        result = 31 * result + (prenume != null ? prenume.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "spital", referencedColumnName = "id", nullable = false)
    public SpitalEntity getSpitalBySpital() {
        return spitalBySpital;
    }

    public void setSpitalBySpital(SpitalEntity spitalBySpital) {
        this.spitalBySpital = spitalBySpital;
    }
}
