package tn.insat.domain;


import java.util.Date;


/**
 * Created by My session on 11/05/2017.
 */
public class CompteMin {
    private Long id;
    private Float solde;
    private Date creationDate;
    private String user_account_firstName;
    private String user_account_lastName;

    public void setUser_account_firstName(String user_account_firstName) {
        this.user_account_firstName = user_account_firstName;
    }

    public void setUser_account_lastName(String user_account_lastName) {
        this.user_account_lastName = user_account_lastName;
    }

    public String getUser_account_firstName() {
        return user_account_firstName;
    }

    public String getUser_account_lastName() {
        return user_account_lastName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
