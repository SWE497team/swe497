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
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findByUser", query = "SELECT s FROM Student s WHERE s.user = :user")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user")
    private Integer user;
    @JoinColumn(name = "user", referencedColumnName = "user", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AcademicUser academicUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentUser")
    private Collection<RequestToLink> requestToLinkCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentUser")
    private Collection<Solution> solutionCollection;

    public Student() {
    }

    public Student(Integer user) {
        this.user = user;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public AcademicUser getAcademicUser() {
        return academicUser;
    }

    public void setAcademicUser(AcademicUser academicUser) {
        this.academicUser = academicUser;
    }

    @XmlTransient
    public Collection<RequestToLink> getRequestToLinkCollection() {
        return requestToLinkCollection;
    }

    public void setRequestToLinkCollection(Collection<RequestToLink> requestToLinkCollection) {
        this.requestToLinkCollection = requestToLinkCollection;
    }

    @XmlTransient
    public Collection<Solution> getSolutionCollection() {
        return solutionCollection;
    }

    public void setSolutionCollection(Collection<Solution> solutionCollection) {
        this.solutionCollection = solutionCollection;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.user == null && other.user != null) || (this.user != null && !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Student[ user=" + user + " ]";
    }
    
}
