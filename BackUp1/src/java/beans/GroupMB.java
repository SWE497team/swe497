/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.AcademicUser;
import entities.Groupp;
import entities.RequestToJoinGroup;

import facades.GrouppFacade;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import facades.RequestToJoinGroupFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@ManagedBean(name = "groupMB")
@SessionScoped
public class GroupMB implements Serializable {

    @ManagedProperty(value = "#{userMB}")
    private UserMB userMB;

    @EJB
    private GrouppFacade groupFacade;
    
    @EJB
    private RequestToJoinGroupFacade requestToJoinGroupFacade;
    
    private RequestToJoinGroup request;

    private boolean hasError = false;

    private Groupp group;
    private List<Groupp> groups;
    private List<Groupp> allgroups;
    


    public GroupMB() {
        group = new Groupp();
        request = new RequestToJoinGroup();

    }



    public UserMB getUserMB() {
        return userMB;
    }

    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }

    public boolean hasError() {
        return this.hasError;
    }

    public Groupp getGroup() {
        return group;
    }

    public void setGroup(Groupp group) {
        this.group = group;
    }

    public String createGroup() {

        EntityManager em = groupFacade.getEntityManager();
        Query q = em.createNamedQuery("Groupp.findByName");
        

        if (!q.setParameter("name", group.getName()).getResultList().isEmpty()) {
            this.hasError = true;
            return "createGroup.xhtml?faces-redirect=true";
        }
        Calendar calendar = Calendar.getInstance();
        java.sql.Date creation = new java.sql.Date(calendar.getTime().getTime());
        group.setCreationDate(creation);

        group.setInstructorUser(userMB.getInstructor());
        groupFacade.create(group);

        return "instructorPage.xhtml?faces-redirect=true";

    }

    public List<Groupp> getGroups() {

        EntityManager em = groupFacade.getEntityManager();
        Query q = em.createNamedQuery("Groupp.listGroups");
        groups = q.setParameter("instructor", userMB.getInstructor()).getResultList();
        return groups;
    }


    public void setGroups(List<Groupp> groups) {
        this.groups = groups;
    }

    public List<Groupp> getAllgroups() {
        allgroups =  groupFacade.findAll();
        
        return allgroups;
    }

    public void setAllgroups(List<Groupp> allgroups) {
        this.allgroups = allgroups;
    }
    

    

    public RequestToJoinGroup getRequest() {
        return request;
    }

    public void setRequest(RequestToJoinGroup request) {
        this.request = request;
    }


}
