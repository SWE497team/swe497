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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Entity
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")
    , @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id")
    , @NamedQuery(name = "Post.findByPostType", query = "SELECT p FROM Post p WHERE p.postType = :postType")
    , @NamedQuery(name = "Post.findByPostDate", query = "SELECT p FROM Post p WHERE p.postDate = :postDate")
    , @NamedQuery(name = "Post.findByState", query = "SELECT p FROM Post p WHERE p.state = :state")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "postType")
    private String postType;
    @Column(name = "postDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    @Size(max = 45)
    @Column(name = "state")
    private String state;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "post")
    private Question question;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "post")
    private Matrial matrial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private Collection<Assessment> assessmentCollection;
    @JoinColumn(name = "AcademicUser_user", referencedColumnName = "user")
    @ManyToOne(optional = false)
    private AcademicUser academicUseruser;
    @JoinColumn(name = "groupp_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Groupp grouppId;

    public Post() {
    }

    public Post(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Matrial getMatrial() {
        return matrial;
    }

    public void setMatrial(Matrial matrial) {
        this.matrial = matrial;
    }

    @XmlTransient
    public Collection<Assessment> getAssessmentCollection() {
        return assessmentCollection;
    }

    public void setAssessmentCollection(Collection<Assessment> assessmentCollection) {
        this.assessmentCollection = assessmentCollection;
    }

    public AcademicUser getAcademicUseruser() {
        return academicUseruser;
    }

    public void setAcademicUseruser(AcademicUser academicUseruser) {
        this.academicUseruser = academicUseruser;
    }

    public Groupp getGrouppId() {
        return grouppId;
    }

    public void setGrouppId(Groupp grouppId) {
        this.grouppId = grouppId;
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
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Post[ id=" + id + " ]";
    }
    
}
