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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "matrial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matrial.findAll", query = "SELECT m FROM Matrial m")
    , @NamedQuery(name = "Matrial.findByText", query = "SELECT m FROM Matrial m WHERE m.text = :text")
    , @NamedQuery(name = "Matrial.findByCreationDate", query = "SELECT m FROM Matrial m WHERE m.creationDate = :creationDate")
    , @NamedQuery(name = "Matrial.findByFile", query = "SELECT m FROM Matrial m WHERE m.file = :file")
    , @NamedQuery(name = "Matrial.findByPostId", query = "SELECT m FROM Matrial m WHERE m.postId = :postId")
    , @NamedQuery(name = "Matrial.findByTitle", query = "SELECT m FROM Matrial m WHERE m.title = :title")})
public class Matrial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 45)
    @Column(name = "text")
    private String text;
    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Size(max = 45)
    @Column(name = "file")
    private String file;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "post_id")
    private Integer postId;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Post post;

    public Matrial() {
    }

    public Matrial(Integer postId) {
        this.postId = postId;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matrial)) {
            return false;
        }
        Matrial other = (Matrial) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Matrial[ postId=" + postId + " ]";
    }
    
}
