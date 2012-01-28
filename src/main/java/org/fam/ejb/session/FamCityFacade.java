/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.session;

import org.fam.ejb.common.AuditInterceptor;
import org.fam.common.log.LogUtil;
import org.fam.ejb.common.LoggingInterceptor;
import org.fam.ejb.model.FamCity;
import org.fam.ejb.model.FamProvince;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.logging.Level;

/**
 *
 * @author gbougear
 */
@Stateless
@Interceptors({AuditInterceptor.class, LoggingInterceptor.class})
public class FamCityFacade extends AbstractFacade<FamCity> {

    @PersistenceContext ////(unitName = "FAM-test-ejbPU")
    private EntityManager em;
    @EJB
    private FamProvinceFacade ejbProvince;

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
    public FamCityFacade() {
        super(FamCity.class);
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
            String strFile = "/META-INF/PaysRegionsDepartementsVilles/cities.xml";
//            File file = new File(strFile);

            InputStream ips = this.getClass().getResourceAsStream(strFile);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(ips);
            doc.getDocumentElement().normalize();
            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("city");
            System.out.println("Information of all citys");

            FamProvince province = null;
            for (int s = 0; s < nodeLst.getLength(); s++) {

                FamCity city = new FamCity();

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element fstElmnt = (Element) fstNode;
                    NodeList elmntLst = fstElmnt.getElementsByTagName("name_city");
                    Element elmnt = (Element) elmntLst.item(0);
                    NodeList nl = elmnt.getChildNodes();
                    System.out.println("name_city : " + ( (Node) nl.item(0) ).getNodeValue());
                    city.setLibCity(( (Node) nl.item(0) ).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("name_city_uppercase");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
                    System.out.println("name_city_uppercase : " + ( (Node) nl.item(0) ).getNodeValue());
                    city.setLibUpper(( (Node) nl.item(0) ).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("city_slug");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
                    System.out.println("city_slug : " + ( (Node) nl.item(0) ).getNodeValue());
                    city.setLibLower(( (Node) nl.item(0) ).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("cp");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
                    System.out.println("cp : " + ( (Node) nl.item(0) ).getNodeValue());
                    city.setCodCity(nl.item(0).getNodeValue());

                    elmntLst = fstElmnt.getElementsByTagName("id");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
                    System.out.println("id : " + ( (Node) nl.item(0) ).getNodeValue());
                    city.setIdCity(Long.parseLong(nl.item(0).getNodeValue()));

                    elmntLst = fstElmnt.getElementsByTagName("id_province");
                    elmnt = (Element) elmntLst.item(0);
                    nl = elmnt.getChildNodes();
                    String token = nl.item(0).getNodeValue();
                    System.out.println("id_province : " + token);

                    if (token.matches("^97$")) {
                        token = city.getCodCity().substring(0, 3);
                    }
                    if (( province == null ) || !province.getCodProvince().equals(token)) {
//                        province = ejbProvince.find(Long.parseLong(nl.item(0).getNodeValue()));

//                        province = ejbProvince.find(Long.parseLong(token));
//
//                        if (province == null) {
//                            System.out.println("findByCod " + token);
                        province = ejbProvince.findByCod(token);
//                        }

                    }
                    System.out.println("province found " + province.getCodProvince());
                    city.setFamProvince(province);

                    create(city);

                }

            }

            ips.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.log(count() + " cities created.", Level.OFF, null);
    }

    public void genDataCSV() {
        try {

            truncate();
            //csv file containing data
            String strFile = "/META-INF/PaysRegionsDepartementsVilles/cities.csv";

            //create BufferedReader to read csv file

            InputStream ips = this.getClass().getResourceAsStream(strFile);
//
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String strLine = "";
            StringTokenizer st = null;
            int lineNumber = 0, tokenNumber = 0;

            //read comma separated file line by line
            FamProvince province = null;
            while (( strLine = br.readLine() ) != null) {
                lineNumber++;

                //break comma separated line using ","
                st = new StringTokenizer(strLine, ";");

                FamCity city = new FamCity();

                while (st.hasMoreTokens()) {
                    //display csv values
                    tokenNumber++;
                    String token = st.nextToken();
                    token = token.replaceAll("\"", "");
                    System.out.println("Line # " + lineNumber
                            + ", Token # " + tokenNumber
                            + ", Token : " + token);
                    switch (tokenNumber) {
                        case 1:
//                            if (token.matches("[0-9][A-Z]")) {
//                                province = ejbProvince.findByCod(token);
//                            } else 
                            if (( province == null ) || !province.getCodProvince().equals(token)) {
//                                province = ejbProvince.find(Long.parseLong(token));
//                                if (province == null) {
//                                    System.out.println("findByCod " + token);
                                province = ejbProvince.findByCod(token);
//                                }
                            }

                            city.setFamProvince(province);
                            break;
                        case 2:
                            city.setIdCity(Long.parseLong(token));
                            break;
                        case 3:
                            city.setLibUpper(token);
                            break;
                        case 4:
                            city.setLibLower(token);
                            break;
                        case 5:
                            city.setCodCity(token);
                            break;
                        default:
                            break;
                    }
                }

                //reset token number
                tokenNumber = 0;
                create(city);

            }
            ips.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.log(count() + " cities created.", Level.OFF, null);
    }
}
