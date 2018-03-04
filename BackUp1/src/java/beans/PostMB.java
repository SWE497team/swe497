/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Matrial;
import entities.Post;
import entities.Question;
import facades.MatrialFacade;
import facades.PostFacade;
import facades.QuestionFacade;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@ManagedBean(name = "postMB")
@SessionScoped
public class PostMB {
    
    
    @ManagedProperty(value = "#{groupMB}")
    private GroupMB groupMB;
        
    @ManagedProperty(value = "#{userMB}")
    private UserMB userMB;
    
    private Post post;
    private Question question;
    private Matrial matrial;
    @EJB
    private PostFacade postFacade;
    
    @EJB
    private QuestionFacade questionFacade;
    
    @EJB
    private MatrialFacade matrialFacade;
    
    


    public PostMB() {
        post = new Post();
        question = new Question();
        matrial = new Matrial();
    }

    public UserMB getUserMB() {
        return userMB;
    }

    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }

    public GroupMB getGroupMB() {
        return groupMB;
    }

    public void setGroupMB(GroupMB groupMB) {
        this.groupMB = groupMB;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Matrial getMatrial() {
        return matrial;
    }

    public void setMatrial(Matrial matrial) {
        this.matrial = matrial;
    }
    
    public String postQuestion(){
        post.setPostType("Question");
        
        Calendar calendar = Calendar.getInstance();
        java.sql.Date creation = new java.sql.Date(calendar.getTime().getTime());
        post.setPostDate(creation);
        
        post.setAcademicUseruser(userMB.getAcademicUser());
        
        post.setGrouppId(groupMB.getGroup());
        post.setState("New");
        postFacade.create(post);
        question.setPost(post);
        questionFacade.create(question);
        
        
        return"studentPage.xhtml?faces-redirect=true";
        
        
    }
    
    
    
}
