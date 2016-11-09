package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

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

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(razbanPage), "/razban.html");
        handler.addServlet(new ServletHolder(search), "/search.html");
        handler.addServlet(new ServletHolder(payment), "/payment");
        handler.addServlet(new ServletHolder(checkout), "/checkout.html");
        handler.addServlet(new ServletHolder(result), "/result");
        handler.addServlet(new ServletHolder(index), "/");

        Server server = new Server(8080);
        server.setHandler(handler);

        server.start();
        server.join();
    }
}
