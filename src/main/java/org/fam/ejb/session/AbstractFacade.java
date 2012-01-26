/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LogUtil;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.exception.FamException;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.logging.Level;

/**
 *
 * @param <T>
 * @author gbougear
 */
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public abstract class AbstractFacade<T> {

    @Inject
    private Event<T> event;
    //
    private Class<T> entityClass;
    private CriteriaQuery<T> criteriaQuery;

    /**
     * 
     * @param entityClass
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * 
     * @return
     */
    protected abstract EntityManager getEntityManager();

    /**
     * 
     * @return
     */
    public CriteriaBuilder getCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    /**
     * 
     * @return
     */
    public CriteriaQuery<T> getCriteriaQuery() {
//        if (criteriaQuery == null) {
        criteriaQuery = getCriteriaBuilder().createQuery(entityClass);
//        }
        return criteriaQuery;
    }

    /**
     * 
     * @return
     */
    public Root<T> getRoot() {
        return getCriteriaQuery().from(entityClass);
    }

    /**
     * 
     */
    protected abstract void genData();

    /**
     * 
     * @param entity
     */
    public void create(T entity) {
        try {
            getEntityManager().persist(entity);

        }
        catch (ConstraintViolationException e) {
            handleConstraintViolation(e);
            LogUtil.log(entity.toString(), Level.SEVERE, e);
            throw new FamException("Create - ContraintViolation", e);
        }
        catch (EntityExistsException e) {
            // if the entity already exists. (If the entity already exists, the EntityExistsException may be thrown when the persist operation is invoked, or the EntityExistsException or another PersistenceException may be thrown at flush or commit time.)
            LogUtil.log("EntityExistsException", Level.SEVERE, e);
        }
        catch (IllegalArgumentException e) {
            // if the instance is not an entity
            LogUtil.log("IllegalArgumentException", Level.SEVERE, e);
        }
        catch (TransactionRequiredException e) {
            // if invoked on a container-managed entity manager of type  PersistenceContextType.TRANSACTION and there is no transaction
            LogUtil.log("TransactionRequiredException", Level.SEVERE, e);
        }
        catch (Exception e) {
            LogUtil.log(entity.toString(), Level.SEVERE, e);
        }

        event.fire(entity);
    }

    /**
     * 
     * @param entity
     */
    public void edit(T entity) {
        try {
            getEntityManager().merge(entity);
        }
        catch (ConstraintViolationException e) {
            handleConstraintViolation(e);
            LogUtil.log(entity.toString(), Level.WARNING, e);
            throw new FamException("Create - ContraintViolation", e);
        }
        catch (Exception e) {
            LogUtil.log(entity.toString(), Level.SEVERE, e);
        }
        event.fire(entity);
    }

    /**
     * 
     * @param entity
     */
    public void remove(T entity) {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
        }
        catch (IllegalArgumentException e) {
            // if the instance is not an entity
            LogUtil.log("IllegalArgumentException", Level.SEVERE, e);
        }
        catch (TransactionRequiredException e) {
            // if invoked on a container-managed entity manager of type  PersistenceContextType.TRANSACTION and there is no transaction
            LogUtil.log("TransactionRequiredException", Level.SEVERE, e);
        }
        catch (Exception e) {
            LogUtil.log(entity.toString(), Level.SEVERE, e);
        }
        event.fire(entity);
    }

    /**
     * 
     */
    public void truncate() {
        getCriteriaQuery().select(getRoot());
        List<T> list = getEntityManager().createQuery(getCriteriaQuery()).getResultList();
        for (T item : list) {
            this.remove(item);
        }
    }

    /**
     * 
     * @param id
     * @return
     */
    public T find(Object id) {
        T result = null;
        try {
            result = getEntityManager().find(entityClass, id);
        }
        catch (IllegalArgumentException e) {
            // if the instance is not an entity
            LogUtil.log("IllegalArgumentException", Level.SEVERE, e);
        }
        return result;
    }

    /**
     * 
     * @return
     */
    public List<T> findAll() {
//        CriteriaQuery<T> cq = getCriteriaQuery();
//        cq.select(getRoot());
//        
//        return getEntityManager().createQuery(getCriteriaQuery()).getResultList();

        Query query = getEntityManager().createNamedQuery(entityClass.getSimpleName() + ".findAll");

        List<T> result = new ArrayList<T>();
        try {
            result = query.getResultList();
        }
        catch (NoResultException e) {
            //- if there is no result}
            LogUtil.log("NoResultException", Level.SEVERE, e);
        }
        catch (NonUniqueResultException e) {
            //- if more than one result
             LogUtil.log("NonUniqueResultException", Level.SEVERE, e);
        }
        catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
             LogUtil.log("IllegalStateException", Level.SEVERE, e);
        }
        catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
             LogUtil.log("QueryTimeoutException", Level.SEVERE, e);
        }
        catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
             LogUtil.log("TransactionRequiredException", Level.SEVERE, e);
        }
        catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
             LogUtil.log("PessimisticLockException", Level.SEVERE, e);
        }
        catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
             LogUtil.log("LockTimeoutException", Level.SEVERE, e);
        }
        catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
             LogUtil.log("PersistenceException", Level.SEVERE, e);
        }

        return result;
    }

    /**
     * 
     * @param range
     * @return
     */
    public List<T> findRange(int[] range) {

        getCriteriaQuery().select(getRoot());
        Query q = getEntityManager().createQuery(getCriteriaQuery());
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * 
     * @return
     */
    public int count() {
        CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        cq.select(getCriteriaBuilder().count(getRoot()));
        Query q = getEntityManager().createQuery(cq);

        return ( (Long) q.getSingleResult() ).intValue();
    }

    /**
     * 
     * @param attributes
     * @return
     */
    public List<T> findByAttributes(final Map<String, Object> attributes) {
        List<T> results;
        //set up the Criteria query
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<T> cq = getCriteriaQuery();
        Root<T> foo = getRoot();

        List<Predicate> predicates = new ArrayList<Predicate>();
        for (String s : attributes.keySet()) {
            if (foo.get(s) != null) {
//                predicates.add(cb.like((Expression) foo.get(s), "%" + attributes.get(s) + "%" ));
                predicates.add(cb.equal(foo.get(s), attributes.get(s)));
            }
        }
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<T> q = getEntityManager().createQuery(cq);

        results = q.getResultList();
        return results;
    }

    /**
     *
     * @return
     */
    public Class getEntityClass() {
        return entityClass;
    }

    /**
     *
     * @param entityClass
     */
    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    private void handleConstraintViolation(ConstraintViolationException cve) {
        LogUtil.log("handleConstraintViolation", Level.INFO, null);
        Set<ConstraintViolation<?>> cvs = cve.getConstraintViolations();
        for (ConstraintViolation<?> cv : cvs) {
            System.out.println("------------------------------------------------");
            System.out.println("Violation: " + cv.getMessage());
            System.out.println("Entity: " + cv.getRootBeanClass().getSimpleName());
            // The violation occurred on a leaf bean (embeddable)
            if (cv.getLeafBean() != null && cv.getRootBean() != cv.getLeafBean()) {
                System.out.println("Embeddable: "
                        + cv.getLeafBean().getClass().getSimpleName());
            }
            System.out.println("Attribute: " + cv.getPropertyPath());
            System.out.println("Invalid value: " + cv.getInvalidValue());
        }
    }

    /**
     * 
     * @param cq
     * @return
     */
    public List<T> findByCriteria(CriteriaQuery cq) {
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    /**
     * 
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return
     */
    public List<T> findAllLazy(final int first,
            final int pageSize,
            final String sortField,
            final boolean sortOrder,
            final Map<String, String> filters) {

        StringBuilder sb = new StringBuilder();
        sb.append("first ").append(first).append(" pageSize ").append(pageSize);
        LogUtil.log(sb.toString(), Level.INFO, null);
        sb = new StringBuilder();
        sb.append("sortField ").append(sortField).append(" sortOrder ").append(sortOrder);
        LogUtil.log(sb.toString(), Level.INFO, null);
        for (String s : filters.keySet()) {
            sb = new StringBuilder();
            sb.append("filtre ").append(s).append(" value ").append(filters.get(s));
            LogUtil.log(sb.toString(), Level.INFO, null);
        }

        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<T> cq = getCriteriaQuery();
//        cq.select(cq.from(entityClass));
        Root root = getRoot();

        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate criteria = cb.conjunction();
        for (String s : filters.keySet()) {
//            sb.append("filtre ").append(s).append(" type ").append(root.get(s).getJavaType());
//            LogUtil.log(sb.toString(), Level.INFO, null);
            sb = new StringBuilder();
            sb.append("filtre ").append(s).append(" value ").append(filters.get(s));
            LogUtil.log(sb.toString(), Level.INFO, null);

            sb = new StringBuilder();
            sb.append("%").append(filters.get(s).toUpperCase()).append("%");
//            Expression<String> literal = cb.upper(cb.literal(sb.toString()));

            if (s.contains(".")) {
                LogUtil.log("filtre  avec . " + sb.toString(), Level.INFO, null);
                String[] splitted = s.split(".");
                LogUtil.log("splitted " + splitted, Level.INFO, null);
                StringTokenizer token = new StringTokenizer(s);
                LogUtil.log("tokens " + token.countTokens(), Level.INFO, null);
//                predicates.add(cb.like(cb.upper(cb.literal(s)), str));
                predicates.add(cb.like(cb.upper(root.get(token.nextToken(".")).get(token.nextToken("."))), sb.toString()));


            } else if (root.get(s) != null) {
                LogUtil.log("filtre " + s + " - " + sb.toString(), Level.INFO, null);
                predicates.add(cb.like(cb.upper(root.get(s)), sb.toString()));

            }
        }
        cq.where(predicates.toArray(new Predicate[]{}));
//        for (String s : filters.keySet()) {
//            sb = new StringBuilder();
//            
//            if (s.contains(".")) {
//                sb.append("%").append(filters.get(s).toUpperCase()).append("%");
//                String[] splitted = s.split(".");
//                
//                ParameterExpression<String> p = cb.parameter(String.class, filters.get(s));
//                criteria = cb.and(criteria, cb.like(cb.upper(root.get(splitted[0]).get(splitted[1])), p));
//                
//            } else {
//                sb.append("%").append(filters.get(s).toUpperCase()).append("%");
//                ParameterExpression<String> p = cb.parameter(String.class, filters.get(s));
//                criteria = cb.and(criteria, cb.like(cb.upper(root.get(s)), p));
//            }
//
//        }

        if (( sortField != null ) && ( sortField.isEmpty() == false )) {
            if (sortOrder) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }

        Query q = getEntityManager().createQuery(cq);

        q.setFirstResult(first);
        q.setMaxResults(pageSize);

        List<T> list = q.getResultList();
        LogUtil.log("nb result " + list.size(), Level.INFO, null);

        return list;
    }

    /**
     * 
     * @param filters
     * @return
     */
    public int countLazy(final Map<String, String> filters) {
//        Root<T> root = getRoot();
        Root root = getRoot();
        CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        cq.select(getCriteriaBuilder().count(root));
        CriteriaBuilder cb = getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<Predicate>();

        for (String s : filters.keySet()) {
//            if (root.get(s) != null) {
//                predicates.add(cb.like((Expression) root.get(s), "%" + filters.get(s) + "%"));
//            }

            StringBuilder sb = new StringBuilder();
            sb.append("%").append(filters.get(s).toUpperCase()).append("%");
            Expression<String> literal = cb.upper(cb.literal(sb.toString()));

            if (s.contains(".")) {
                LogUtil.log("filtre  avec . " + sb.toString(), Level.INFO, null);
                String[] splitted = s.split(".");
                LogUtil.log("splitted " + splitted, Level.INFO, null);
                StringTokenizer token = new StringTokenizer(s);
                LogUtil.log("tokens " + token.countTokens(), Level.INFO, null);
//                predicates.add(cb.like(cb.upper(cb.literal(s)), str));
                predicates.add(cb.like(cb.upper(root.get(token.nextToken(".")).get(token.nextToken("."))), sb.toString()));


            } else if (root.get(s) != null) {
                predicates.add(cb.like(cb.upper(root.get(s)), sb.toString()));

            }
        }

        cq.where(predicates.toArray(new Predicate[]{}));

        Query q = getEntityManager().createQuery(cq);

        return ( (Long) q.getSingleResult() ).intValue();
    }
}
