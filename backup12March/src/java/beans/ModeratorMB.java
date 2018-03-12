/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Moderator;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@ManagedBean(name = "moderatorMB")
@SessionScoped
public class ModeratorMB implements Serializable {
    
    private Moderator moderator;

    
    
  
    public ModeratorMB() {
        moderator = new Moderator();
    }

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }
    
    
    
}
