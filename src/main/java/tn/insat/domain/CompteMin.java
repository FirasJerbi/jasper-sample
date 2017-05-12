package tn.insat.domain;


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Created by My session on 11/05/2017.
 */
public class CompteMin {
    private Long id;
    private Float solde;
    private Date creationDate;
    private Long user_account;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getUser_account() {
        return user_account;
    }

    public void setUser_account(Long user_account) {
        this.user_account = user_account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSolde() {
        return solde;
    }

    public CompteMin solde(Float solde) {
        this.solde = solde;
        return this;
    }

    public void setSolde(Float solde) {
        this.solde = solde;
    }

}
