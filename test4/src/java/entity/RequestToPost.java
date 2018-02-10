/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
@Table(name = "requestToPost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequestToPost.findAll", query = "SELECT r FROM RequestToPost r")
    , @NamedQuery(name = "RequestToPost.findById", query = "SELECT r FROM RequestToPost r WHERE r.id = :id")
    , @NamedQuery(name = "RequestToPost.findByState", query = "SELECT r FROM RequestToPost r WHERE r.state = :state")
    , @NamedQuery(name = "RequestToPost.findByCreationDate", query = "SELECT r FROM RequestToPost r WHERE r.creationDate = :creationDate")})
public class RequestToPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "state")
    private String state;
    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Post postId;

    public RequestToPost() {
    }

    public RequestToPost(Integer id) {
        this.id = id;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
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
        if (!(object instanceof RequestToPost)) {
            return false;
        }
        RequestToPost other = (RequestToPost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RequestToPost[ id=" + id + " ]";
    }
    
}
