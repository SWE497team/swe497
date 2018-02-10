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
@Table(name = "AcademicUser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcademicUser.findAll", query = "SELECT a FROM AcademicUser a")
    , @NamedQuery(name = "AcademicUser.findByUser", query = "SELECT a FROM AcademicUser a WHERE a.user = :user")})
public class AcademicUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user")
    private Integer user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "academicUser")
    private Student student;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicUseruser")
    private Collection<Discipline> disciplineCollection;
    @JoinColumn(name = "user", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user1;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "academicUser")
    private Instructor instructor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicUseruser")
    private Collection<Post> postCollection;

    public AcademicUser() {
    }

    public AcademicUser(Integer user) {
        this.user = user;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @XmlTransient
    public Collection<Discipline> getDisciplineCollection() {
        return disciplineCollection;
    }

    public void setDisciplineCollection(Collection<Discipline> disciplineCollection) {
        this.disciplineCollection = disciplineCollection;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
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
        if (!(object instanceof AcademicUser)) {
            return false;
        }
        AcademicUser other = (AcademicUser) object;
        if ((this.user == null && other.user != null) || (this.user != null && !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AcademicUser[ user=" + user + " ]";
    }
    
}
