package servlets;

import DBservice.DBservice;
import DBservice.DBException;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
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

                //generate signature and date

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String date = String.valueOf(timestamp.getTime());
                System.out.println(date);
                long id = dBservice.getRowCount()+2; //database has on 1 less row
                String params = "jirniy_pp_ua;jirniy.pp.ua;"+id+";"+date+";5;UAH;Разбан в ЖУ "+vk+";1;5";

                Wfp wfp = new Wfp();
                String sign = wfp.generateSign(params);

                dBservice.addOrder(vk, date, sign, "wfp");

                res.put("vk", vk);
                res.put("sign",sign);
                res.put("id", id);
                res.put("date", date);

                response.setCharacterEncoding("utf-8");
                response.getWriter().println(PageGenerator.instance().getPage("checkout.html", res));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            }
            dBservice.closeFactory();
        } catch (DBException e) {}
    }
}
