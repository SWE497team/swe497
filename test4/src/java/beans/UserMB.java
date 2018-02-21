/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.AcademicUser;
import entity.Admin;
import entity.Instructor;
import entity.Parent;
import entity.Student;
import entity.User;
import facades.AcademicUserFacade;
import facades.AdminFacade;
import facades.InstructorFacade;
import facades.ParentFacade;
import facades.StudentFacade;

import facades.UserFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;


/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Named(value = "userMB")
@RequestScoped
public class UserMB {


    private User user;
    private Admin admin;
    private Student student;
    private Instructor instructor;
    private Parent parent;
    private AcademicUser academicUser;
    
    @EJB
    private UserFacade userFacade;
    @EJB
    private AdminFacade adminFacade;
    @EJB
    private StudentFacade studentFacade;
    @EJB
    private InstructorFacade instructorFacade;
    @EJB
    private ParentFacade parentFacade;
    @EJB
    private AcademicUserFacade academicUserFacade;
    
    
    private boolean hasError = false;

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public AcademicUser getAcademicUser() {
        return academicUser;
    }

    public void setAcademicUser(AcademicUser academicUser) {
        this.academicUser = academicUser;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
  
    
    public boolean hasError(){
        return this.hasError;
    }

    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Creates a new instance of userMB
     */
    public UserMB() {
        
        user = new User();
        admin = new Admin();
        student = new Student();
        instructor = new Instructor();
        parent = new Parent();
        academicUser = new AcademicUser();
    }
    
  
    
     
        
        public String createUser() throws SQLException {
            int id;
            EntityManager em = userFacade.getEntityManager();
            Query q = em.createNamedQuery("User.findByUsername");

    
   
       
        if(!q.setParameter("username",user.getUsername()).getResultList().isEmpty()){
            this.hasError = true;
            return "signup1";
        }
                        Calendar calendar = Calendar.getInstance();
                        java.sql.Date creation = new java.sql.Date(calendar.getTime().getTime());
                 user.setCreationDate(creation);
                 userFacade.create(user);
                    id = user.getId();
                    
                   

        if(user.getUserType().equals("admin")){
            admin.setUser(id);
            adminFacade.create(admin);
        }
        else if(user.getUserType().equals("parent")){
            parent.setUser(id);
            parentFacade.create(parent);
        
        }
        else if(user.getUserType().equals("student")){
            academicUser.setUser(id);
            academicUserFacade.create(academicUser);
            student.setUser(id);
            studentFacade.create(student);
        }
        else if(user.getUserType().equals("instructor")){
            academicUser.setUser(id);
            academicUserFacade.create(academicUser);
            instructor.setUser(id);
            instructorFacade.create(instructor);
        }
  
        
        return "login.xhtml?faces-redirect=true";
    }


  public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login.xhtml?faces-redirect=true";
	}
  
    public List<User> getUserList() {
            List<User> list = new ArrayList<User>();

                list =  userFacade.findAll();
                return list;
    }
    
    
   
        
        public String login (){
            HttpSession session = null;
            EntityManager em = userFacade.getEntityManager();
            Query q =  em.createNamedQuery("User.loginadmin");
            
       
        if(!q.setParameter("username",user.getUsername()).setParameter("password", user.getPassword()).getResultList().isEmpty()){
                   session = SessionUtils.getSession();
		   session.setAttribute("username", user.getUsername()); 
           
                   return"adminPage.xhtml?faces-redirect=true";}
        
        else { 
             EntityManager em2 = userFacade.getEntityManager();
             Query q2 =  em2.createNamedQuery("User.loginstudent");
             if(!q2.setParameter("username",user.getUsername()).setParameter("password", user.getPassword()).getResultList().isEmpty()){
                   session = SessionUtils.getSession();
		   session.setAttribute("username", user.getUsername()); 
                
                   return"studentPage.xhtml?faces-redirect=true";}
             else{
                 EntityManager em3 =  userFacade.getEntityManager();
                 Query q3 =  em3.createNamedQuery("User.logininstructor");
                 if(!q3.setParameter("username",user.getUsername()).setParameter("password", user.getPassword()).getResultList().isEmpty()){
                   session = SessionUtils.getSession();
		   session.setAttribute("username", user.getUsername()); 
                   
                  return"instructorPage.xhtml?faces-redirect=true";}
             else  {
                     
                 EntityManager em4 =  userFacade.getEntityManager();
                 Query q4 =  em4.createNamedQuery("User.loginparent");
                 if(!q4.setParameter("username",user.getUsername()).setParameter("password", user.getPassword()).getResultList().isEmpty()){
                   session = SessionUtils.getSession();
		   session.setAttribute("username", user.getUsername()); 
                  return"parentPage.xhtml?faces-redirect=true";}
                     
                 }
             }
                 
        
        
        
        
        
        }
        
        

             
        
        
          
            return "login.xhtml?faces-redirect=true";
                }

}
