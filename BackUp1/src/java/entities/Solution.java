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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Entity
@Table(name = "solution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solution.findAll", query = "SELECT s FROM Solution s")
    , @NamedQuery(name = "Solution.findById", query = "SELECT s FROM Solution s WHERE s.id = :id")
    , @NamedQuery(name = "Solution.findByText", query = "SELECT s FROM Solution s WHERE s.text = :text")
    , @NamedQuery(name = "Solution.findByCreationDate", query = "SELECT s FROM Solution s WHERE s.creationDate = :creationDate")
    , @NamedQuery(name = "Solution.findByMark", query = "SELECT s FROM Solution s WHERE s.mark = :mark")
    , @NamedQuery(name = "Solution.findByComment", query = "SELECT s FROM Solution s WHERE s.comment = :comment")})
public class Solution implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "text")
    private String text;
    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "mark")
    private Integer mark;
    @Size(max = 45)
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "assessment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Assessment assessmentId;
    @JoinColumn(name = "instructor_user", referencedColumnName = "user")
    @ManyToOne(optional = false)
    private Instructor instructorUser;
    @JoinColumn(name = "student_user", referencedColumnName = "user")
    @ManyToOne(optional = false)
    private Student studentUser;

    public Solution() {
    }

    public Solution(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Assessment getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Assessment assessmentId) {
        this.assessmentId = assessmentId;
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
        if (!(object instanceof Solution)) {
            return false;
        }
        Solution other = (Solution) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Solution[ id=" + id + " ]";
    }
    
}
