import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * Class to handle the logic of the Infinity Hotel Mgmt. System
 */

public class Model {
    private DbWrapper dbWrapper;

    public Model(DbWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }

    public void displayReservationsByDate(LocalDateTime fromDate, LocalDateTime toDate){
        dbWrapper.getReservationsById(1);
        // ResultSet resultSet = dbWrapper.getReservationsById(1); // demo
    }
}
