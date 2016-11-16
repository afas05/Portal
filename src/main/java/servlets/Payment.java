package servlets;

import DBservice.DBException;
import DBservice.DBservice;
import DBservice.dataSets.OrderDataSet;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by Игорь on 01.11.2016.
 */
public class Payment extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String data = request.getParameter("data");
        System.out.println(data);
        System.out.println("-----------data");

        DBservice dBservice = new DBservice();
        VkApiServlet vkApi = new VkApiServlet();

        try {
            byte[] dataByte = Base64.decode(data);
            String dataString = new String(dataByte, "UTF-8");
            JsonObject dataJson = new JsonParser().parse(dataString).getAsJsonObject();
            long order_id = Long.parseLong(dataJson.get("order_id").getAsString());
            System.out.println(order_id);
            System.out.println("-----------order_id");

            OrderDataSet dataSet = dBservice.getOrderById(order_id);
            String vk = dataSet.getVk();
            System.out.println(vk);
            System.out.println("-----------vk");

            String status = dataJson.get("status").getAsString();
            System.out.println(status);
            System.out.println("-----------status");
            float amount = Float.parseFloat(dataJson.get("amount").getAsString());
            System.out.println(amount);
            System.out.println("-----------amount");

            if (status.equals("sandbox") && amount == 5.0) {

                vkApi.razban(vk);

                System.out.println("-----------vk - razbaneno");
                dBservice.addUser(vk);

                System.out.println("-----------db dobaleno");
            }
        } catch (DBException | ApiException | ClientException | Base64DecodingException e) {
            e.printStackTrace();
        } catch (NullPointerException c) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
