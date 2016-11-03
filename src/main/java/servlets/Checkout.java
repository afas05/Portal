package servlets;

import DBservice.DBservice;
import DBservice.DBException;
import liqpay.LiqPay;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Игорь on 03.11.2016.
 */
public class Checkout extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String vk = request.getParameter("vkadres");
        Map<String, Object> res = new HashMap<>();

        try {
            DBservice dBservice = new DBservice();

            if (dBservice.getUser(vk) == 0) {

                //liqpay: generate data & signature strings
                HashMap params = new HashMap();
                params.put("action", "pay");
                params.put("amount", "5");
                params.put("currency", "UAH");
                params.put("description", vk);
                params.put("order_id", "id");//OrderDao нужен метод для получение кол-ва записей в таблице
                params.put("result_url", "");
                params.put("server_url", "/payment");
                LiqPay liqpay = new LiqPay("i7745127439", "pqAblB1GfdvJaqP60W7hhj5plT8RJMhGcXDLkOy1");
                HashMap api = liqpay.cnb_form(params);

                dBservice.addOrder(vk, api.get("data").toString(), api.get("signature").toString(), "lp");

                res.put("vk", vk);
                res.put("data", api.get("data").toString());
                res.put("signature", api.get("signature").toString());

                response.setCharacterEncoding("utf-8");
                response.getWriter().println(PageGenerator.instance().getPage("checkout.html", res));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (DBException e) {}
    }
}
