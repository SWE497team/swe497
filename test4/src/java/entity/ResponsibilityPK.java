/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
public class ResponsibilityPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "question")
    private int question;

    public ResponsibilityPK() {
    }

    public ResponsibilityPK(int id, int question) {
        this.id = id;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) question;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsibilityPK)) {
            return false;
        }
        ResponsibilityPK other = (ResponsibilityPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.question != other.question) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ResponsibilityPK[ id=" + id + ", question=" + question + " ]";
    }
    
}
