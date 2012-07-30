/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.fam.ejb.session;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamFormation;

/**
 * @author gbougear
 */
@Named
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamFormationFacade extends AbstractFacade<FamFormation> {
//    @PersistenceContext//(unitName = "FAM-test-ejbPU")
//    private EntityManager em;

    /**
     * @return
     */
//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }

    /**
     *
     */
    public FamFormationFacade() {

        super(FamFormation.class);
    }

    /**
     *
     */
    @Override
    public void genData() {

        for (int i = 0;
             i < 10;
             i++) {
            FamFormation item = new FamFormation();
            item.setLibFormation("Formation_" + i);
            item.setCodFormation("F" + i);
            this.create(item);
        }
    }

    @Override
    public void edit(FamFormation entity) {

        super.edit(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void create(FamFormation entity) {

        super.create(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void remove(FamFormation entity) {

        super.remove(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public FamFormation find(Object id) {

        return super.find(id);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<FamFormation> findAllLazy(int first, int pageSize, String sortField, boolean sortOrder, Map<String, String> filters) {

        return super.findAllLazy(first, pageSize, sortField, sortOrder, filters);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
