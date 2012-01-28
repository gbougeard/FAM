/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.common.log.LogUtil;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamCountry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamCountryFacade extends AbstractFacade<FamCountry> {

    @PersistenceContext ////(unitName = "FAM-test-ejbPU")
    private EntityManager em;

    /**
     * 
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * 
     */
    public FamCountryFacade() {
        super(FamCountry.class);
    }


//    @Override
//    public List<FamCountry> findAll() {
//        Query query = getEntityManager().createNamedQuery("FamCountry.findAll");
//
//        List<FamCountry> result = new ArrayList<FamCountry>();
//        try {
//            result = query.getResultList();
//        } catch (NoResultException e) {
//            //- if there is no result}
//        } catch (NonUniqueResultException e) {
//            //- if more than one result
//        } catch (IllegalStateException e) {
//            //- if called for a Java Persistence query language UPDATE or DELETE statement
//        } catch (QueryTimeoutException e) {
//            // - if the query execution exceeds the query timeout value set and only the statement is rolled back
//        } catch (TransactionRequiredException e) {
//            // - if a lock mode has been set and there is no transaction
//        } catch (PessimisticLockException e) {
//            //- if pessimistic locking fails and the transaction is rolled back
//        } catch (LockTimeoutException e) {
//            // - if pessimistic locking fails and only the statement is rolled back
//        } catch (PersistenceException e) {
//            // - if the query execution exceeds the query timeout value set and the transaction is rolled back
//        }
//
//        return result;
//    }
    
    

    /**
     * 
     */
    @Override
//    @GET // HTTP's GET verb/operation
//    @Path("") // specializes the path with a parameter
    public void genData() {
        try {

            truncate();
            //csv file containing data
            String strFile = "/META-INF/PaysRegionsDepartementsVilles/country.xml";

            //create BufferedReader to read csv file

            InputStream ips = this.getClass().getResourceAsStream(strFile);
//
//            InputStreamReader ipsr = new InputStreamReader(ips);
//            BufferedReader br = new BufferedReader(ipsr);
//            String strLine = "";
//            StringTokenizer st = null;
//            int lineNumber = 0, tokenNumber = 0;
//
//            //read comma separated file line by line
//            while (( strLine = br.readLine() ) != null) {
//                lineNumber++;
//
//                //break comma separated line using ","
//                st = new StringTokenizer(strLine, ";");
//
//                FamCountry country = new FamCountry();
//
//                while (st.hasMoreTokens()) {
//                    //display csv values
//                    tokenNumber++;
//                    String token = st.nextToken();
//                    System.out.println("Line # " + lineNumber
//                            + ", Token # " + tokenNumber
//                            + ", Token : " + token);
//                    switch (tokenNumber) {
//                        case 1:
//                            country.setIdCountry(Long.parseLong(token));
//                            break;
//                        case 2:
//                            country.setLibCountry(token);
//                            break;
//                        case 3:
//                            country.setLibUpper(token);
//                            break;
//                        case 4:
//                            country.setLibLower(token);
//                            break;
//                        case 5:
//                            country.setCodCountry(token);
//                            break;
//                        default:
//                            break;
//                    }
//                }
//
//                //reset token number
//                tokenNumber = 0;
//                create(country);
//
//            }

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(ips);
            doc.getDocumentElement().normalize();
            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("country");
            System.out.println("Information of all countrys");

            for (int s = 0; s < nodeLst.getLength(); s++) {

                FamCountry country = new FamCountry();

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element fstElmnt = (Element) fstNode;
                    NodeList elmntLst = fstElmnt.getElementsByTagName("name_country");
                    Element elmnt = (Element) elmntLst.item(0);
                    NodeList nl = elmnt.getChildNodes();
                    System.out.println("name_country : " + ( (Node) nl.item(0) ).getNodeValue());
                    country.setLibCountry(( (Node) nl.item(0) ).getNodeValue());
                    
                    elmntLst = fstElmnt.getElementsByTagName("name_country_uppercase");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
                    System.out.println("name_country_uppercase : " + ( (Node) nl.item(0) ).getNodeValue());
                    country.setLibUpper(( (Node) nl.item(0) ).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("country_slug");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
                    System.out.println("country_slug : " + ( (Node) nl.item(0) ).getNodeValue());
                    country.setLibLower(( (Node) nl.item(0) ).getNodeValue());
                    
                    elmntLst = fstElmnt.getElementsByTagName("code");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
                    System.out.println("code : " + ( (Node) nl.item(0) ).getNodeValue());
                    country.setCodCountry(nl.item(0).getNodeValue());
                    
                    elmntLst = fstElmnt.getElementsByTagName("id");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
                    System.out.println("id : " + ( (Node) nl.item(0) ).getNodeValue());
                    country.setIdCountry(Long.parseLong(nl.item(0).getNodeValue()));
                    
                    create(country);

                }

            }
            ips.close();

        }
        catch (Exception e) {
            System.out.println("Exception while reading csv file: " + e);
        }
        
        LogUtil.log(count() + " countries created.", Level.OFF, null);
    }
}
