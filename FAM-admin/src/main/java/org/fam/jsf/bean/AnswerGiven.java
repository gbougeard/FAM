package org.fam.jsf.bean;

import org.fam.ejb.model.FamAnswer;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gbougeard
 * Date: 06/04/12
 * Time: 23:42
 * To change this template use File | Settings | File Templates.
 */
@Named
public class AnswerGiven implements Answer<FamAnswer> {

    private List<FamAnswer> lstAnswer;
    private FamAnswer selected;
    private List<FamAnswer> lstSelected = new ArrayList<FamAnswer>();

    @Override
    public String addSelected() {
        lstAnswer.remove(selected);
        lstSelected.add(selected);
        return null;
    }

    @Override
    public FamAnswer getSelected() {
        return selected;
    }

    @Override
    public void setSelected(FamAnswer entity) {
        selected = entity;
    }

    @Override
    public List<FamAnswer> getLstAnswer() {
        return lstAnswer;
    }

    @Override
    public void setLstAnswer(List<FamAnswer> list) {
        lstAnswer = list;
    }

    @Override
    public List<FamAnswer> getLstSelected() {
        return lstSelected;
    }

    @Override
    public void setLstSelected(List<FamAnswer> list) {
        lstSelected = list;
    }
}
