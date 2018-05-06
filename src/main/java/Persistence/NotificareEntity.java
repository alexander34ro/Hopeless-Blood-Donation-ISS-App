package Persistence;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Notificare", schema = "main", catalog = "")
public class NotificareEntity implements Serializable {
    private short id;
    private String mesaj;
    private AsistentEntity asistentByAsistent;
    private DonatorEntity donatorByDonator;
    private short asistent;
    private short donator;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "mesaj")
    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificareEntity that = (NotificareEntity) o;

        if (id != that.id) return false;
        if (mesaj != null ? !mesaj.equals(that.mesaj) : that.mesaj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (mesaj != null ? mesaj.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "asistent", referencedColumnName = "id", nullable = false)
    public AsistentEntity getAsistentByAsistent() {
        return asistentByAsistent;
    }

    public void setAsistentByAsistent(AsistentEntity asistentByAsistent) {
        this.asistentByAsistent = asistentByAsistent;
    }

    @ManyToOne
    @JoinColumn(name = "donator", referencedColumnName = "id", nullable = false)
    public DonatorEntity getDonatorByDonator() {
        return donatorByDonator;
    }

    public void setDonatorByDonator(DonatorEntity donatorByDonator) {
        this.donatorByDonator = donatorByDonator;
    }

    @Basic
    @Column(name = "asistent")
    public short getAsistent() {
        return asistent;
    }

    public void setAsistent(short asistent) {
        this.asistent = asistent;
    }

    @Basic
    @Column(name = "donator")
    public short getDonator() {
        return donator;
    }

    public void setDonator(short donator) {
        this.donator = donator;
    }
}
