/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Group1;
import entity.Instructor;
import entity.User;
import facades.Group1Facade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Named(value = "groupMB")
@RequestScoped
public class GroupMB {

    /**
     * Creates a new instance of GroupMB
     */
    
    private Instructor instructor;
    private User user;
    private Group1 group;
    
   
    @EJB
    private Group1Facade groupFacade;
    private boolean hasError = false;

    public GroupMB() {
        instructor = new Instructor();
        user = new User();
        group = new Group1();
        
    }

    public Group1 getGroup() {
        return group;
    }

    public void setGroup(Group1 group) {
        this.group = group;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public boolean hasError(){
        return this.hasError;
    }
    
    public String createGroup() {

        EntityManager em = groupFacade.getEntityManager();
        Query q = em.createNamedQuery("Group1.findByName");

        if(!q.setParameter("name",group.getName()).getResultList().isEmpty()){
            this.hasError = true;
            return "createGroup.xhtml?faces-redirect=true";
        }
                        Calendar calendar = Calendar.getInstance();
                        java.sql.Date creation = new java.sql.Date(calendar.getTime().getTime());
                        group.setCreationDate(creation);
                 
                 groupFacade.create(group);
  
 
                 
                 
      
        return "instructorPage.xhtmlfaces-redirect=true";
                
    
    
}
}
