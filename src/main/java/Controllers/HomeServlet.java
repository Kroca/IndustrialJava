package Controllers;

import Model.Template.PublicationTemplate;
import Model.Template.UserTemplate;
import Services.PublicationServices;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "home",urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    static {
        PropertyConfigurator.configure(HomeServlet.class.getClassLoader().getResource("log4j.properties"));
    }
    private static final Logger LOGGER = Logger.getLogger(HomeServlet.class);


    private static PublicationServices publicationServices = new PublicationServices();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = ((UserTemplate)req.getSession().getAttribute("user")).getId();
        PublicationTemplate publicationTemplate = new PublicationTemplate();
        publicationTemplate.setTitle(req.getParameter("title"));
        publicationTemplate.setDescription(req.getParameter("description"));
        publicationTemplate.setLink(req.getParameter("link"));
        publicationTemplate.setAuthorId(user_id);

        publicationServices.addNewPublication(publicationTemplate);
        resp.sendRedirect(req.getContextPath()+"/home");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = ((UserTemplate)req.getSession().getAttribute("user")).getId();
        System.out.println("User id is " + user_id);
        req.setAttribute("userName",req.getSession().getAttribute("userLogin"));
        req.setAttribute("publications",publicationServices.getPublicationsOfAuthor(user_id));
        getServletContext().getRequestDispatcher("/Home.jsp").forward(req, resp);
    }
}
