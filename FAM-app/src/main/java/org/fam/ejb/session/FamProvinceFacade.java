/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.common.interceptor.AuditInterceptor;
import org.fam.common.interceptor.LoggingInterceptor;
import org.fam.ejb.model.FamProvince;
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
import javax.persistence.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gbougear
 */
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamProvinceFacade extends AbstractFacade<FamProvince> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamProvinceFacade.class);

    //    @PersistenceContext ////(unitName = "FAM-test-ejbPU")
//    private EntityManager em;
    @EJB
    FamStateFacade ejbState;

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
    public FamProvinceFacade() {
        super(FamProvince.class);
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
            String strFile = "/META-INF/PaysRegionsDepartementsVilles/provinces.xml";
//            File file = new File(strFile);

            InputStream ips = this.getClass().getResourceAsStream(strFile);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(ips);
            doc.getDocumentElement().normalize();
//            LOGGER.debug("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("province");
//            LOGGER.debug("Information of all provinces");

            for (int s = 0;
                 s < nodeLst.getLength();
                 s++) {

                FamProvince province = new FamProvince();

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element fstElmnt = (Element) fstNode;
                    NodeList elmntLst = fstElmnt.getElementsByTagName("name_province");
                    Element elmnt = (Element) elmntLst.item(0);
                    NodeList nl = elmnt.getChildNodes();
//                    LOGGER.debug("name_province : " + ((Node) nl.item(0)).getNodeValue());
                    province.setLibProvince(((Node) nl.item(0)).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("name_province_uppercase");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
//                    LOGGER.debug("name_province_uppercase : " + ((Node) nl.item(0)).getNodeValue());
                    province.setLibUpper(((Node) nl.item(0)).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("province_slug");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
//                    LOGGER.debug("province_slug : " + ((Node) nl.item(0)).getNodeValue());
                    province.setLibLower(((Node) nl.item(0)).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("id_region");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
//                    LOGGER.debug("id_region : " + ((Node) nl.item(0)).getNodeValue());
                    FamState state = ejbState.find(Long.parseLong(nl.item(0).getNodeValue()));
                    province.setFamState(state);

                    elmntLst = fstElmnt.getElementsByTagName("id_departement");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
//                    LOGGER.debug("id_departement : " + ((Node) nl.item(0)).getNodeValue());
                    province.setIdProvince(Long.parseLong(nl.item(0).getNodeValue()));

                    elmntLst = fstElmnt.getElementsByTagName("code");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
//                    LOGGER.debug("code : " + ((Node) nl.item(0)).getNodeValue());
                    province.setCodProvince(nl.item(0).getNodeValue());

                    create(province);

                }

            }

            ips.close();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }

        LOGGER.info(count() + " provinces created.");
    }

    public FamProvince findByCod(String cod) {
        Query query = getEntityManager().createNamedQuery("FamProvince.findByCodProvince");
        query.setParameter(FamProvince.PROP_COD, cod);

        List<FamProvince> result = new ArrayList<FamProvince>();
        try {
            result = query.getResultList();
        } catch (NoResultException e) {
            //- if there is no result}
            LOGGER.error("Error", e);
        } catch (NonUniqueResultException e) {
            //- if more than one result
            LOGGER.error("Error", e);
        } catch (IllegalStateException e) {
            //- if called for a Java Persistence query language UPDATE or DELETE statement
            LOGGER.error("Error", e);
        } catch (QueryTimeoutException e) {
            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
            LOGGER.error("Error", e);
        } catch (TransactionRequiredException e) {
            // - if a lock mode has been set and there is no transaction
            LOGGER.error("Error", e);
        } catch (PessimisticLockException e) {
            //- if pessimistic locking fails and the transaction is rolled back
            LOGGER.error("Error", e);
        } catch (LockTimeoutException e) {
            // - if pessimistic locking fails and only the statement is rolled back
            LOGGER.error("Error", e);
        } catch (PersistenceException e) {
            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
            LOGGER.error("Error", e);
        }

        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }
}
