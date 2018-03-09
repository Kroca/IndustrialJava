package paperSubmission.model.DAO;

import paperSubmission.model.template.UserTemplate;

public interface UserDAO extends DAO<UserTemplate,Integer> {
    UserTemplate authenticateUser(String nick, String pas);

}
