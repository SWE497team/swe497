/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.User;

import facades.UserFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Named(value = "userMB")
@RequestScoped
public class UserMB {

    
    private User user;
    @EJB
    private UserFacade userFacade;

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
    }
        public String createUser() throws SQLException {
         
        userFacade.create(user);
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swe496", "root","1122qqwwaass");
        Statement ps = con.createStatement();
        
        if(user.getUserType().equals("admin"))
        ps.executeUpdate("INSERT INTO Admin (user)  VALUE ('"+user.getId()+"')");
        
        else if(user.getUserType().equals("parent"))
        ps.executeUpdate("INSERT INTO Parent (user)  VALUE ('"+user.getId()+"')");
        

        else if(user.getUserType().equals("student") || user.getUserType().equals("instructor"))
        ps.executeUpdate("INSERT INTO AcademicUser (user)  VALUE ('"+user.getId()+"')");
  
        
        return "signup1";
    }
        
        
        
        public void ss(){
            user.checkValidUser();
        }
     
            
        
        
        
        
    

}
