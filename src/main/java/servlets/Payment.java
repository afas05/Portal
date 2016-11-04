package servlets;

import DBservice.DBException;
import DBservice.DBservice;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import liqpay.LiqPay;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

/**
 * Created by Игорь on 01.11.2016.
 */
public class Payment extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String data = request.getParameter("data");
        String signature = request.getParameter("signature");
        String vk = "";

        LiqPay liqPay = new LiqPay("i7745127439","pqAblB1GfdvJaqP60W7hhj5plT8RJMhGcXDLkOy1");
        String sign = liqPay.str_to_sign(signature);
        System.out.println(sign);

        DBservice dBservice = new DBservice();
        VkApiServlet vkApi = new VkApiServlet();
/*
        try {
            if ( ) {

                vkApi.razban(vk);

                dBservice.addUser(vk);
            }
        } catch (DBException | ApiException | ClientException e) {
            e.printStackTrace();
        }
*/
    }
}
