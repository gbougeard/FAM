package org.fam.jsf.controller;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamPlace;
import org.fam.ejb.session.FamPlaceFacade;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

@ManagedBean(name = "famPlaceController")
@ViewScoped
public class FamPlaceController extends AbstractController<FamPlace> implements Serializable {

    private MapModel simpleModel = new DefaultMapModel();
    //
    @EJB
    private FamPlaceFacade ejbFacade;

    public FamPlaceController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
//        findAll();
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    @Override
    public FamPlace getSelected() {
        if (current == null) {
            current = new FamPlace();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamPlaceFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdPlace();
        return "pretty:editPlace";
    }

    @Override
    public String prepareView() {
        id = current.getIdPlace();
        return "pretty:viewPlace";
    }

    @Override
    public String prepareList() {
        return "pretty:listPlace";
    }

    @Override
    public String prepareCreate() {
        current = new FamPlace();
        selectedItemIndex = -1;
        return "pretty:createPlace";
    }

    //    public String geocode() {
//        
//        final String fullAdress = current.getFullAddress();
//        geocoder.getLatLng(fullAdress, new LatLngCallback() {
//            
//            @Override
//            public void onFailure() {
//                LogUtil.log(fullAdress, Level.SEVERE, null);
//            }
//            
//            @Override
//            public void onSuccess(LatLng point) {
//                
//                current.setLatitude(point.getLatitude());
//                current.setLongitude(point.getLongitude());
//                
//            }
//        });
//        return null;
//    }
    public MapModel getSimpleModel() {
        if (current.getLatitude() == null) {
            simpleModel = null;
        } else {
            LatLng coord = new LatLng(current.getLatitude().doubleValue(), current.getLongitude().doubleValue());
            simpleModel.addOverlay(new Marker(coord, current.getLibPlace()));
        }

        return simpleModel;
    }

    public String getLatLong() throws IOException {
        String details;
//        if (current.getFullAddress().isEmpty()) {
        StringBuilder builder = new StringBuilder();
        builder.append(current.getAddress()).append(",").append(current.getZipcode()).append(",").append(current.getCity());
        details = builder.toString();
//        } else {
//            details = current.getFullAddress();
//        }
        System.out.println("adresse:" + details);

        Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(details).getGeocoderRequest();
//       setLanguage("en")
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        LogUtil.log(geocoderResponse.toString(), Level.OFF, null);
        List<GeocoderResult> results = geocoderResponse.getResults();
        for (GeocoderResult result : results) {
            LogUtil.log(result.getFormattedAddress(), Level.OFF, null);
            LogUtil.log(result.getGeometry().getLocation().getLat().toPlainString(), Level.OFF, null);
            LogUtil.log(result.getGeometry().getLocation().getLng().toString(), Level.OFF, null);
            current.setLatitude(result.getGeometry().getLocation().getLat());
            current.setLongitude(result.getGeometry().getLocation().getLng());
        }

        LatLng coord = new LatLng(current.getLatitude().doubleValue(), current.getLongitude().doubleValue());
        if (coord == null) {
            LogUtil.log("coord NULL", Level.WARNING, null);
            coord = new LatLng(42.0, 1.43);
        }
        if (simpleModel == null) {
            LogUtil.log("simpleModel NULL", Level.WARNING, null);
            simpleModel = new DefaultMapModel();
        }
        simpleModel.addOverlay(new Marker(coord, "Test"));

//        String msg = "OK!";
//        FacesContext.getCurrentInstance().addMessage(null, 
//                    new FacesMessage(FacesMessage.SEVERITY_INFO, this.getClass().getName(), msg));
        return null;//"map";
    }

//    @FacesConverter(forClass = FamPlace.class)
//    public static class FamPlaceControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            FamPlaceController controller = (FamPlaceController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "famPlaceController");
//            return controller.ejbFacade.find(getKey(value));
//        }
//
//        Long getKey(String value) {
//            Long key;
//            key = Long.valueOf(value);
//            return key;
//        }
//
//        String getStringKey(Long value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        @Override
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof FamPlace) {
//                FamPlace o = (FamPlace) object;
//                return getStringKey(o.getIdPlace());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FamPlaceController.class.getName());
//            }
//        }
//    }
}
