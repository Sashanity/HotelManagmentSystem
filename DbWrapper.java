import java.sql.*;
import java.time.LocalDate;

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

    void callArchive(LocalDate localDate){



        //        Connection conn = DriverManager.getConnection();
//        String query = "{call archiveTransactions('"+date+"');}";
//        CallableStatement stmt = conn.prepareCall(query);
        try {
            System.out.println("\nCalling the archive procedure.");
            CallableStatement cs = connection.prepareCall("{CALL archiveTransactions(?)}");
            cs.setString(1,localDate.toString());
            ResultSet resultSet = cs.executeQuery();

//            CallableStatement stmt = connection.prepareCall(sqlQuery);

        } catch (SQLException sqlException){
            System.out.println("Error with SQL: DbWrapper.callArchive.");
            sqlException.printStackTrace();
        }
    }
}
