package servlets.admin;

import DBservice.DBservice;
import DBservice.DBException;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ihor on 01.02.17.
 */
public class Povelitel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<>();
        res.put("id", 00);
        res.put("mes", "good");
        res.put("c", 0);

        response.setCharacterEncoding("utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("povelitel.html", res));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> res = new HashMap<>();
        res.put("c", 1);

        String vk = request.getParameter("vk_id");
        try {
            DBservice dBservice = new DBservice();
            long id = dBservice.addUser(vk);
            res.put("id", id);
            res.put("mes", "good");
            dBservice.closeFactory();
        } catch (DBException e) {
            res.put("mes", "ошибка");
        }

        response.setCharacterEncoding("utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("povelitel.html", res));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
