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
import entities.Moderator;
import entities.ModeratorPK;
import static entities.Moderator_.moderatorPK;
import entities.RequestToJoinGroup;
import facades.GroupMembersFacade;

import facades.GrouppFacade;
import facades.ModeratorFacade;
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
    
    
    @ManagedProperty(value = "#{moderatorMB}")
    private ModeratorMB moderatorMB;
    
 

    @EJB
    private GrouppFacade groupFacade;

    @EJB
    private ModeratorFacade moderatorFacade;
    
    @EJB
    private GroupMembersFacade groupMembersFacade;

    @EJB
    private RequestToJoinGroupFacade requestToJoinGroupFacade;

    private RequestToJoinGroup request;
    private Moderator moderator;
    private ModeratorPK moderatorPK;
    private GroupMembers groupMembers;
    private GroupMembersPK groupMembersPK;

    private boolean hasError = false;

    private Groupp group;
    private List<Groupp> groups;
    private List<Groupp> allgroups;
    private List<Groupp> groupsMod;
    private List<Moderator> moderators;
    private boolean showDo=false;
    private boolean showUndo=false;

    public void doAction(){
      showDo=true;
    }

    public void undoAction(){
      showUndo=true;
    }

    public boolean isShowDo() {
        return showDo;
    }

    public void setShowDo(boolean showDo) {
        this.showDo = showDo;
    }

    public boolean isShowUndo() {
        return showUndo;
    }

    public void setShowUndo(boolean showUndo) {
        this.showUndo = showUndo;
    }
    


    public GroupMB() {
        moderatorPK = new ModeratorPK();
        moderator = new Moderator();
        groupMembersPK = new GroupMembersPK();
        groupMembers = new GroupMembers();
        group = new Groupp();
        request = new RequestToJoinGroup();

    }

    public UserMB getUserMB() {
        return userMB;
    }

    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }

    public ModeratorMB getModeratorMB() {
        return moderatorMB;
    }

    public void setModeratorMB(ModeratorMB moderatorMB) {
        this.moderatorMB = moderatorMB;
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

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
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
        group.setInstructorUser(userMB.getUser().getAcademicUser().getInstructor());
        groupFacade.create(group);
        
        moderator.setGroupp(group);
        moderator.setAcademicUser(userMB.getAcademicUser());
        moderator.setCreationDate(creation);
        moderatorPK.setAcademicUseruser(userMB.getUser().getAcademicUser().getUser());
        moderatorPK.setGrouppId(group.getId());
        moderator.setModeratorPK(moderatorPK);
        moderatorFacade.create(moderator);
        
            
        groupMembers.setCreationDate(creation);
        groupMembers.setAcademicUser(userMB.getUser().getAcademicUser());
        groupMembers.setGroupp(group);

        groupMembersPK.setAcademicUseruser(userMB.getUser().getAcademicUser().getUser());
        groupMembersPK.setGroupId(group.getId());

        groupMembers.setGroupMembersPK(groupMembersPK);
        groupMembersFacade.create(groupMembers);
       
        
        return "instructorPage.xhtml?faces-redirect=true";

    }

 

    public List<Groupp> getGroups() {

        EntityManager em = groupFacade.getEntityManager();
        Query q = em.createNamedQuery("Groupp.listGroups");
        groups = q.setParameter("instructor", userMB.getUser().getAcademicUser().getInstructor()).getResultList();
       if(!groups.isEmpty())
        return groups;
       return null;
    }

    public void setGroups(List<Groupp> groups) {
        this.groups = groups;
    }

    public List<Groupp> getAllgroups() {
        allgroups = groupFacade.findAll();
        

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
    
        public List<Groupp> getModeratorGroups() {

        EntityManager em = groupFacade.getEntityManager();
        Query q = em.createNamedQuery("Groupp.listGroups");
        groupsMod = q.setParameter("instructor", userMB.getInstructor()).getResultList();
        
        EntityManager em2 = moderatorFacade.getEntityManager();
        Query q2 = em2.createNamedQuery("Moderator.findByAcademicUseruser");
        moderators = q2.setParameter("academicUseruser", userMB.getInstructor().getUser()).getResultList();
        
        if(!groupsMod.isEmpty() && !moderators.isEmpty() ){
  group = groupsMod.get(0);
  moderator = moderators.get(0);
           if(group.getInstructorUser().getUser().equals(moderator.getAcademicUser().getUser()) )
               
                 return groupsMod;
}
            
            
         return null;
        }
                              
        

            
    
        }


