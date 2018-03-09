package Controllers;

import Model.DAO.UserDAO;
import Model.Template.UserTemplate;
import Services.UserServices;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader().getResource("log4j.properties"));
    }
    private static Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private static UserServices userServices = new UserServices();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickName = req.getParameter("nickName");
        String password = req.getParameter("password");


        UserTemplate userTemplate = userServices.auth(nickName,password);

        if (userTemplate!=null)
        {
            HttpSession session = req.getSession();
            session.setAttribute("userLogin",userTemplate.getNickName());
            session.setAttribute("user",userTemplate);
            req.setAttribute("userName", nickName);
            resp.sendRedirect("/home");
        } else {
            req.setAttribute("errMessage", "Failed to login, try again");
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
    }
}
