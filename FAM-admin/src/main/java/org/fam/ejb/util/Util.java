package org.fam.ejb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mask_hot
 * Date: 01/08/12
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class Util {

    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

    public static String hash(String string) {
        try {
            //Create MessageDigest and encoding for input String
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(string.getBytes("UTF-8"));

            //Hash the Input String
            StringBuffer sb = new StringBuffer();
            for (byte aHash : hash) {
                sb.append(Integer.toString((aHash & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("OUCH!", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("OUCH!", e);
        }

        return null;
    }

    /**
     * This method verifies the content of a list.
     *
     * @param clazz
     * @param c
     * @return
     * @throws ClassCastException
     */
    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) throws ClassCastException {
        List<T> r = new ArrayList<T>(c.size());
        for (Object o : c) {
            r.add(clazz.cast(o));
        }
        return r;
    }
}
