package Model.DAO;

import Controllers.LoginServlet;
import Model.Template.PublicationTemplate;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationsDAO implements DAO<PublicationTemplate, Integer> {
    static {
        PropertyConfigurator.configure(PublicationsDAO.class.getClassLoader().getResource("log4j.properties"));
    }
    private static Logger LOGGER = Logger.getLogger(PublicationsDAO.class);

    public List<PublicationTemplate> getPublicationsOfAuthor(int author_id) {

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<PublicationTemplate> publications = new ArrayList<>();
        try {
            con = DBConnection.getDataSource().getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from publications where author_id = " + author_id);
            while (resultSet.next()) {
                PublicationTemplate temp = new PublicationTemplate();
                temp.setId(resultSet.getInt("id"));
                temp.setAuthorId(author_id);
                temp.setDescription(resultSet.getString("description"));
                temp.setLink(resultSet.getString("link"));
                temp.setTitle(resultSet.getString("title"));
                publications.add(temp);
            }
        } catch (SQLException e) {
            LOGGER.error("Couldn't get information about authors publications from database",e);
            e.printStackTrace();
        } finally {
            closeResources(con, statement, resultSet);
        }
        return publications;
    }

    private void closeResources(Connection con, Statement statement, ResultSet resultSet) {
        //close connection
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //close statement
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //close resulting set
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public PublicationTemplate getById(Integer id) {
        throw new NotImplementedException();
    }

    @Override
    public List<PublicationTemplate> getAll() {
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<PublicationTemplate> publications = new ArrayList<>();
        try {
            con = DBConnection.getDataSource().getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from publications");
            while (resultSet.next()) {
                PublicationTemplate temp = new PublicationTemplate();
                temp.setId(resultSet.getInt("id"));
                temp.setAuthorId(resultSet.getInt("author_id"));
                temp.setDescription(resultSet.getString("description"));
                temp.setLink(resultSet.getString("link"));
                temp.setTitle(resultSet.getString("title"));
                publications.add(temp);
            }
        } catch (SQLException e) {
            LOGGER.error("Couldn't get list publications from database",e);
            e.printStackTrace();
        } finally {
            closeResources(con, statement, resultSet);
        }
        return publications;
    }

    @Override
    public Integer insert(PublicationTemplate entity) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnection.getDataSource().getConnection();
            String query = "insert into publications(description,title,link,author_id) values (?,?,?,?)"; //Insert user details into the table 'USERS'
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getLink());
            preparedStatement.setInt(4, entity.getAuthorId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Couldn't add new  publication to database",e);
            e.printStackTrace();
        } finally {
            closeResources(con, preparedStatement, null);
        }
        return 0;
    }
}
