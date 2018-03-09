package paperSubmission.model.DAO;
import paperSubmission.model.template.PublicationTemplate;

import java.util.List;

public interface PublicationDAO extends DAO<PublicationTemplate,Integer> {
    List<PublicationTemplate> getPublicationsOfAuthor(int author_id);

}
