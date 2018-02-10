/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
@Table(name = "responsibility")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsibility.findAll", query = "SELECT r FROM Responsibility r")
    , @NamedQuery(name = "Responsibility.findById", query = "SELECT r FROM Responsibility r WHERE r.responsibilityPK.id = :id")
    , @NamedQuery(name = "Responsibility.findByCreationDate", query = "SELECT r FROM Responsibility r WHERE r.creationDate = :creationDate")
    , @NamedQuery(name = "Responsibility.findByQuestion", query = "SELECT r FROM Responsibility r WHERE r.responsibilityPK.question = :question")})
public class Responsibility implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResponsibilityPK responsibilityPK;
    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JoinColumn(name = "instructor_user", referencedColumnName = "user")
    @ManyToOne(optional = false)
    private Instructor instructorUser;

    public Responsibility() {
    }

    public Responsibility(ResponsibilityPK responsibilityPK) {
        this.responsibilityPK = responsibilityPK;
    }

    public Responsibility(int id, int question) {
        this.responsibilityPK = new ResponsibilityPK(id, question);
    }

    public ResponsibilityPK getResponsibilityPK() {
        return responsibilityPK;
    }

    public void setResponsibilityPK(ResponsibilityPK responsibilityPK) {
        this.responsibilityPK = responsibilityPK;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Instructor getInstructorUser() {
        return instructorUser;
    }

    public void setInstructorUser(Instructor instructorUser) {
        this.instructorUser = instructorUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (responsibilityPK != null ? responsibilityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsibility)) {
            return false;
        }
        Responsibility other = (Responsibility) object;
        if ((this.responsibilityPK == null && other.responsibilityPK != null) || (this.responsibilityPK != null && !this.responsibilityPK.equals(other.responsibilityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Responsibility[ responsibilityPK=" + responsibilityPK + " ]";
    }
    
}
