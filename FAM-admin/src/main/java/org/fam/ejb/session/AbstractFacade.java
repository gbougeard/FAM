/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.exception.FamException;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;

/**
 * @param <T>
 * @author gbougear
 */
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class AbstractFacade<T> implements Serializable {

    @Inject
    private Logger LOGGER;

    @PersistenceContext//(unitName = "FAM-test-ejbPU")
    private EntityManager em;
    //
//    @Inject
//    @LoggedIn
//    protected FamUser currentUser;


    /**
     * @return
     */
    protected EntityManager getEntityManager() {

        return em;
    }

    @Inject
    private Event<T> event;
    //
    private Class<T> entityClass;
    private CriteriaQuery<T> criteriaQuery;

    /**
     * @param entityClass
     */
    public AbstractFacade(Class<T> entityClass) {

        this.entityClass = entityClass;
    }

    protected AbstractFacade() {

    }

    @PostConstruct
    private void init() {

        LOGGER.debug("Init");
    }

    /**
     * @return
     */
//    protected abstract EntityManager getEntityManager();

    /**
     * @return
     */
    public CriteriaBuilder getCriteriaBuilder() {

        return getEntityManager().getCriteriaBuilder();
    }

    /**
     * @return
     */
    public CriteriaQuery<T> getCriteriaQuery() {
//        if (criteriaQuery == null) {
        criteriaQuery = getCriteriaBuilder().createQuery(entityClass);
//        }
        return criteriaQuery;
    }

    /**
     * @return
     */
    public Root<T> getRoot() {

        return getCriteriaQuery().from(entityClass);
    }

    /**
     *
     */
    protected void genData() {

    }

    /**
     * @param entity
     */
    public void create(T entity) {

        try {
            getEntityManager().persist(entity);

        } catch (ConstraintViolationException e) {
            handleConstraintViolation(e);
            LOGGER.error(entity.toString(), e);
            throw new FamException("Create - ContraintViolation", e);
        } catch (EntityExistsException e) {
            // if the entity already exists. (If the entity already exists, the EntityExistsException may be thrown when the persist operation is invoked, or the EntityExistsException or another PersistenceException may be thrown at flush or commit time.)
            LOGGER.error("EntityExistsException", e);
        } catch (IllegalArgumentException e) {
            // if the instance is not an entity
            LOGGER.error("IllegalArgumentException", e);
        } catch (TransactionRequiredException e) {
            // if invoked on a container-managed entity manager of type  PersistenceContextType.TRANSACTION and there is no transaction
            LOGGER.error("TransactionRequiredException", e);
        } catch (Exception e) {
            LOGGER.error(entity.toString(), e);
        }

        event.fire(entity);
    }

    /**
     * @param entity
     */
    public void edit(T entity) {

        try {
            getEntityManager().merge(entity);
        } catch (ConstraintViolationException e) {
            handleConstraintViolation(e);
            LOGGER.error(entity.toString(), Level.WARNING, e);
            throw new FamException("Create - ContraintViolation", e);
        } catch (Exception e) {
            LOGGER.error(entity.toString());
        }
        event.fire(entity);
    }

    /**
     * @param entity
     */
    public void remove(T entity) {

        try {
            getEntityManager().remove(getEntityManager().merge(entity));
        } catch (IllegalArgumentException e) {
            // if the instance is not an entity
            LOGGER.error("IllegalArgumentException");
        } catch (TransactionRequiredException e) {
            // if invoked on a container-managed entity manager of type  PersistenceContextType.TRANSACTION and there is no transaction
            LOGGER.error("TransactionRequiredException");
        } catch (Exception e) {
            LOGGER.error(entity.toString());
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
     * @param id
     * @return
     */
    public T find(Object id) {

        T result = null;
        try {
            result = getEntityManager().find(entityClass, id);
        } catch (ConstraintViolationException e) {
            handleConstraintViolation(e);
            LOGGER.error("ConstraintViolationException", e);
        } catch (IllegalArgumentException e) {
            // if the instance is not an entity
            LOGGER.error("IllegalArgumentException");
        }
        return result;
    }

    /**
     * @return
     */
    public List<T> findAll() {
//        CriteriaQuery<T> cq = getCriteriaQuery();
//        cq.select(getRoot());
//        
//        return getEntityManager().createQuery(getCriteriaQuery()).getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(entityClass.getSimpleName() + ".findAll");
        }

        Query query = getEntityManager().createNamedQuery(entityClass.getSimpleName() + ".findAll");

        List<T> result = new ArrayList<T>();
        try {
            result = query.getResultList();
        } catch (ConstraintViolationException e) {
            handleConstraintViolation(e);
            LOGGER.error("ConstraintViolationException", e);
        } catch (NoResultException e) {
            //- if there is no result}
            LOGGER.error("NoResultException");
        } catch (NonUniqueResultException e) {
            //- if more than one result
            LOGGER.error("NonUniqueResultException");
        } catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
            LOGGER.error("IllegalStateException");
        } catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
            LOGGER.error("QueryTimeoutException");
        } catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
            LOGGER.error("TransactionRequiredException");
        } catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
            LOGGER.error("PessimisticLockException");
        } catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
            LOGGER.error("LockTimeoutException");
        } catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
            LOGGER.error("PersistenceException");
        }

        return result;
    }

    /**
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
     * @return
     */
    public int count() {

        CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        cq.select(getCriteriaBuilder().count(getRoot()));
        Query q = getEntityManager().createQuery(cq);

        return ((Long) q.getSingleResult()).intValue();
    }

    /**
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
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<T> q = getEntityManager().createQuery(cq);

        results = q.getResultList();
        return results;
    }

    /**
     * @return
     */
    public Class getEntityClass() {

        return entityClass;
    }

    /**
     * @param entityClass
     */
    public void setEntityClass(Class entityClass) {

        this.entityClass = entityClass;
    }

    protected void handleConstraintViolation(ConstraintViolationException cve) {

        LOGGER.error("handleConstraintViolation");
        Set<ConstraintViolation<?>> cvs = cve.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> cv : cvs) {
            sb.append("\n------------------------------------------------");
            sb.append("\nViolation: ").append(cv.getMessage());
            sb.append("\nEntity: ").append(cv.getRootBeanClass().getSimpleName());
            // The violation occurred on a leaf bean (embeddable)
            if (cv.getLeafBean() != null && cv.getRootBean() != cv.getLeafBean()) {
                sb.append("\nEmbeddable: ").append(cv.getLeafBean().getClass().getSimpleName());
            }
            sb.append("\nAttribute: ").append(cv.getPropertyPath());
            sb.append("\nInvalid value: ").append(cv.getInvalidValue());
        }

        LOGGER.error(sb.toString());
    }

    /**
     * @param cq
     * @return
     */
    public List<T> findByCriteria(CriteriaQuery cq) {

        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    /**
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(sb.toString());
        }
        sb = new StringBuilder();
        sb.append("sortField ").append(sortField).append(" sortOrder ").append(sortOrder);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(sb.toString());
        }
        for (String s : filters.keySet()) {
            sb = new StringBuilder();
            sb.append("filtre ").append(s).append(" value ").append(filters.get(s));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(sb.toString());
            }
        }

        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<T> cq = getCriteriaQuery();
//        cq.select(cq.from(entityClass));
        Root root = getRoot();

        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate criteria = cb.conjunction();
        for (String s : filters.keySet()) {
//            sb.append("filtre ").append(s).append(" type ").append(root.get(s).getJavaType());
//           LOGGER.debug(sb.toString());
            sb = new StringBuilder();
            sb.append("filtre ").append(s).append(" value ").append(filters.get(s));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(sb.toString());
            }

            sb = new StringBuilder();
            sb.append("%").append(filters.get(s).toUpperCase()).append("%");
//            Expression<String> literal = cb.upper(cb.literal(sb.toString()));

            if (s.contains(".")) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("filtre  avec . " + sb.toString());
                }
                String[] splitted = s.split(".");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("splitted " + Arrays.toString(splitted));
                }
                StringTokenizer token = new StringTokenizer(s);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("tokens " + token.countTokens());
                }
//                predicates.add(cb.like(cb.upper(cb.literal(s)), str));
                predicates.add(cb.like(cb.upper(root.get(token.nextToken(".")).get(token.nextToken("."))), sb.toString()));


            } else {
                if (root.get(s) != null) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("filtre " + s + " - " + sb.toString());
                    }
                    predicates.add(cb.like(cb.upper(root.get(s)), sb.toString()));

                }
            }
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
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

        if ((sortField != null) && (!sortField.isEmpty())) {
            if (sortOrder) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }

        Query q = getEntityManager().createQuery(cq);

        q.setFirstResult(first);
        q.setMaxResults(pageSize);

        List<T> list = new ArrayList<T>();
        try {
            list = q.getResultList();
        } catch (ConstraintViolationException e) {
            handleConstraintViolation(e);
            LOGGER.error("ConstraintViolationException", e);
        } catch (Exception e) {
            LOGGER.error("OUCH!", e);
        }

        LOGGER.debug("nb result " + list.size());

        return list;
    }

    /**
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
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("filtre  avec . " + sb.toString());
                }
                String[] splitted = s.split(".");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("splitted " + Arrays.toString(splitted));
                }
                StringTokenizer token = new StringTokenizer(s);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("tokens " + token.countTokens());
                }
//                predicates.add(cb.like(cb.upper(cb.literal(s)), str));
                predicates.add(cb.like(cb.upper(root.get(token.nextToken(".")).get(token.nextToken("."))), sb.toString()));


            } else {
                if (root.get(s) != null) {
                    predicates.add(cb.like(cb.upper(root.get(s)), sb.toString()));

                }
            }
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        Query q = getEntityManager().createQuery(cq);

        return ((Long) q.getSingleResult()).intValue();
    }


    public void setLOGGER(Logger LOGGER) {

        this.LOGGER = LOGGER;
    }

    public int getCount() {

        return count();
    }
}
