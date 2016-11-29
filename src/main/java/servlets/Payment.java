package servlets;

import DBservice.DBException;
import DBservice.DBservice;
import DBservice.dataSets.OrderDataSet;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;

/**
 * Created by Игорь on 01.11.2016.
 */
public class Payment extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> a = request.getParameterMap();
        String s = "";
        for (Map.Entry<String, String[]> entry: a.entrySet()) {
            s = entry.getKey();
        }

        String rS = "";

        DBservice dBservice = new DBservice();
        VkApiServlet vkApi = new VkApiServlet();
        Wfp wfp = new Wfp();

        try {
            JsonObject json = new JsonParser().parse(s).getAsJsonObject();
            long order_id = Long.parseLong(json.get("orderReference").getAsString());
            System.out.println(order_id);
            System.out.println("-----------order_id");

            OrderDataSet dataSet = dBservice.getOrderById(order_id);
            String vk = dataSet.getVk();
            System.out.println(vk);
            System.out.println("-----------vk");

            String status = json.get("transactionStatus").getAsString();
            System.out.println(status);
            System.out.println("-----------status");
            float amount = json.get("amount").getAsFloat();
            System.out.println(amount);
            System.out.println("-----------amount");
            String time = json.get("processingDate").getAsString();
            System.out.println(amount);
            System.out.println("-----------time");

            if ("Approved".equals(status) && amount == 5.0) {

                synchronized (this) {
                    vkApi.razban(vk);
                    dBservice.addUser(vk);
                    Thread.sleep(334);
                }
            }

            String params = order_id + ";accept;" + time.toString();
            String sign = wfp.generateSign(params);

            JSONObject responseJson = new JSONObject();
            responseJson.put("orderReference", order_id);
            responseJson.put("status", "accept");
            responseJson.put("time", time);
            responseJson.put("signature", sign);
            rS = responseJson.toString();

        } catch (DBException | ApiException | ClientException e) {
            e.printStackTrace();
        } catch (NullPointerException c) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } catch (InterruptedException g) {
            System.out.println("Thread interupted");
        }

        response.getOutputStream().write(rS.getBytes("UTF-8"));
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("response", "*");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
