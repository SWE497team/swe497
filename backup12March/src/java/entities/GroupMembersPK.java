/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Embeddable
public class GroupMembersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "AcademicUser_user")
    private int academicUseruser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "group_id")
    private int groupId;

    public GroupMembersPK() {
    }

    public GroupMembersPK(int academicUseruser, int groupId) {
        this.academicUseruser = academicUseruser;
        this.groupId = groupId;
    }

    public int getAcademicUseruser() {
        return academicUseruser;
    }

    public void setAcademicUseruser(int academicUseruser) {
        this.academicUseruser = academicUseruser;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) academicUseruser;
        hash += (int) groupId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupMembersPK)) {
            return false;
        }
        GroupMembersPK other = (GroupMembersPK) object;
        if (this.academicUseruser != other.academicUseruser) {
            return false;
        }
        if (this.groupId != other.groupId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GroupMembersPK[ academicUseruser=" + academicUseruser + ", groupId=" + groupId + " ]";
    }
    
}
