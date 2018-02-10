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
@Table(name = "parent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parent.findAll", query = "SELECT p FROM Parent p")
    , @NamedQuery(name = "Parent.findByUser", query = "SELECT p FROM Parent p WHERE p.user = :user")})
public class Parent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user")
    private Integer user;
    @JoinColumn(name = "user", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentUser")
    private Collection<RequestToLink> requestToLinkCollection;

    public Parent() {
    }

    public Parent(Integer user) {
        this.user = user;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @XmlTransient
    public Collection<RequestToLink> getRequestToLinkCollection() {
        return requestToLinkCollection;
    }

    public void setRequestToLinkCollection(Collection<RequestToLink> requestToLinkCollection) {
        this.requestToLinkCollection = requestToLinkCollection;
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
        if (!(object instanceof Parent)) {
            return false;
        }
        Parent other = (Parent) object;
        if ((this.user == null && other.user != null) || (this.user != null && !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Parent[ user=" + user + " ]";
    }
    
}
