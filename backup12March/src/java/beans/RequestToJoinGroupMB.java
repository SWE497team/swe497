/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.AcademicUser;
import entities.GroupMembers;
import entities.GroupMembersPK;
import entities.Groupp;
import entities.RequestToJoinGroup;
import entities.Student;
import facades.GroupMembersFacade;
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
    @EJB
    private GroupMembersFacade groupMembersFacade;

    private RequestToJoinGroup req;
    private List<RequestToJoinGroup> requests;
    private GroupMembers groupMembers;
    private GroupMembersPK groupMembersPK;
    private List<RequestToJoinGroup> memberOf;

    public RequestToJoinGroupMB() {
        req = new RequestToJoinGroup();
        groupMembers = new GroupMembers();
        groupMembersPK = new GroupMembersPK();

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

    public GroupMembers getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(GroupMembers groupMembers) {
        this.groupMembers = groupMembers;
    }

    public GroupMembersPK getGroupMembersPK() {
        return groupMembersPK;
    }

    public void setGroupMembersPK(GroupMembersPK groupMembersPK) {
        this.groupMembersPK = groupMembersPK;
    }

    public String sendReq() {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date creation = new java.sql.Date(calendar.getTime().getTime());

        req.setCreationDate(creation);
        req.setState("New");
        req.setGroupId(groupMB.getGroup());
       // req.setAcademicUseruser(userMB.getUser().getAcademicUser());
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

    public String accept() {
        req.setState("Accepted");
        requestToJoinGroupFacade.edit(req);

        Calendar calendar = Calendar.getInstance();
        java.sql.Date creation = new java.sql.Date(calendar.getTime().getTime());
        
        groupMembers.setCreationDate(creation);
        groupMembers.setAcademicUser(req.getAcademicUseruser());
        groupMembers.setGroupp(req.getGroupId());

        groupMembersPK.setAcademicUseruser(req.getAcademicUseruser().getUser());
        groupMembersPK.setGroupId(req.getGroupId().getId());

        groupMembers.setGroupMembersPK(groupMembersPK);
        groupMembersFacade.create(groupMembers);
        return "instructorPage.xhtml?faces-redirect=true";
    }

    public String reject() {
        req.setState("Rejected");
        requestToJoinGroupFacade.edit(req);

        return "instructorPage.xhtml?faces-redirect=true";
    }

    public List<RequestToJoinGroup> getMemberOf() {
        EntityManager em = requestToJoinGroupFacade.getEntityManager();
        Query q = em.createNamedQuery("RequestToJoinGroup.MemberOf");
        memberOf = q.setParameter("academicUser", userMB.getUser().getAcademicUser()).getResultList();

        return memberOf;
    }

}
