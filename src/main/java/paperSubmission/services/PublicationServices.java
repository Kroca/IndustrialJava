package paperSubmission.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paperSubmission.model.DAO.PublicationDAO;
import paperSubmission.model.template.PublicationTemplate;

import java.util.List;

@Service
public class PublicationServices {

    @Autowired
    private PublicationDAO publicationsDAO;

    public List<PublicationTemplate> getAllPublications(){
        return publicationsDAO.getAll();
    }

    public List<PublicationTemplate> getPublicationsOfAuthor(int author_id){
        return publicationsDAO.getPublicationsOfAuthor(author_id);
    }

    public String addNewPublication(PublicationTemplate template){
        if(publicationsDAO.insert(template)!=0){
            return "Successfully added publication";
        }else {
            return "You couldn't add new publication, please try again later";
        }
    }
}
