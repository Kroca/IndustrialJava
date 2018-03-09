package Services;

import Model.DAO.UserDAO;
import Model.Template.UserTemplate;

public class UserServices {
    private static UserDAO userDAO = new UserDAO();

    public UserTemplate auth(String login, String pas){
        return userDAO.authenticateUser(login,pas);
    }

    public String register(UserTemplate user){
        if(userDAO.insert(user)!=0) {
            return "SUCCESS";
        }else {
            return "Registration failed miserably";
        }
    }
}
