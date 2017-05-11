package tn.insat.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Compte.
 */
@Entity
@Table(name = "compte")
public class Compte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "solde")
    private Float solde;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(unique = true)
    private User user_account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSolde() {
        return solde;
    }

    public Compte solde(Float solde) {
        this.solde = solde;
        return this;
    }

    public void setSolde(Float solde) {
        this.solde = solde;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Compte creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser_account() {
        return user_account;
    }

    public Compte user_account(User user) {
        this.user_account = user;
        return this;
    }

    public void setUser_account(User user) {
        this.user_account = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Compte compte = (Compte) o;
        if (compte.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), compte.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Compte{" +
            "id=" + getId() +
            ", solde='" + getSolde() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
