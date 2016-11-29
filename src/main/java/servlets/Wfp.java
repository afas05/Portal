package servlets;

import org.apache.commons.codec.binary.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Игорь on 29.11.2016.
 */
public class Wfp {
    private String key = "8053cc2fa0ef3cad5d187629c7ad642c1470fb99";

    public String generateSign(String params) throws IOException{
        String sign = "";
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacMD5");
            mac.init(secretKey);

            byte[] text = mac.doFinal(params.getBytes());
            byte[] hexBytes = new Hex().encode(text);

            sign = new String(hexBytes, "UTF-8");

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No algoritm");
        } catch (InvalidKeyException e) {
            System.out.println("Invalid key");
        }
        return sign;
    }
}
