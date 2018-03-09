package Services;

import Model.DAO.PublicationsDAO;
import Model.Template.PublicationTemplate;

import java.util.List;

public class PublicationServices {

    private static PublicationsDAO publicationsDAO = new PublicationsDAO();

    public List<PublicationTemplate> getAllPublications(){
        return publicationsDAO.getAll();
    }

    public List<PublicationTemplate> getPublicationsOfAuthor(int author_id){

        return publicationsDAO.getPublicationsOfAuthor(author_id);
    }

    public String addNewPublication(PublicationTemplate template){
        if(publicationsDAO.insert(template)!=0){
            return "SUCCESS";
        }else {
            return "You couldn't add new publication, please try again later";
        }
    }
}
