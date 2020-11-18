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
            String sqlQuery = "SELECT * FROM Reservations WHERE bookingID=" + String.valueOf(reservationId) ;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        } finally {
            return resultSet;
        }
    }

    ResultSet getRoomById(int roomId){
        ResultSet resultSet = null;
        try {
            String sqlQuery = "SELECT * FROM Rooms WHERE roomID=" + String.valueOf(roomId) ;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        } finally {
            return resultSet;
        }
    }

    ResultSet getGuestById(int gID){
        ResultSet resultSet = null;
        try {
            String sqlQuery = "SELECT * FROM Guests WHERE gID=" + String.valueOf(gID) ;
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
