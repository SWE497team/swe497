/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.RequestToLink;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Stateless
public class RequestToLinkFacade extends AbstractFacade<RequestToLink> {

    @PersistenceContext(unitName = "test4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequestToLinkFacade() {
        super(RequestToLink.class);
    }
    
}
