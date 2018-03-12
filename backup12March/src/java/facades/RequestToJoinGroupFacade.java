/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.RequestToJoinGroup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Stateless
public class RequestToJoinGroupFacade extends AbstractFacade<RequestToJoinGroup> {

    @PersistenceContext(unitName = "test4PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public RequestToJoinGroupFacade() {
        super(RequestToJoinGroup.class);
    }
    
}
