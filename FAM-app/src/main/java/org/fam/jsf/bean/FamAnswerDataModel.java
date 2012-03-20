/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import org.fam.ejb.model.FamAnswer;
import org.fam.ejb.session.FamAnswerFacade;
import org.primefaces.model.SelectableDataModel;

import javax.ejb.EJB;
import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * @author mask_hot
 */
public class FamAnswerDataModel extends ListDataModel<FamAnswer> implements SelectableDataModel<FamAnswer> {

    @EJB
    FamAnswerFacade ejb;

    public FamAnswerDataModel() {
    }

    public FamAnswerDataModel(List<FamAnswer> data) {
        super(data);
    }

    @Override
    public FamAnswer getRowData(String rowKey) {
        return rowKey.equals("null") ? null : ejb.find(Long.parseLong(rowKey));
    }

    @Override
    public Object getRowKey(FamAnswer item) {
        return item.getId();
    }

}
