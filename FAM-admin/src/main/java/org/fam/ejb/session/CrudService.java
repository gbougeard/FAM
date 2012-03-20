package org.fam.ejb.session;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mask_hot
 * Date: 17/03/12
 * Time: 00:35
 * To change this template use File | Settings | File Templates.
 */
public interface CrudService<T> {
    public T create(T t);

    public T find(Class type, Object id);

    public T update(T t);

    public void delete(Class type, Object id);

    public List findWithNamedQuery(String queryName);

    public List findWithNamedQuery(String queryName, int resultLimit);

    public List findWithNamedQuery(String namedQueryName, Map parameters);

    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit);
}
