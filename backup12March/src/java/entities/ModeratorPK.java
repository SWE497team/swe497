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
public class ModeratorPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "groupp_id")
    private int grouppId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AcademicUser_user")
    private int academicUseruser;

    public ModeratorPK() {
    }

    public ModeratorPK(int grouppId, int academicUseruser) {
        this.grouppId = grouppId;
        this.academicUseruser = academicUseruser;
    }

    public int getGrouppId() {
        return grouppId;
    }

    public void setGrouppId(int grouppId) {
        this.grouppId = grouppId;
    }

    public int getAcademicUseruser() {
        return academicUseruser;
    }

    public void setAcademicUseruser(int academicUseruser) {
        this.academicUseruser = academicUseruser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) grouppId;
        hash += (int) academicUseruser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModeratorPK)) {
            return false;
        }
        ModeratorPK other = (ModeratorPK) object;
        if (this.grouppId != other.grouppId) {
            return false;
        }
        if (this.academicUseruser != other.academicUseruser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ModeratorPK[ grouppId=" + grouppId + ", academicUseruser=" + academicUseruser + " ]";
    }
    
}
