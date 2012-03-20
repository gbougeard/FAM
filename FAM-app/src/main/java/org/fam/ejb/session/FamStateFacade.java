/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamCountry;
import org.fam.ejb.model.FamState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @author gbougear
 */
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamStateFacade extends AbstractFacade<FamState> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamStateFacade.class);

    //    @PersistenceContext ////(unitName = "FAM-test-ejbPU")
//    private EntityManager em;
    //
    @EJB
    FamCountryFacade ejbCountry;

    /**
     * @return
     */
//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }

    /**
     *
     */
    public FamStateFacade() {
        super(FamState.class);
    }

    /**
     *
     */
    @Override
//    @GET // HTTP's GET verb/operation
//    @Path("") // specializes the path with a parameter
    public void genData() {
        truncate();
        try {
            String strFile = "/META-INF/PaysRegionsDepartementsVilles/states.xml";
//            File file = new File(strFile);

            InputStream ips = this.getClass().getResourceAsStream(strFile);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(ips);
            doc.getDocumentElement().normalize();
//            LOGGER.debug("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("state");
//            LOGGER.debug("Information of all states");

            for (int s = 0;
                 s < nodeLst.getLength();
                 s++) {

                FamState state = new FamState();

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element fstElmnt = (Element) fstNode;
                    NodeList elmntLst = fstElmnt.getElementsByTagName("name_state");
                    Element elmnt = (Element) elmntLst.item(0);
                    NodeList nl = elmnt.getChildNodes();
//                    LOGGER.debug("name_state : " + ((Node) nl.item(0)).getNodeValue());
                    state.setLibState(((Node) nl.item(0)).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("name_state_uppercase");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
//                    LOGGER.debug("name_state_uppercase : " + ((Node) nl.item(0)).getNodeValue());
                    state.setLibUpper(((Node) nl.item(0)).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("state_slug");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
//                    LOGGER.debug("state_slug : " + ((Node) nl.item(0)).getNodeValue());
                    state.setLibLower(((Node) nl.item(0)).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("id_country");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
//                    LOGGER.debug("id_country : " + ((Node) nl.item(0)).getNodeValue());
                    FamCountry country = ejbCountry.find(Long.parseLong(nl.item(0).getNodeValue()));
                    state.setFamCountry(country);

                    elmntLst = fstElmnt.getElementsByTagName("id_region");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
//                    LOGGER.debug("id_region : " + ((Node) nl.item(0)).getNodeValue());
                    state.setIdState(Long.parseLong(nl.item(0).getNodeValue()));
                    state.setCodState(nl.item(0).getNodeValue());

                    create(state);

                }

            }

            ips.close();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }

        LOGGER.info(count() + " states created.");
    }
}
