import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        if (roomIsAvailable(roomId,startDate,endDate)) {
            int days = (int) startDate.until(endDate, ChronoUnit.DAYS);
            try {
                ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT rate FROM Rooms NATURAL JOIN RoomTypes WHERE roomID=" + roomId);
                resultSet.next();
                float roomRate = resultSet.getFloat(1);
                String query = "INSERT INTO Reservations(gID, roomID, startDate, endDate, numPeople, totalDue) VALUES (" +
                        guestId + "," + roomId + ",'" + startDate + "','" + endDate + "'," +
                        numberOfPeople + "," + String.valueOf(days * roomRate) + ") ";
                resultSet = dbWrapper.insertToDb(query);
                resultSet.next();
                System.out.println("Succesfully created reservation with the following details:\n");
                displayReservationsById(resultSet.getInt(1));
            } catch (SQLException sqlException) {
                System.out.println("Error getting room rate. In Model.createReservation");
                sqlException.printStackTrace();
            }
        } else {
            System.out.println("Room is taken during these dates");
        }
    }

    private boolean roomIsAvailable(String roomId, LocalDate startDate, LocalDate endDate) {
        boolean result = false;
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM Reservations WHERE roomID= " + roomId + " AND  (startDate BETWEEN '" +
                    startDate + "' AND '" + endDate + "' OR endDate BETWEEN '" + startDate + "' AND '" + endDate + "')");
            result = !resultSet.next();
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        }
        return result;
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

    public void displayGuestByName(String name){
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM Guests WHERE firstN='" + name + "' OR lastN= '"
                    + name + "'");
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

    public void displayReservationsByDates(LocalDate startDate, LocalDate endDate) {
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM Reservations WHERE startDate BETWEEN '" +
                    startDate + "' AND '" + endDate + "' OR endDate BETWEEN '" + startDate + "' AND '" + endDate + "'");
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    displayReservationsById(resultSet.getInt(1));
                } while (resultSet.next());
            }
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
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
                            "Reservation ID: " + resultSet.getInt(1)
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

    public void displayAllReservations() {
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM Reservations");
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    displayReservationsById(resultSet.getInt(1));
                } while (resultSet.next());
            }
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        }
    }

    public void displayAllFutureReservations() {
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM Reservations WHERE startDate >= DATE '" + LocalDate.now() +"'");
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    displayReservationsById(resultSet.getInt(1));
                } while (resultSet.next());
            }
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        }
    }

    public void displayReservationsByRoom(Integer roomID) {
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM Reservations WHERE roomID=" +String.valueOf(roomID));
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    displayReservationsById(resultSet.getInt(1));
                } while (resultSet.next());
            }
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        }
    }

    public void displayReservationsByGuest(Integer guestID) {
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM Reservations WHERE gID=" +String.valueOf(guestID));
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    displayReservationsById(resultSet.getInt(1));
                } while (resultSet.next());
            }
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        }
    }

    public void deleteReservationsById(Integer id) {
        try {
            dbWrapper.insertToDb("DELETE FROM Reservations WHERE bookingID=" +String.valueOf(id));
            System.out.println("Successfully deleted\n");

        } catch (Exception e) {
            System.out.println("Error has occured in Model.deleteReservationsById");
            e.printStackTrace();
        }
    }

    public void changeReservationsById(Integer id, String guestId, String roomId, LocalDate startDate, LocalDate endDate, String numberOfPeople) {
        try {
            dbWrapper.insertToDb("UPDATE Reservations SET gID="+ String.valueOf(guestId) +
                    ", roomID=" + String.valueOf(roomId) + ", startDate='" + startDate + "', endDate='"
                    + endDate +"', numPeople=" + String.valueOf(numberOfPeople) + " WHERE bookingID=" + String.valueOf(id));
            System.out.println("Successfully updated transaction number: "+ id +"\n");

        } catch (Exception e) {
            System.out.println("Error has occured in Model.changeReservationsById");
            e.printStackTrace();
        }
    }


    public void createGuest(String firstName, String lastName) {
        try {
            String query = "INSERT INTO Guests(firstN,lastN) VALUES ('" +
                    firstName +"','" + lastName + "') ";
            ResultSet resultSet = dbWrapper.insertToDb(query);
            resultSet.next();
            System.out.println("Succesfully created reservation with the following details:\n");
            displayGuestById(resultSet.getInt(1));
        } catch (SQLException sqlException) {
            System.out.println("Error adding guest. In Model.createGuest");
            sqlException.printStackTrace();
        }
    }

    public void checkIn(int id) {
        try {
            String query = "INSERT INTO Transactions(bookingID,type,time_stamp) VALUES (" +
                    id +",'check in',NOW())";
            ResultSet resultSet = dbWrapper.insertToDb(query);
            resultSet.next();
            System.out.println("Succesfully checked in reservation.\n");
        } catch (SQLException sqlException) {
            System.out.println("Error adding guest. In Model.createGuest");
            sqlException.printStackTrace();
        }
    }

    public void displayServiceRequestsById(int id){
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM ServiceRequest WHERE reqID=" + String.valueOf(id));
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    String costString = String.format("%.02f",resultSet.getFloat(5));
                    System.out.println(
                            "request ID: " + resultSet.getInt(1)
                                    + ", room number: " + resultSet.getInt(2)
                                    + ", service type: "  + resultSet.getString(3)
                                    + ", time_stamp: "  + resultSet.getTimestamp(4)
                                    + ", status: "  + resultSet.getBoolean(5)
                    );
                } while (resultSet.next());
            }
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        }
    }
    public void displayServiceRequests(){
        try {
            ResultSet resultSet = dbWrapper.retrieveFromDb("SELECT * FROM ServiceRequest");
            if ( resultSet.next() == false) {
                System.out.println("No results is database\n");
            } else {
                do {
                    displayServiceRequestsById(resultSet.getInt(1));
                } while (resultSet.next());
            }
        }
        catch (SQLException sqlException){
            System.out.println("Error with SQL query");
            sqlException.printStackTrace();
        }
    }

    public void createServiceRequest(Integer roomID, String serviceType, Timestamp timestamp, boolean status){
        try {
            dbWrapper.insertToDb("insert into ServiceRequest values(null,"+ roomID+",'"+serviceType+"','"+timestamp+"',"+status+")");
            System.out.println("Successfully added new service request\n");
        }catch (Exception e) {
            System.out.println("Error ");
            e.printStackTrace();
        }
    }

    public void serviceRoom (Integer roomID, Integer reqID, Integer staffID, Timestamp timestamp ){
        try {
            dbWrapper.insertToDb("insert into ServiceRoom values("+roomID+","+reqID+","+staffID+",'"+timestamp+"')");
            System.out.println("Thank you for your service\n");
        }catch (Exception e) {
            System.out.println("Error ");
            e.printStackTrace();
        }
    }
}
