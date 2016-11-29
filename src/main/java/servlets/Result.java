package servlets;

import DBservice.DBservice;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import DBservice.DBException;

/**
 * Created by Игорь on 08.11.2016.
 */
public class Result extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap res = new HashMap();
        String vk = request.getParameter("vk");
        DBservice dBservice = new DBservice();
        try {
            long dataSet = dBservice.getUser(vk);

            if (dataSet == 0) {
                response.setCharacterEncoding("utf-8");
                response.getWriter().println(PageGenerator.instance().getPage("wait.html", res));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setCharacterEncoding("utf-8");
                response.getWriter().println(PageGenerator.instance().getPage("result.html", res));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap res = new HashMap();
        String vk = request.getParameter("vk");
        DBservice dBservice = new DBservice();
        try {
            long dataSet = dBservice.getUser(vk);

            if (dataSet == 0) {
                response.setCharacterEncoding("utf-8");
                response.getWriter().println(PageGenerator.instance().getPage("wait.html", res));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setCharacterEncoding("utf-8");
                response.getWriter().println(PageGenerator.instance().getPage("result.html", res));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
