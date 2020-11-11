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

    ResultSet getReservationsById(int reservationId){
        ResultSet resultSet = null;
        try {
            String sqlQuery = "SELECT * FROM Reservation WHERE reservationId=" + String.valueOf(reservationId) ;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        } finally {
            return resultSet;
        }
    }
}
