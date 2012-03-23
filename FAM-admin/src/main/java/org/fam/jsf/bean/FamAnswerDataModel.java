/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import lombok.Getter;
import lombok.Setter;
import org.fam.ejb.model.FamAnswer;
import org.fam.ejb.session.FamAnswerFacade;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import java.util.List;

/**
 * @author mask_hot
 */
@Getter
@Setter
public class FamAnswerDataModel extends ListDataModel<FamAnswer> implements SelectableDataModel<FamAnswer> {

    @Inject
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
