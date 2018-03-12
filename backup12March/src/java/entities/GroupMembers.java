/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Entity
@Table(name = "groupMembers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupMembers.findAll", query = "SELECT g FROM GroupMembers g")
    , @NamedQuery(name = "GroupMembers.findByCreationDate", query = "SELECT g FROM GroupMembers g WHERE g.creationDate = :creationDate")
    , @NamedQuery(name = "GroupMembers.findByAcademicUseruser", query = "SELECT g FROM GroupMembers g WHERE g.groupMembersPK.academicUseruser = :academicUseruser")
    , @NamedQuery(name = "GroupMembers.findByGroupId", query = "SELECT g FROM GroupMembers g WHERE g.groupMembersPK.groupId = :groupId")})
public class GroupMembers implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupMembersPK groupMembersPK;
    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JoinColumn(name = "AcademicUser_user", referencedColumnName = "user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AcademicUser academicUser;
    @JoinColumn(name = "group_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Groupp groupp;

    public GroupMembers() {
    }

    public GroupMembers(GroupMembersPK groupMembersPK) {
        this.groupMembersPK = groupMembersPK;
    }

    public GroupMembers(int academicUseruser, int groupId) {
        this.groupMembersPK = new GroupMembersPK(academicUseruser, groupId);
    }

    public GroupMembersPK getGroupMembersPK() {
        return groupMembersPK;
    }

    public void setGroupMembersPK(GroupMembersPK groupMembersPK) {
        this.groupMembersPK = groupMembersPK;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public AcademicUser getAcademicUser() {
        return academicUser;
    }

    public void setAcademicUser(AcademicUser academicUser) {
        this.academicUser = academicUser;
    }

    public Groupp getGroupp() {
        return groupp;
    }

    public void setGroupp(Groupp groupp) {
        this.groupp = groupp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupMembersPK != null ? groupMembersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupMembers)) {
            return false;
        }
        GroupMembers other = (GroupMembers) object;
        if ((this.groupMembersPK == null && other.groupMembersPK != null) || (this.groupMembersPK != null && !this.groupMembersPK.equals(other.groupMembersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GroupMembers[ groupMembersPK=" + groupMembersPK + " ]";
    }
    
}
