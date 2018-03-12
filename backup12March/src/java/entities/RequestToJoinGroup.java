/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Entity
@Table(name = "requestToJoinGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequestToJoinGroup.findAll", query = "SELECT r FROM RequestToJoinGroup r")
                    ,@NamedQuery(name = "RequestToJoinGroup.findreq", query = "SELECT r FROM RequestToJoinGroup r  WHERE r.state = 'New' ")
            ,@NamedQuery(name = "RequestToJoinGroup.MemberOf", query = "SELECT r FROM RequestToJoinGroup r  WHERE r.academicUseruser = :academicUser and r.state = 'Accepted' ")
    , @NamedQuery(name = "RequestToJoinGroup.findByCreationDate", query = "SELECT r FROM RequestToJoinGroup r WHERE r.creationDate = :creationDate")
    , @NamedQuery(name = "RequestToJoinGroup.findById", query = "SELECT r FROM RequestToJoinGroup r WHERE r.id = :id")
    , @NamedQuery(name = "RequestToJoinGroup.findByState", query = "SELECT r FROM RequestToJoinGroup r WHERE  r.state = :state")})
public class RequestToJoinGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "state")
    private String state;
    @JoinColumn(name = "AcademicUser_user", referencedColumnName = "user")
    @ManyToOne(optional = false)
    private AcademicUser academicUseruser;
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Groupp groupId;

    public RequestToJoinGroup() {
    }

    public RequestToJoinGroup(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public AcademicUser getAcademicUseruser() {
        return academicUseruser;
    }

    public void setAcademicUseruser(AcademicUser academicUseruser) {
        this.academicUseruser = academicUseruser;
    }

    public Groupp getGroupId() {
        return groupId;
    }

    public void setGroupId(Groupp groupId) {
        this.groupId = groupId;
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
        if (!(object instanceof RequestToJoinGroup)) {
            return false;
        }
        RequestToJoinGroup other = (RequestToJoinGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RequestToJoinGroup[ id=" + id + " ]";
    }
    
}
