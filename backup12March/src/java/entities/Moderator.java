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
@Table(name = "moderator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moderator.findAll", query = "SELECT m FROM Moderator m")
    , @NamedQuery(name = "Moderator.findByGrouppId", query = "SELECT m FROM Moderator m WHERE m.moderatorPK.grouppId = :grouppId")
    , @NamedQuery(name = "Moderator.findByAcademicUseruser", query = "SELECT m FROM Moderator m WHERE m.moderatorPK.academicUseruser = :academicUseruser")
    , @NamedQuery(name = "Moderator.findByCreationDate", query = "SELECT m FROM Moderator m WHERE m.creationDate = :creationDate")})
public class Moderator implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ModeratorPK moderatorPK;
    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JoinColumn(name = "AcademicUser_user", referencedColumnName = "user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AcademicUser academicUser;
    @JoinColumn(name = "groupp_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Groupp groupp;

    public Moderator() {
    }

    public Moderator(ModeratorPK moderatorPK) {
        this.moderatorPK = moderatorPK;
    }

    public Moderator(int grouppId, int academicUseruser) {
        this.moderatorPK = new ModeratorPK(grouppId, academicUseruser);
    }

    public ModeratorPK getModeratorPK() {
        return moderatorPK;
    }

    public void setModeratorPK(ModeratorPK moderatorPK) {
        this.moderatorPK = moderatorPK;
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
        hash += (moderatorPK != null ? moderatorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moderator)) {
            return false;
        }
        Moderator other = (Moderator) object;
        if ((this.moderatorPK == null && other.moderatorPK != null) || (this.moderatorPK != null && !this.moderatorPK.equals(other.moderatorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Moderator[ moderatorPK=" + moderatorPK + " ]";
    }
    
}
