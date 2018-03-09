package Controllers;

import Model.Template.UserTemplate;
import Services.UserServices;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "register",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    static {
        PropertyConfigurator.configure(RegisterServlet.class.getClassLoader().getResource("log4j.properties"));
    }
    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);
    private static UserServices userServices = new UserServices();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("register dopost");
        String fullName = req.getParameter("name");
        String email = req.getParameter("email");
        String nickName = req.getParameter("nickName");
        String password = req.getParameter("password");

        UserTemplate user = new UserTemplate(fullName,email,nickName,password);

        String result =userServices.register(user);
        LOGGER.debug("register "+ result);
        if (result.equals("SUCCESS"))
        {
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        } else
        {
            req.setAttribute("errMessage", result);
            req.getRequestDispatcher("/Register.jsp").forward(req, resp);
        }
    }
}
