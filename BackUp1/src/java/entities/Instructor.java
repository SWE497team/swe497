/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
@Table(name = "instructor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instructor.findAll", query = "SELECT i FROM Instructor i")
    , @NamedQuery(name = "Instructor.findByUser", query = "SELECT i FROM Instructor i WHERE i.user = :user")})
public class Instructor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user")
    private Integer user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructorUser")
    private Collection<Groupp> grouppCollection;
    @JoinColumn(name = "user", referencedColumnName = "user", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AcademicUser academicUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructorUser")
    private Collection<Solution> solutionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructorUser")
    private Collection<Responsibility> responsibilityCollection;

    public Instructor() {
    }

    public Instructor(Integer user) {
        this.user = user;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @XmlTransient
    public Collection<Groupp> getGrouppCollection() {
        return grouppCollection;
    }

    public void setGrouppCollection(Collection<Groupp> grouppCollection) {
        this.grouppCollection = grouppCollection;
    }

    public AcademicUser getAcademicUser() {
        return academicUser;
    }

    public void setAcademicUser(AcademicUser academicUser) {
        this.academicUser = academicUser;
    }

    @XmlTransient
    public Collection<Solution> getSolutionCollection() {
        return solutionCollection;
    }

    public void setSolutionCollection(Collection<Solution> solutionCollection) {
        this.solutionCollection = solutionCollection;
    }

    @XmlTransient
    public Collection<Responsibility> getResponsibilityCollection() {
        return responsibilityCollection;
    }

    public void setResponsibilityCollection(Collection<Responsibility> responsibilityCollection) {
        this.responsibilityCollection = responsibilityCollection;
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
        if (!(object instanceof Instructor)) {
            return false;
        }
        Instructor other = (Instructor) object;
        if ((this.user == null && other.user != null) || (this.user != null && !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Instructor[ user=" + user + " ]";
    }
    
}
