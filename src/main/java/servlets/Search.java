package servlets;

import templater.PageGenerator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import DBservice.DBException;
import DBservice.DBservice;

/**
 * Created by Игорь on 02.11.2016.
 */
public class Search extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vkadres = request.getParameter("fild");

        Map<String, Object> res = new HashMap<>();
        res.put("vkadres", vkadres);

        VkApiServlet apiServlet = new VkApiServlet();
        DBservice dBservice = new DBservice();

        try {
            if(vkadres.contains("vk.com")) {

                response.setCharacterEncoding("utf-8");
                response.getWriter().println(PageGenerator.instance().getPage("razban.html", res));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);

            } else {

                long dataSet = dBservice.getUser(vkadres);
                if(dataSet == 0) {
                    res.put("firstName", apiServlet.getFirst(vkadres));
                    res.put("lastName", apiServlet.getSecond(vkadres));

                    res.put("rezultat", "Ты впервые здесь, можем тебя разбанить");
                    res.put("otvet", "ПОДЛЕЖИТ");

                    response.setCharacterEncoding("utf-8");
                    response.getWriter().println(PageGenerator.instance().getPage("search.html", res));
                    response.setContentType("text/html;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_OK);

                } else {
                    res.put("firstName", apiServlet.getFirst(vkadres));
                    res.put("lastName", apiServlet.getSecond(vkadres));

                    res.put("rezultat", "Опача попался! Уже был помилован ранее, второго шанса нет");
                    res.put("otvet", "НЕ ПОДЛЕЖИТ!! GO AWAY BITCH!!");

                    response.setCharacterEncoding("utf-8");
                    response.getWriter().println(PageGenerator.instance().getPage("search.html", res));
                    response.setContentType("text/html;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_OK);
                }

            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
