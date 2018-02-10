/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Responsibility;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author abdulsalam-alhomaidhi
 */
@Stateless
public class ResponsibilityFacade extends AbstractFacade<Responsibility> {

    @PersistenceContext(unitName = "test4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResponsibilityFacade() {
        super(Responsibility.class);
    }
    
}
