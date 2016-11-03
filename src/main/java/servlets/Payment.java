package servlets;

import DBservice.DBException;
import DBservice.DBservice;
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

        DBservice dBservice = new DBservice();

        try {
            if (data.equals(dBservice.getOrder(signature).getData()) ) {
                //API VK.COM razbanit
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
