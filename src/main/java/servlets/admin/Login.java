package servlets.admin;

import DBservice.DBservice;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ihor on 01.03.17.
 */
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        //response.getWriter().println();
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        DBservice dBservice = new DBservice();

        if (dBservice.checUser(login, pass)) {

            String sid = request.getRequestedSessionId();
            dBservice.setSid(login, sid);

        } else {

            Map<String, Object> res = new HashMap<>();

            response.setCharacterEncoding("utf-8");
            response.getWriter().println(PageGenerator.instance().getPage("povelitel.html", res));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }
}
