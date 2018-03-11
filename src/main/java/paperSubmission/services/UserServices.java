package paperSubmission.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paperSubmission.model.DAO.UserDAO;
import paperSubmission.model.template.UserTemplate;

@Service
public class UserServices {

    @Autowired
    private UserDAO userDAO;

    public UserTemplate auth(String login, String pas){
        return userDAO.authenticateUser(login,pas);
    }

    public String register(UserTemplate user){
        if(userDAO.insert(user)!=0) {
            return "SUCCESS";
        }else {
            return "Couldn't register try again";
        }
    }
}
