package org.fam.jsf.bean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gbougeard
 * Date: 06/04/12
 * Time: 23:40
 * To change this template use File | Settings | File Templates.
 */
public interface Answer<T> {
    public String addSelected();

    public T getSelected();

    public void setSelected(T entity);

    public List<T> getLstAnswer();

    public void setLstAnswer(List<T> list);

    public List<T> getLstSelected();

    public void setLstSelected(List<T> list);
}
