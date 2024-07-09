/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "account", catalog = "ProductIntro2", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"account"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByAccountId", query = "SELECT a FROM Account a WHERE a.accountId = :accountId")
    , @NamedQuery(name = "Account.findByAccount", query = "SELECT a FROM Account a WHERE a.account = :account")
    , @NamedQuery(name = "Account.findByPass", query = "SELECT a FROM Account a WHERE a.pass = :pass")
    , @NamedQuery(name = "Account.findByLastName", query = "SELECT a FROM Account a WHERE a.lastName = :lastName")
    , @NamedQuery(name = "Account.findByFirstName", query = "SELECT a FROM Account a WHERE a.firstName = :firstName")
    , @NamedQuery(name = "Account.findByBirthday", query = "SELECT a FROM Account a WHERE a.birthday = :birthday")
    , @NamedQuery(name = "Account.findByGender", query = "SELECT a FROM Account a WHERE a.gender = :gender")
    , @NamedQuery(name = "Account.findByPhone", query = "SELECT a FROM Account a WHERE a.phone = :phone")
    , @NamedQuery(name = "Account.findByIsUse", query = "SELECT a FROM Account a WHERE a.isUse = :isUse")
    , @NamedQuery(name = "Account.checkLogin", query = "SELECT a FROM Account a WHERE a.account = :account AND a.pass = :pass")
})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accountId", nullable = false)
    private Integer accountId;
    @Basic(optional = false)
    @Column(name = "account", nullable = false, length = 20)
    private String account;
    @Basic(optional = false)
    @Column(name = "pass", nullable = false, length = 20)
    private String pass;
    @Column(name = "lastName", length = 50)
    private String lastName;
    @Basic(optional = false)
    @Column(name = "firstName", nullable = false, length = 30)
    private String firstName;
    @Column(name = "birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "isUse")
    private Boolean isUse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<Product> productCollection;
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    @ManyToOne
    private Role roleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<AccountAuth> accountAuthCollection;

    public Account() {
    }

    public Account(Integer accountId) {
        this.accountId = accountId;
    }

    public Account(Integer accountId, String account, String pass, String firstName) {
        this.accountId = accountId;
        this.account = account;
        this.pass = pass;
        this.firstName = firstName;
    }

    public Account(String account, String pass, String lastName, String firstName, Date birthday, Boolean gender, String phone, Boolean isUse, Role roleId) {
        this.account = account;
        this.pass = pass;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.isUse = isUse;
        this.roleId = roleId;
    }
    
    
    

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getFullName(){
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public Collection<AccountAuth> getAccountAuthCollection() {
        return accountAuthCollection;
    }

    public void setAccountAuthCollection(Collection<AccountAuth> accountAuthCollection) {
        this.accountAuthCollection = accountAuthCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "blo.Account[ accountId=" + accountId + " ]";
    }
    
}
