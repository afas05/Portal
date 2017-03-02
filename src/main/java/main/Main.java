package main;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;
import servlets.admin.Login;
import servlets.admin.Povelitel;

/**
 * Created by Игорь on 01.11.2016.
 */
public class Main {

    public static void main(String[] args) throws Exception{

        RazbanPage razbanPage = new RazbanPage();
        Search search = new Search();
        Payment payment = new Payment();
        Checkout checkout = new Checkout();
        Result result = new Result();
        Index index = new Index();
        Povelitel povelitel = new Povelitel();
        Login login = new Login();

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(razbanPage), "/razban.html");
        handler.addServlet(new ServletHolder(search), "/search.html");
        handler.addServlet(new ServletHolder(payment), "/payment.html");
        handler.addServlet(new ServletHolder(checkout), "/checkout.html");
        handler.addServlet(new ServletHolder(result), "/result.html");
        handler.addServlet(new ServletHolder(povelitel), "/tu/povelitel.html");
        handler.addServlet(new ServletHolder(login), "/l.html");
        handler.addServlet(new ServletHolder(index), "/");

        Server server = new Server(80);

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
