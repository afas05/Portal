package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.Checkout;
import servlets.Payment;
import servlets.RazbanPage;
import servlets.Search;

/**
 * Created by Игорь on 01.11.2016.
 */
public class Main {

    public static void main(String[] args) throws Exception{

        RazbanPage razbanPage = new RazbanPage();
        Search search = new Search();
        Payment payment = new Payment();
        Checkout checkout = new Checkout();

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(razbanPage), "/razban.html");
        handler.addServlet(new ServletHolder(search), "/search.html");
        handler.addServlet(new ServletHolder(payment), "/payment");
        handler.addServlet(new ServletHolder(checkout), "/checkout.html");

        Server server = new Server(8080);
        server.setHandler(handler);

        server.start();
        server.join();
    }
}
