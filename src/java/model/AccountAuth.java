/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "account_auth", catalog = "ProductIntro2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountAuth.findAll", query = "SELECT a FROM AccountAuth a")
    , @NamedQuery(name = "AccountAuth.findById", query = "SELECT a FROM AccountAuth a WHERE a.id = :id")
    , @NamedQuery(name = "AccountAuth.findBySelector", query = "SELECT a FROM AccountAuth a WHERE a.selector = :selector")
    , @NamedQuery(name = "AccountAuth.findByValidator", query = "SELECT a FROM AccountAuth a WHERE a.validator = :validator")})
public class AccountAuth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "selector", length = 12)
    private String selector;
    @Column(name = "validator", length = 64)
    private String validator;
    @JoinColumn(name = "accountId", referencedColumnName = "accountId", nullable = false)
    @ManyToOne(optional = false)
    private Account accountId;

    public AccountAuth() {
    }

    public AccountAuth(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountAuth)) {
            return false;
        }
        AccountAuth other = (AccountAuth) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "blo.AccountAuth[ id=" + id + " ]";
    }
    
}
