package Controllers;

import Services.PublicationServices;
import Services.UserServices;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PublicationServlet extends HttpServlet{
    static {
        PropertyConfigurator.configure(PublicationServlet.class.getClassLoader().getResource("log4j.properties"));
    }
    private static final Logger LOGGER = Logger.getLogger(PublicationServlet.class);
    private static PublicationServices publicationServices = new PublicationServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        String title = req.getParameter("title");
        String description =req.getParameter("description");
        String link = req.getParameter("link");

        String username = (String) req.getSession().getAttribute("userLogin");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
