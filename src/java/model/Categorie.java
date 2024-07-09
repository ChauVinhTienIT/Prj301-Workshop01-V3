/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "categorie", catalog = "ProductIntro2", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c")
    , @NamedQuery(name = "Categorie.findByTypeId", query = "SELECT c FROM Categorie c WHERE c.typeId = :typeId")
    , @NamedQuery(name = "Categorie.findByCategoryName", query = "SELECT c FROM Categorie c WHERE c.categoryName = :categoryName")
    , @NamedQuery(name = "Categorie.findByMemo", query = "SELECT c FROM Categorie c WHERE c.memo = :memo")})
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "typeId", nullable = false)
    private Integer typeId;
    @Basic(optional = false)
    @Column(name = "categoryName", nullable = false, length = 88)
    private String categoryName;
    @Column(name = "memo", length = 1073741823)
    private String memo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
    private Collection<Product> productCollection;

    public Categorie() {
    }

    public Categorie(Integer typeId) {
        this.typeId = typeId;
    }

    public Categorie(Integer typeId, String categoryName) {
        this.typeId = typeId;
        this.categoryName = categoryName;
    }

    public Categorie(String categoryName, String memo) {
        this.categoryName = categoryName;
        this.memo = memo;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "blo.Categorie[ typeId=" + typeId + " ]";
    }
    
}
