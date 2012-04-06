package org.fam.jsf.bean;

import org.fam.ejb.model.FamPlayer;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gbougeard
 * Date: 06/04/12
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */
@Named
public class AnswerUngiven implements Answer<FamPlayer> {
    private List<FamPlayer> lstPlayer;
    private List<FamPlayer> lstSelected = new ArrayList<FamPlayer>();
    private FamPlayer selected;

    @Override
    public String addSelected() {
        lstPlayer.remove(selected);
        lstSelected.add(selected);
        return null;
    }

    @Override
    public FamPlayer getSelected() {
        return selected;
    }

    @Override
    public void setSelected(FamPlayer entity) {
        selected = entity;
    }

    @Override
    public List<FamPlayer> getLstAnswer() {
        return lstPlayer;
    }

    @Override
    public void setLstAnswer(List<FamPlayer> list) {
        lstPlayer = list;
    }

    @Override
    public List<FamPlayer> getLstSelected() {
        return lstSelected;
    }

    @Override
    public void setLstSelected(List<FamPlayer> list) {
        lstSelected = list;
    }
}
