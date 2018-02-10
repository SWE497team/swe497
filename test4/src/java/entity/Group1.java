/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Entity
@Table(name = "group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Group1.findAll", query = "SELECT g FROM Group1 g")
    , @NamedQuery(name = "Group1.findById", query = "SELECT g FROM Group1 g WHERE g.id = :id")
    , @NamedQuery(name = "Group1.findByName", query = "SELECT g FROM Group1 g WHERE g.name = :name")
    , @NamedQuery(name = "Group1.findByDiscipline", query = "SELECT g FROM Group1 g WHERE g.discipline = :discipline")
    , @NamedQuery(name = "Group1.findByCreationDate", query = "SELECT g FROM Group1 g WHERE g.creationDate = :creationDate")
    , @NamedQuery(name = "Group1.findByResponseDate", query = "SELECT g FROM Group1 g WHERE g.responseDate = :responseDate")})
public class Group1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "discipline")
    private String discipline;
    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Size(max = 45)
    @Column(name = "responseDate")
    private String responseDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private Collection<RequestToJoinGroup> requestToJoinGroupCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private Collection<Discipline> disciplineCollection;
    @JoinColumn(name = "instructor_user", referencedColumnName = "user")
    @ManyToOne(optional = false)
    private Instructor instructorUser;
    @JoinColumn(name = "student_user", referencedColumnName = "user")
    @ManyToOne(optional = false)
    private Student studentUser;

    public Group1() {
    }

    public Group1(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    @XmlTransient
    public Collection<RequestToJoinGroup> getRequestToJoinGroupCollection() {
        return requestToJoinGroupCollection;
    }

    public void setRequestToJoinGroupCollection(Collection<RequestToJoinGroup> requestToJoinGroupCollection) {
        this.requestToJoinGroupCollection = requestToJoinGroupCollection;
    }

    @XmlTransient
    public Collection<Discipline> getDisciplineCollection() {
        return disciplineCollection;
    }

    public void setDisciplineCollection(Collection<Discipline> disciplineCollection) {
        this.disciplineCollection = disciplineCollection;
    }

    public Instructor getInstructorUser() {
        return instructorUser;
    }

    public void setInstructorUser(Instructor instructorUser) {
        this.instructorUser = instructorUser;
    }

    public Student getStudentUser() {
        return studentUser;
    }

    public void setStudentUser(Student studentUser) {
        this.studentUser = studentUser;
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
        if (!(object instanceof Group1)) {
            return false;
        }
        Group1 other = (Group1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Group1[ id=" + id + " ]";
    }
    
}
