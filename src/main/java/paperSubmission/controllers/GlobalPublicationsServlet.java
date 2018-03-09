//package Controllers;
//
//import Services.PublicationServices;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//@WebServlet(name = "publications",urlPatterns = "/publication")
//public class GlobalPublicationsServlet extends HttpServlet {
//
//    static {
//        PropertyConfigurator.configure(GlobalPublicationsServlet.class.getClassLoader().getResource("log4j.properties"));
//    }
//    private static final Logger LOGGER = Logger.getLogger(GlobalPublicationsServlet.class);
//
//
//    private static PublicationServices publicationServices = new PublicationServices();
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("publications",publicationServices.getAllPublications());
//        getServletContext().getRequestDispatcher("/publications.jsp").forward(req,resp);
//    }
//}
