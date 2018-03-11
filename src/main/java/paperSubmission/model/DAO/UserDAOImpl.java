package paperSubmission.model.DAO;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Repository;
import paperSubmission.model.template.UserTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    static {
        PropertyConfigurator.configure(UserDAOImpl.class.getClassLoader().getResource("log4j.properties"));
    }
    private static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);


    public UserTemplate authenticateUser(String nick,String pas) {

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserTemplate template = null;

        try {
            con = DBConnection.getDataSource().getConnection();
            String sql ="select * from users WHERE nickName= ? AND password= ?";
            statement = con.prepareStatement(sql);
            statement.setString(1,nick);
            statement.setString(2,pas);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                template = new UserTemplate(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("email"), resultSet.getString("nickName"), resultSet.getString("password"));
            }else {
                LOGGER.debug("user not found");
            }
        } catch (SQLException e) {
            LOGGER.error("error getting information about user from database",e);
            e.printStackTrace();
        }finally {
            closeResources(con,statement,resultSet);
        }
        return template;
    }
    @Override
    public UserTemplate getById(Integer id) {
        UserTemplate template = null;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            con = DBConnection.getDataSource().getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from users WHERE id = "+id);
            if(resultSet.next()) {
                template = new UserTemplate(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("email"), resultSet.getString("nickName"), resultSet.getString("password"));
            }else {
                LOGGER.debug("user not found");
            }
        } catch (SQLException e) {
            LOGGER.debug("Couldn't get info about user from database",e);
            e.printStackTrace();
        }finally {
            closeResources(con,statement,resultSet);
        }
        return template;
    }

    @Override
    public List<UserTemplate> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public Integer insert(UserTemplate entity) {
        String name = entity.getName();
        String email = entity.getEmail();
        String nickName = entity.getNickName();
        String password = entity.getPassword();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try
        {
            con = DBConnection.getDataSource().getConnection();
            String query = "insert into users(name,email,nickName,password) values (?,?,?,?)"; //Insert user details into the table 'USERS'
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, nickName);
            preparedStatement.setString(4, password);
            return preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            LOGGER.debug("Couldn't add new user to database",e);
            e.printStackTrace();
        }finally {
            closeResources(con,preparedStatement,null);
        }
        return 0;
    }


    private void closeResources(Connection con,Statement statement,ResultSet resultSet){
        //close connection
        try {
            if(con!=null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //close statement
        try {
            if(statement!=null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //close resulting set
        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
