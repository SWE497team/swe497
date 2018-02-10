/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Entity
@Table(name = "admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a")
    , @NamedQuery(name = "Admin.findByUser", query = "SELECT a FROM Admin a WHERE a.user = :user")})
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user")
    private Integer user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adminUser")
    private Collection<RequestToLink> requestToLinkCollection;
    @JoinColumn(name = "user", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user1;

    public Admin() {
    }

    public Admin(Integer user) {
        this.user = user;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @XmlTransient
    public Collection<RequestToLink> getRequestToLinkCollection() {
        return requestToLinkCollection;
    }

    public void setRequestToLinkCollection(Collection<RequestToLink> requestToLinkCollection) {
        this.requestToLinkCollection = requestToLinkCollection;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (user != null ? user.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.user == null && other.user != null) || (this.user != null && !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Admin[ user=" + user + " ]";
    }
    
}
