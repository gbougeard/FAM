package org.fam.jsf.bootstrap;

import org.fam.ejb.session.FamClubFacade;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: mask_hot
 * Date: 21/03/12
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
@Startup
@Singleton
public class MySingleton {
    @Inject
    FamClubFacade ejb;

    @Inject
    Logger LOGGER;

    @PostConstruct
    void init() {
        LOGGER.debug("PostConstruct");
        LOGGER.debug("Nb de clubs : " + ejb.findAll().size());
    }
}
