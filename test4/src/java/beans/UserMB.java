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
import java.sql.ResultSet;
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
    private boolean hasError = false;
    
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
    }
    
     
        
        public String createUser() throws SQLException {
         
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swe496", "root","1122qqwwaass");
        Statement ps = con.createStatement();
        
        ps.execute("SELECT * FROM user WHERE username='" + user.getUsername() + "'");
        ResultSet result = ps.getResultSet();
        if(result.next()){
            this.hasError = true;
            return "signup1";
        }
                userFacade.create(user);

        if(user.getUserType().equals("admin"))
        ps.executeUpdate("INSERT INTO Admin (user)  VALUE ('"+user.getId()+"')");
        
        else if(user.getUserType().equals("parent"))
        ps.executeUpdate("INSERT INTO Parent (user)  VALUE ('"+user.getId()+"')");
        

        else if(user.getUserType().equals("student")){
        ps.executeUpdate("INSERT INTO AcademicUser (user)  VALUE ('"+user.getId()+"')");
        ps.executeUpdate("INSERT INTO student (user)  VALUE ('"+user.getId()+"')");
        }
        else if(user.getUserType().equals("instructor")){
        ps.executeUpdate("INSERT INTO AcademicUser (user)  VALUE ('"+user.getId()+"')");
        ps.executeUpdate("INSERT INTO instructor (user)  VALUE ('"+user.getId()+"')");
        }
  
        
        return "login";
    }


        
   public String CheckValidUser(){
       try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swe496", "root","1122qqwwaass");
            Statement stmt=con.createStatement();
            ResultSet rs =stmt.executeQuery("select * from user where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'");

         if(rs.next())
   
             return "signup1.xhtml?faces-redirect=true";
         
         else 
             return "Couldn't found user register";
       }
            
        catch(Exception e) {
            }
       return "fail";
      }   
   
   
   
   
   

}
