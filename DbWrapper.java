import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Class contains abstractions for Db access
 */

public class DbWrapper {
    private Connection connection;

    public DbWrapper(Connection connection) {
        this.connection = connection;
    }

    void insertToDb(String sqlQuery){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        } catch (SQLException sqlException){
            System.out.println("Error with SQL: DbWrapper.insertToDb, with query: " + sqlQuery);
            sqlException.printStackTrace();
        }
    }

    ResultSet retrieveFromDb(String sqlQuery){
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException sqlException){
            System.out.println("Error with SQL: DbWrapper.retrieveFromDb, with query: " + sqlQuery);
            sqlException.printStackTrace();
        } finally {
            return resultSet;
        }
    }
}
