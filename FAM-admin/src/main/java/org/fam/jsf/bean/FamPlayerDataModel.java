/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.bean;

import org.fam.ejb.model.FamPlayer;
import org.fam.ejb.session.FamPlayerFacade;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import java.util.List;

/**
 * @author mask_hot
 */
public class FamPlayerDataModel extends ListDataModel<FamPlayer> implements SelectableDataModel<FamPlayer> {

    @Inject
    FamPlayerFacade ejb;

    public FamPlayerDataModel() {
    }

    public FamPlayerDataModel(List<FamPlayer> data) {
        super(data);
    }

    @Override
    public FamPlayer getRowData(String rowKey) {
//       return rowKey.equals("null") ? null : org.fam.ejb.find(Long.parseLong(rowKey));
        List<FamPlayer> cars = (List<FamPlayer>) getWrappedData();

        for (FamPlayer car : cars) {
            if (car.getId().equals(Long.parseLong(rowKey))) {
                return car;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(FamPlayer item) {
        return item.getId();
    }
}
