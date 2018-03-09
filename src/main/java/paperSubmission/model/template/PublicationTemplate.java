package paperSubmission.model.template;

public class PublicationTemplate {

    private int id;
    private String title;
    private String description;
    private String link;
    private int authorId;

    public PublicationTemplate(String title, String description, String link, int authorId) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.authorId = authorId;
    }

    public PublicationTemplate() {
    }

    public PublicationTemplate(int id, String title, String description, String link, int authorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
