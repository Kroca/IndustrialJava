import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/mydb";

    //  Database credentials
    static final String USER = "azatfanisovic";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();
//            String sql = "CREATE TABLE IF NOT EXISTS users(" +
//                    "ID SERIAL PRIMARY KEY," +
//                    "name VARCHAR (30) NOT NULL," +
//                    "email VARCHAR (30) NOT NULL ," +
//                    "nickName VARCHAR (30) NOT NULL UNIQUE, " +
//                    "password VARCHAR (30) NOT NULL );";
            String sql = "CREATE TABLE IF NOT EXISTS publications(" +
                    "id SERIAL PRIMARY KEY ," +
                    "description VARCHAR (100) NOT NULL ," +
                    "title VARCHAR (20) NOT NULL ," +
                    "link VARCHAR (100) NOT NULL ," +
                    "author_id INTEGER REFERENCES users (ID)" +
                    ");";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

