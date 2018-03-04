/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.AcademicUser;
import entities.Groupp;
import entities.RequestToJoinGroup;
import entities.Student;
import facades.RequestToJoinGroupFacade;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@ManagedBean(name = "requestToJoinGroupMB")
@SessionScoped
public class RequestToJoinGroupMB implements Serializable {

    /**
     * Creates a new instance of RequestToJoinGroupMB
     */
    @ManagedProperty(value = "#{groupMB}")
    private GroupMB groupMB;

    @ManagedProperty(value = "#{userMB}")
    private UserMB userMB;

    @EJB
    private RequestToJoinGroupFacade requestToJoinGroupFacade;


    private RequestToJoinGroup req;
    private List<RequestToJoinGroup> requests;

    public RequestToJoinGroupMB() {
        req = new RequestToJoinGroup();

    }

    public GroupMB getGroupMB() {
        return groupMB;
    }

    public void setGroupMB(GroupMB groupMB) {
        this.groupMB = groupMB;
    }

    public RequestToJoinGroup getReq() {
        return req;
    }

    public void setReq(RequestToJoinGroup req) {
        this.req = req;
    }

    public UserMB getUserMB() {
        return userMB;
    }

    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }

    public String sendReq() {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date creation = new java.sql.Date(calendar.getTime().getTime());

        req.setCreationDate(creation);
        req.setState("New");
        req.setGroupId(groupMB.getGroup());
        req.setAcademicUseruser(userMB.getUser().getAcademicUser());

        requestToJoinGroupFacade.create(req);

        return "studentPage.xhtml?faces-redirect=true";
    }
    public List<RequestToJoinGroup> getRequests() {
        EntityManager em = requestToJoinGroupFacade.getEntityManager();
        Query q = em.createNamedQuery("RequestToJoinGroup.findByState");
        requests = q.setParameter("state", "New").getResultList();
        return requests;
    }
    public String accept(){
        req.setState("Accepted");
        requestToJoinGroupFacade.edit(req);
        //System.out.println(req.getGroupId());
        

        
        
        
        
        return"requestToJoinPage.xhtml?faces-redirect=true";
    }
        public String reject(){
        req.setState("Rejected");
        requestToJoinGroupFacade.edit(req);
        
        return"requestToJoinPage.xhtml?faces-redirect=true";
    }

}
