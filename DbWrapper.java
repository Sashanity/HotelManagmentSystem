import java.sql.*;

/**
 * Class contains abstractions for Db access
 */

public class DbWrapper {
    private Connection connection;

    public DbWrapper(Connection connection) {
        this.connection = connection;
    }

    ResultSet insertToDb(String sqlQuery){
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlQuery,Statement.RETURN_GENERATED_KEYS);
            if (result != 0){
                resultSet = statement.getGeneratedKeys();
            } else
                throw new SQLException("New data has not been inserted");
        } catch (SQLException sqlException){
            System.out.println("Error with SQL: DbWrapper.insertToDb, with query: " + sqlQuery);
            sqlException.getMessage();
            sqlException.printStackTrace();
        } finally {
            return resultSet;
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

    void callArchive(String sqlQuery){
        ResultSet resultSet = null;
        //        Connection conn = DriverManager.getConnection();
//        String query = "{call archiveTransactions('"+date+"');}";
//        CallableStatement stmt = conn.prepareCall(query);
        try {
            CallableStatement stmt = connection.prepareCall(sqlQuery);

        } catch (SQLException sqlException){
            System.out.println("Error with SQL: DbWrapper.callArchive: " + sqlQuery);
            sqlException.printStackTrace();
        } finally {

        }
    }
}
