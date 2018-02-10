/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Entity
@Table(name = "discipline")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discipline.findAll", query = "SELECT d FROM Discipline d")
    , @NamedQuery(name = "Discipline.findById", query = "SELECT d FROM Discipline d WHERE d.id = :id")
    , @NamedQuery(name = "Discipline.findByName", query = "SELECT d FROM Discipline d WHERE d.name = :name")})
public class Discipline implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "AcademicUser_user", referencedColumnName = "user")
    @ManyToOne(optional = false)
    private AcademicUser academicUseruser;
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Group1 groupId;

    public Discipline() {
    }

    public Discipline(Integer id) {
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

    public AcademicUser getAcademicUseruser() {
        return academicUseruser;
    }

    public void setAcademicUseruser(AcademicUser academicUseruser) {
        this.academicUseruser = academicUseruser;
    }

    public Group1 getGroupId() {
        return groupId;
    }

    public void setGroupId(Group1 groupId) {
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
        if (!(object instanceof Discipline)) {
            return false;
        }
        Discipline other = (Discipline) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Discipline[ id=" + id + " ]";
    }
    
}
