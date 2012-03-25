package org.fam.jsf.controller;

import org.fam.common.cdi.Loggable;
import org.fam.ejb.model.FamFormation;
import org.fam.ejb.model.FamFormationItem;
import org.fam.ejb.model.FamTypMatch;
import org.fam.ejb.session.FamFormationFacade;
import org.fam.jsf.bean.CanvasFormationItem;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "famFormationController")
@ViewScoped
@Loggable
public class FamFormationController extends AbstractController<FamFormation> {

    @Inject
    private Logger LOGGER;
    @Inject
    private FamFormationFacade ejbFacade;
    //
    private List<StreamedContent> lstGraphicText = new ArrayList<StreamedContent>();
    private List<String> lstNumber = new ArrayList<String>();
    private List<CanvasFormationItem> lstTarget = new ArrayList<CanvasFormationItem>();

    public FamFormationController() {
    }

    @PostConstruct
    private void postConstruct() {

//        genTarget();
    }

    @PreDestroy
    private void preDestroy() {

    }

    @Override
    public FamFormation getSelected() {
        if (current == null) {
            current = new FamFormation();
            selectedItemIndex = -1;
        }
        return current;
    }

    @Override
    public FamFormationFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String prepareEdit() {
        id = current.getIdFormation();
        return "pretty:editFormation";
    }

    @Override
    public String prepareView() {
        id = current.getIdFormation();
        return "pretty:viewFormation";
    }

    @Override
    public String prepareList() {
        return "pretty:listFormation";
    }

    @Override
    public String prepareCreate() {
        loadForCreate();
        return "pretty:createFormation";
    }

    @Override
    public String create() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("create ");
        }
        return super.create();
    }

    @Override
    public String update() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("update ");
        }
        return super.update();
    }

    @Override
    public String destroy() {
        return super.destroy();
    }

    @Override
    protected void findAll() {
        super.findAll();
    }

    public void genTarget() {

        lstTarget.clear();
//        if (current.getFamTypMatch() != null) {
        for (int i = 1;
             i <= 30;
             i++) {
            CanvasFormationItem item = new CanvasFormationItem();
            item.setStrIdx(String.format("%d", i));
//            lstTarget.add(String.format("%d", i));

            if (current != null && current.getFamFormationItemList() != null) {
                for (FamFormationItem fi : current.getFamFormationItemList()) {
                    if (fi.getCoord().equals(Integer.valueOf(i))) {
                        item.setFamFormationItem(fi);
                        break;
                    }
                }
            }
            lstTarget.add(item);
        }
    }

    @Override
    public String loadAction() {
        super.loadAction();
        genTarget();
        return null;
    }

    //    @Override
