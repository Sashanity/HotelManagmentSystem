import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Class to handle the logic of the Infinity Hotel Mgmt. System
 */

public class Model {
    private DbWrapper dbWrapper;

    public Model(DbWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }


    public void createReservation(String guestId, String roomId, LocalDate startDate, LocalDate endDate, String numberOfPeople) {
        int days = (int) startDate.until(endDate, ChronoUnit.DAYS);
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT rate FROM Rooms NATURAL JOIN RoomTypes WHERE roomID=" + roomId);
            resultSet.next();
            float roomRate = resultSet.getFloat(1);
            String query = "INSERT INTO Reservations(gID, roomID, startDate, endDate, numPeople, totalDue) VALUES (" +
                    guestId + "," + roomId + ",'" + startDate + "','" + endDate + "'," +
                    numberOfPeople + "," + String.valueOf(days*roomRate) + ") ";
            resultSet = dbWrapper.insertToDb(query);
            resultSet.next();
            System.out.println("Succesfully created reservation with the following details:\n");
            displayReservationsById(resultSet.getInt(1));
        } catch (SQLException sqlException){
            System.out.println("Error getting room rate. In Model.createReservation");
            sqlException.printStackTrace();
        }


    }


    public void displayReservationsById(int id){
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM Reservations WHERE bookingID=" + String.valueOf(id));
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    String costString = String.format("%.02f",resultSet.getFloat(7));
                    System.out.println(
                            "Transaction ID: " + resultSet.getInt(1)
                                    + ", Guest ID: " + resultSet.getInt(2)
                                    + ", Room number: "  + resultSet.getInt(3)
                                    + ", Start date: "  + resultSet.getString(4)
                                    + ", End date: "  + resultSet.getString(5)
                                    + ", Number of people: "  + resultSet.getInt(6)
                                    + ", Total cost: $"  + costString
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
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM Rooms WHERE roomID=" + String.valueOf(id));
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
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM Guests WHERE gID=" + String.valueOf(id));
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
