import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Class to handle the logic of the Infinity Hotel Mgmt. System
 */

public class Model {
    private DbWrapper dbWrapper;

    public Model(DbWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }

    public void displayReservationsById(int id){
        try {
            ResultSet resultSet = dbWrapper.getReservationsById(id);
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    System.out.println(
                            "Transaction ID: " + resultSet.getInt(1)
                                    + ", Guest ID: " + resultSet.getInt(2)
                                    + ", Room number: "  + resultSet.getInt(3)
                                    + ", Start date: "  + resultSet.getString(4)
                                    + ", End date: "  + resultSet.getString(5)
                                    + ", Number of people: "  + resultSet.getInt(6)
                                    + ", Total cost: $"  + resultSet.getFloat(7)
                    );
                } while (resultSet.next());
            }
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        }
    }

    public void displayRoomTypeById(int id){
        try {
            ResultSet resultSet = dbWrapper.getRoomById(id);
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    System.out.println(
                            "Room ID: " + resultSet.getInt(1)
                                    + ", Room type: " + resultSet.getString(2));
                } while (resultSet.next());
            }
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        }
    }

    public void displayGuestById(int id){
        try {
            ResultSet resultSet = dbWrapper.getGuestById(id);
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    System.out.println(
                            "Guest ID: " + resultSet.getInt(1)
                                    + ", First name: " + resultSet.getString(2)
                            + ", Last name: " + resultSet.getString(3));
                } while (resultSet.next());
            }
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        }
    }

    public void displayReservationsByDate(LocalDateTime fromDate, LocalDateTime toDate){
        // ResultSet resultSet = dbWrapper.getReservationsById(1); // demo
    }
}
