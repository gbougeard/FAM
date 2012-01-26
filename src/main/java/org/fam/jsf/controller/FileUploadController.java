/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.jsf.controller;

import org.fam.ejb.common.LogUtil;
import org.primefaces.event.FileUploadEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

/**
 * @author mask_hot
 */
@Named(value = "fileUploadController")
@RequestScoped
public class FileUploadController {

    @Inject
    FamPlayerController famPlayerController;
    //Primitives
    private static final int BUFFER_SIZE = 6124;
    private String folderToUpload;
    private final static String TMP_PATH = "/images/players/tmp/";

    /**
     * Creates a new instance of FileUploadController
     */
    public FileUploadController() {
    }

    @PostConstruct
    private void postConstruct() {
        LogUtil.log(this.getClass() + " - postConstruct", Level.INFO, null);
    }

    @PreDestroy
    private void preDestroy() {
        LogUtil.log(this.getClass() + " - preDestroy", Level.INFO, null);
    }

    public void handleFileUpload(FileUploadEvent event) {
        LogUtil.log("handleFileUpload - " + event.getFile().getFileName(), Level.INFO, null);

        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        File result = new File(extContext.getRealPath(TMP_PATH
                + event.getFile().getFileName()));

        System.out.println(extContext.getRealPath(TMP_PATH
                + event.getFile().getFileName()));

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            System.out.println("Succesful, " + event.getFile().getFileName()
                    + " is uploaded.");

            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName()
                    + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            famPlayerController.setTmpImgUrl(TMP_PATH + event.getFile().getFileName());

        } catch (IOException e) {
            LogUtil.log("Erreur!", Level.SEVERE, e);

            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "The files were not uploaded!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }
}
