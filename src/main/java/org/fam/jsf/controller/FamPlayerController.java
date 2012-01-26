package org.fam.jsf.controller;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.ejb.common.LogUtil;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.*;
import org.fam.ejb.session.FamPlayerFacade;
import org.fam.jsf.bean.util.JsfUtil;
import org.fam.jsf.cache.CacheBean;
import org.primefaces.event.SlideEndEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.servlet.ServletContext;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

@ManagedBean(name = "famPlayerController")
@ViewScoped
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamPlayerController extends AbstractController<FamPlayer> implements Serializable {

    public static final String PRETTY_ID_LIST = "listPlayer";
    public static final String PRETTY_ID_CREATE = "createPlayer";
    public static final String PRETTY_ID_EDIT = "editPlayer";
    public static final String PRETTY_ID_VIEW = "viewPlayer";
    //
    @EJB
    private FamPlayerFacade ejbFacade;
    //
    private DualListModel<FamPosition> positions;
    @Inject
    private CacheBean cacheBean;
    //
    private FamPlayerSeason currentProfile;
    //
    private String tmpImgUrl;
    //    private final static String PLAYERS_PHOTO_PATH = "/images/players/";
    private final static String DEFAULT_PHOTO = "default.png";
    private CroppedImage croppedImage;
    private String newImageName;

    public FamPlayerController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    @Override
    public FamPlayer getSelected() {
        if (current == null) {
            current = new FamPlayer();
            selectedItemIndex = -1;
            tmpImgUrl = "";
        }
        return current;
    }

    @Override
    public FamPlayerFacade getFacade() {
        return ejbFacade;
    }

    public String prepareForCreate() {
        current = new FamPlayer();
        id = null;
        selectedItemIndex = -1;
        tmpImgUrl = "";

        List<FamPosition> source = new ArrayList<FamPosition>();
        source.addAll(cacheBean.getListPosition());

        List<FamPosition> target = new ArrayList<FamPosition>();

        positions = new DualListModel<FamPosition>(source, target);

        currentProfile = new FamPlayerSeason();
        currentProfile.setFamPlayer(current);
        currentProfile.setFamSeason(cacheBean.getCurrentSeason());
        currentProfile.setFamPlayerProfile(new FamPlayerProfile());

        current.setFamPlayerSeasons(new ArrayList<FamPlayerSeason>());
        current.getFamPlayerSeasons().add(currentProfile);

        return null;
    }

    @Override
    public String prepareCreate() {
        prepareForCreate();
        return getPrettyId(PRETTY_ID_CREATE);
    }

    @Override
    public String prepareEdit() {
        LogUtil.log(this.getClass() + " - prepareEdit", Level.INFO, null);
//        preparePositionForEdit();
        id = current.getIdPlayer();
        return getPrettyId(PRETTY_ID_EDIT);
    }

    public String preparePositionForEdit() {
        List<FamPosition> source = new ArrayList<FamPosition>();
        source.addAll(cacheBean.getListPosition());

        if (current.getFamPlayerPositionList() != null) {
            source.removeAll(current.getFamPlayerPositionList());
        }

        List<FamPosition> target = new ArrayList<FamPosition>();
        if (current.getFamPlayerPositionList() != null) {
            for (FamPlayerPosition item : current.getFamPlayerPositionList()) {
                target.add(item.getFamPosition());
            }
//            target.addAll(current.getFamPlayerPositionList());
        }

        positions = new DualListModel<FamPosition>(source, target);

        tmpImgUrl = "";

        return null;
    }

    public String loadActionForEdit() {
        LogUtil.log(this.getClass() + " - loadActionForEdit", Level.INFO, null);
        super.loadAction();
        return preparePositionForEdit();
    }

    @Override
    public String prepareView() {
        id = current.getIdPlayer();
        return getPrettyId(PRETTY_ID_VIEW);
    }

    @Override
    public String prepareList() {
        return getPrettyId(PRETTY_ID_LIST);
    }

    private void getTargetPositions() {
        List<FamPosition> set = new ArrayList<FamPosition>(positions.getTarget());
        LogUtil.log("create target size " + positions.getTarget().size(), Level.OFF, null);
//        List<FamPlayerPosition> list = new ArrayList<FamPlayerPosition>();
        Integer i = 1;
        if (current.getFamPlayerPositionList() == null) {
            current.setFamPlayerPositionList(new ArrayList<FamPlayerPosition>());
        }
        current.getFamPlayerPositionList().clear();

        for (FamPosition item : set) {
            FamPlayerPosition pp = new FamPlayerPosition();
            pp.setFamPlayer(current);
            pp.setFamPosition(item);
            pp.setNumOrder(i);

            current.getFamPlayerPositionList().add(pp);
            i++;
        }
//        return list;
    }

    @Override
    public String create() {
        LogUtil.log("create", Level.OFF, null);
        try {

            getTargetPositions();

            LogUtil.log("avant create joueur " + currentProfile, Level.OFF, null);

            // on sauvegarde le joueur
            getFacade().create(current);

            // On lui crée une photo à partir de la photo par défaut
            StringBuilder sb = new StringBuilder();
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            sb.append(servletContext.getRealPath("")).append(File.separator).append("images").append(File.separator).append("players").append(File.separator).append(current.getIdPlayer()).append(".png");
            String newFileName = sb.toString();

            LogUtil.log("file Rename " + newFileName, Level.OFF, null);

//            File file = new File(DEFAULT_PHOTO);
//            Boolean bRes = file.renameTo(new File(newFileName));
            sb = new StringBuilder();
            sb.append(servletContext.getRealPath("")).append(File.separator).append("images").append(File.separator).append("players").append(File.separator).append(DEFAULT_PHOTO);

            copyFile(sb.toString(), newFileName);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamPlayerCreated"));
            return prepareView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    @Override
    public String update() {
        try {
            getTargetPositions();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FamPlayerUpdated"));
            return prepareView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public DualListModel<FamPosition> getPositions() {
        return positions;
    }

    public void setPositions(DualListModel<FamPosition> positions) {
        LogUtil.log("setPositions " + positions, Level.OFF, null);
        if (positions != null) {
            this.positions = positions;
        }
    }

    public void onSlideEnd(SlideEndEvent event) {
        FacesMessage msg = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public FamPlayerSeason getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(FamPlayerSeason currentProfile) {
        this.currentProfile = currentProfile;
    }

    public CacheBean getCacheBean() {
        return cacheBean;
    }

    public void setCacheBean(CacheBean cacheBean) {
        this.cacheBean = cacheBean;
    }

    public String getTmpImgUrl() {
        return tmpImgUrl;
    }

    public void setTmpImgUrl(String tmpImgUrl) {
        this.tmpImgUrl = tmpImgUrl;
    }

    public Boolean getShowPhoto() {
        return (tmpImgUrl != null && !tmpImgUrl.isEmpty());
    }

    public String getUrlPhoto() {
        StringBuilder sb = new StringBuilder();
//        sb.append(PLAYERS_PHOTO_PATH);
        if (current.getIdPlayer() != null) {
            sb.append(current.getIdPlayer()).append(".png");
        } else {
            sb.append(DEFAULT_PHOTO);
        }
        return sb.toString();
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public String crop() {
        if (croppedImage == null) {
            return null;
        }

//        setNewImageName(getRandomImageName());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        StringBuilder sb = new StringBuilder();
        sb.append(servletContext.getRealPath("")).append(File.separator).append("images").append(File.separator).append("players").append(File.separator).append(current.getIdPlayer()).append(".png");
        String newFileName = sb.toString();

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();

            File file = new File(croppedImage.getOriginalFilename());
            file.delete();
        } catch (FileNotFoundException e) {
            LogUtil.log("Cropping", Level.SEVERE, e);
        } catch (IOException e) {
            LogUtil.log("Cropping", Level.SEVERE, e);
        }

        return null;
    }

    public String getNewImageName() {
        return newImageName;
    }

    public void setNewImageName(String newImageName) {
        this.newImageName = newImageName;
    }

    private void copyFile(String src, String dest) {
        FileChannel in = null; // canal d'entrée
        FileChannel out = null; // canal de sortie

        try {
            // Init
            in = new FileInputStream(src).getChannel();
            out = new FileOutputStream(dest).getChannel();

            // Copie depuis le in vers le out
            in.transferTo(0, in.size(), out);
        } catch (Exception e) {
//             LogUtil.log("Erreur!", Level.SEVERE, e); // n'importe quelle exception
            LogUtil.log("copyFile", Level.SEVERE, e);
        } finally { // finalement on ferme
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LogUtil.log("copyFile Close IN", Level.SEVERE, e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LogUtil.log("copyFile Close OUT", Level.SEVERE, e);
                }
            }
        }
    }
}