//    public String update() {
//        // On reporte les coordonnées sur la liste des items de la formation
//        for (CanvasFormationItem cfi : lstTarget) {
//            if (cfi.getFamFormationItem() != null) {
//                for (FamFormationItem fi : current.getFamFormationItemList()) {
//                    if (cfi.getFamFormationItem().getIdFormationItem().equals(fi.getIdFormationItem())) {
//                        fi.setCoord(cfi.getFamFormationItem().getCoord());
//                        break;
//                    }
//                }
//            }
//        }
//
//        return super.update();
//    }
    public void onDrop(DragDropEvent event) {

//        Player player = (Player) event.getData();  
//  
//        selectedPlayers.add(player);  
//        String idTarget = (String) event.getData();
        CanvasFormationItem item = (CanvasFormationItem) event.getData();


        String idCoord = event.getDropId();
        idCoord = idCoord.substring(idCoord.indexOf("trgField"));
        idCoord = idCoord.substring(idCoord.indexOf(':') + 1, idCoord.lastIndexOf(':'));
        Integer coord = Integer.parseInt(idCoord) + 1;

        FamFormationItem fi = item.getFamFormationItem();

        for (CanvasFormationItem cfi : lstTarget) {
            if (cfi.getStrIdx().equals(String.format("%d", coord))) {
                // on swappe les formationItem
                FamFormationItem fiTemp = cfi.getFamFormationItem();
                Integer oldCoord = null;
                if (fi != null) {
                    oldCoord = fi.getCoord();
                }

                cfi.setFamFormationItem(fi);
                item.setFamFormationItem(fiTemp);

                cfi.getFamFormationItem().setCoord(coord);
                if (oldCoord != null && item.getFamFormationItem() != null) {
                    item.getFamFormationItem().setCoord(oldCoord);
                }
                break;
            }
        }


//        JsfUtil.addInfoMessage("ID N°" + event.getDragId(), "ID Position:" + event.getDropId());
//        JsfUtil.addInfoMessage("N°" + num, "Position:" + coord);
//        Integer oldCoord = null;
//        for (FamFormationItem fi : current.getFamFormationItemList()) {
//            if (fi.getNumItem().equals(num)) {
//                oldCoord = fi.getCoord();
//                fi.setCoord(coord);
//                break;
//            }
//        }
//        // On intervertit si besoin
//        for (FamFormationItem fi : current.getFamFormationItemList()) {
//            if (fi.getCoord().equals(coord) && !fi.getNumItem().equals(num)) {
//                fi.setCoord(oldCoord);
//                break;
//            }
//        }
//        genTarget();

//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(player.getName() + " added", "Position:" + event.getDropId()));  
    }

    public void genImage(SelectEvent event) {

        lstGraphicText.clear();
        lstNumber.clear();
        FamTypMatch typMatch = (FamTypMatch) event.getObject();
        current.setFamFormationItemList(new ArrayList<FamFormationItem>());
        for (int i = 1;
             i <= typMatch.getNbPlayer();
             i++) {
//                BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
//                Graphics2D g2 = bufferedImg.createGraphics();
//                g2.drawString(String.format("%d", i), 0, 10);
//                ByteArrayOutputStream os = new ByteArrayOutputStream();
//                try {
//                    ImageIO.write(bufferedImg, "png", os);
//                } catch (IOException ex) {
//                    Logger.getLogger(FamFormationController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                lstGraphicText.add(new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png"));
            lstNumber.add(String.format("%d", i));
            FamFormationItem item = new FamFormationItem();
            item.setNumItem(i);
            current.getFamFormationItemList().add(item);
        }
    }

    public void genImage() {

        lstGraphicText.clear();
        lstNumber.clear();
//        FamTypMatch typMatch = (FamTypMatch) event.getObject();

        current.setFamFormationItemList(new ArrayList<FamFormationItem>());
        for (int i = 1;
             i <= 11;
             i++) {
            BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufferedImg.createGraphics();
            g2.drawString(String.format("%d", i), 0, 10);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ImageIO.write(bufferedImg, "png", os);
            } catch (IOException e) {
                LOGGER.error("genImage", e);
            }
            lstGraphicText.add(new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png"));


            lstNumber.add(String.format("%d", i));
            FamFormationItem item = new FamFormationItem();
            item.setNumItem(i);
            item.setCoord(i * 2);
            item.setFamFormation(current);
            current.getFamFormationItemList().add(item);

        }

    }

    public void loadForCreate() {
        current = new FamFormation();
        selectedItemIndex = -1;
        genImage();
        genTarget();
    }

    public List<StreamedContent> getLstGraphicText() {
        return lstGraphicText;
    }

    public void setLstGraphicText(List<StreamedContent> lstGraphicText) {
        this.lstGraphicText = lstGraphicText;
    }

    public List<String> getLstNumber() {
        return lstNumber;
    }

    public void setLstNumber(List<String> lstNumber) {
        this.lstNumber = lstNumber;
    }

    public List<CanvasFormationItem> getLstTarget() {
        return lstTarget;
    }

    public void setLstTarget(List<CanvasFormationItem> lstTarget) {
        this.lstTarget = lstTarget;
    }

    public void setLOGGER(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }

    public void setEjbFacade(FamFormationFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
}
