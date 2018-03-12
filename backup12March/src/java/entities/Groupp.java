/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
@Table(name = "groupp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupp.findAll", query = "SELECT g FROM Groupp g")
    , @NamedQuery(name = "Groupp.listGroups", query = "SELECT g FROM Groupp g WHERE g.instructorUser = :instructor")
    , @NamedQuery(name = "Groupp.findById", query = "SELECT g FROM Groupp g WHERE g.id = :id")
    , @NamedQuery(name = "Groupp.findByName", query = "SELECT g FROM Groupp g WHERE g.name = :name")
    , @NamedQuery(name = "Groupp.findByDiscipline", query = "SELECT g FROM Groupp g WHERE g.discipline = :discipline")
    , @NamedQuery(name = "Groupp.findByCreationDate", query = "SELECT g FROM Groupp g WHERE g.creationDate = :creationDate")
    , @NamedQuery(name = "Groupp.findByResponseDate", query = "SELECT g FROM Groupp g WHERE g.responseDate = :responseDate")})
public class Groupp implements Serializable {

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
    @Column(name = "responseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupp")
    private Collection<GroupMembers> groupMembersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private Collection<RequestToJoinGroup> requestToJoinGroupCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupp")
    private Collection<Moderator> moderatorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private Collection<Discipline> disciplineCollection;
    @JoinColumn(name = "instructor_user", referencedColumnName = "user")
    @ManyToOne(optional = false)
    private Instructor instructorUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grouppId")
    private Collection<Post> postCollection;

    public Groupp() {
    }

    public Groupp(Integer id) {
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

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    @XmlTransient
    public Collection<GroupMembers> getGroupMembersCollection() {
        return groupMembersCollection;
    }

    public void setGroupMembersCollection(Collection<GroupMembers> groupMembersCollection) {
        this.groupMembersCollection = groupMembersCollection;
    }

    @XmlTransient
    public Collection<RequestToJoinGroup> getRequestToJoinGroupCollection() {
        return requestToJoinGroupCollection;
    }

    public void setRequestToJoinGroupCollection(Collection<RequestToJoinGroup> requestToJoinGroupCollection) {
        this.requestToJoinGroupCollection = requestToJoinGroupCollection;
    }

    @XmlTransient
    public Collection<Moderator> getModeratorCollection() {
        return moderatorCollection;
    }

    public void setModeratorCollection(Collection<Moderator> moderatorCollection) {
        this.moderatorCollection = moderatorCollection;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupp)) {
            return false;
        }
        Groupp other = (Groupp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Groupp[ id=" + id + " ]";
    }
    
}
