import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Manages interaction with the user and performs operations in the Infinity Hotel Mgmt. System
 * Based on the Console class from CS151 HW2 by: Ilan Granot
 */
public class TerminalConsole {
    private static final String DISPLAY_WELCOME_MESSAGE = "\nWelcome to Infinity Hotel Management Program\n\n";
    private static final String DISPLAY_MAIN_MENU = "\n\tMAIN MENU\nPlease choose from the following:\n"+
            "[R]eservations\n[C]hecking in/out\n[G]uest managment\n[M]aintenance\n[K]eying system\n[O]thers\n[Q]uit\n";
    private static final String DISPLAY_PROMPT = "Enter your choice and press enter: ";
    private static final String DISPLAY_WRONG_INPUT = "\nWrong input. Please try again.\n";
    private static final String INVALID_SELECTION = "Invalid selection. Please try again.\n";
    private static final String DISPLAY_GUEST_SUBMENU = "\n\tGUEST MANAGEMENT MENU\nPlease choose from the following:\n"+
            "[S]earch for a guest by ID\nS[e]arch for a guest by name\n[A]dd a new guest\n[B]ack\n";
    private static final String DISPLAY_RESERVATIONS_MENU = "\n\tRESERVATIONS MENU\nPlease choose from the following:\n"+
            "[C]reate new reservation\n[F]ind reservation by ID\nFind reservation by [D]ate\n" +
            "Find reservation by [R]room number\nFind reservation by [G]uest ID\n[S]how all reservations\n" +
            "Show a[l]l future reservations\nD[e]lete reservation by ID\nC[h]ange reservation (by ID)\n[B]ack\n";
    private static final String DISPLAY_CHECKIN_SUBMENU = "\n\tCHECKING MENU\nPlease choose from the following:\n"+
            "[D]isplay vacant rooms\nCheck-[I]n\nCheck-[O]ut\n[A]ll transactions\nA[R]chive transactions\n[B]ack\n";
    private static final String DISPLAY_MAINTAINANCE_SUBMENU = "\n\tMAINTENANCE MENU\nPlease choose from the following:\n"+
            "[G]et room by ID\n[R]equest Service\n[D]isplay service requests\n[S]ervice Room\n[B]ack\n";
    private static final String DISPLAY_KEYING_SUBMENU = "\n\tXXX MENU\nPlease choose from the following:\n"+
            "[C]reate key\n[U]pdate forgotten key for the guest";
    private static final String DISPLAY_OTHERS_SUBMENU = "\n\tXXX MENU\nPlease choose from the following:\n"+
            "[W]hatever\n[B]ack\n";

    private static final String DISPLAY_GET_DATE = "Pick your desired date in the following format MM/DD/YYYY\n";
    private static final String DISPLAY_CALENDAR_HEADER = "Current month is:\n Su\t Mo\t Tu\t We\t Th\t Fr\t Sa\n";
    private static final String DATE_PARSING_FORMAT = "M/d/yyyy";

    private Scanner scanner;
    private Model model;


    /**
     * Constructor
     * @param scanner a scanner to read user input
     * @param model a model to use for logic
     */
    public TerminalConsole(Scanner scanner, Model model){
        this.scanner = scanner;
        this.model = model;
    }

    /**
     * Displays main menu and calls desired operations until user chooses [Q]uit
     */
    public void startConsole(){
        String input;
        display(DISPLAY_WELCOME_MESSAGE);
        boolean isQuit = false;
        while (!isQuit){
            input = displayPrompt(DISPLAY_MAIN_MENU);
            switch (input.toLowerCase()){
                // RESERVATIONS MENU
                case "r": input = displayPrompt(DISPLAY_RESERVATIONS_MENU);
                    if (input.toLowerCase().equals("c")) {
                        String guestId = displayPrompt("Guest ID, ");
                        String roomId = displayPrompt("Room number, ");
                        String startDateStr = displayPrompt("Start date (" + DATE_PARSING_FORMAT + "), ");
                        String endDateStr = displayPrompt("End date (" + DATE_PARSING_FORMAT + "), ");
                        String numberOfPeople = displayPrompt("Number of people (1 - 10): ");
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PARSING_FORMAT);
                        LocalDate startDate = LocalDate.parse(startDateStr, dateTimeFormatter);
                        LocalDate endDate = LocalDate.parse(endDateStr, dateTimeFormatter);
                        model.createReservation(guestId, roomId, startDate, endDate, numberOfPeople);
                    } else if (input.toLowerCase().equals("f") ){
                        input = displayPrompt("Search resrvation by ID. ");
                        if (input.matches("[0-9]+")) {
                            model.displayReservationsById(Integer.valueOf(input));
                        } else display(DISPLAY_WRONG_INPUT);
                    } else if (input.toLowerCase().equals("d") ) {
                        String startDateStr = displayPrompt("Start date (" + DATE_PARSING_FORMAT + "), ");
                        String endDateStr = displayPrompt("End date (" + DATE_PARSING_FORMAT + "), ");
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PARSING_FORMAT);
                        LocalDate startDate = LocalDate.parse(startDateStr, dateTimeFormatter);
                        LocalDate endDate = LocalDate.parse(endDateStr, dateTimeFormatter);
                        model.displayReservationsByDates(startDate,endDate);
                    } else if (input.toLowerCase().equals("r") ) {
                        input = displayPrompt("Search resrvation by room number. ");
                        if (input.matches("[0-9]+")) {
                            model.displayReservationsByRoom(Integer.valueOf(input));
                        } else display(DISPLAY_WRONG_INPUT);
                    } else if (input.toLowerCase().equals("g") ) {
                        input = displayPrompt("Search resrvation by Guest ID. ");
                        if (input.matches("[0-9]+")) {
                            model.displayReservationsByGuest(Integer.valueOf(input));
                        } else display(DISPLAY_WRONG_INPUT);
                    } else if (input.toLowerCase().equals("s") ) {
                        model.displayAllReservations();
                    } else if (input.toLowerCase().equals("l") ) {
                        model.displayAllFutureReservations();
                    } else if (input.toLowerCase().equals("e") ) {
                        input = displayPrompt("Delete resrvation by ID. ");
                        if (input.matches("[0-9]+")) {
                            model.deleteReservationsById(Integer.valueOf(input));
                        } else display(DISPLAY_WRONG_INPUT);
                    } else if (input.toLowerCase().equals("h") ) {
                        input = displayPrompt("Enter resrvation ID. ");
                        if (input.matches("[0-9]+")) {
                            String guestId = displayPrompt("Guest ID, ");
                            String roomId = displayPrompt("Room number, ");
                            String startDateStr = displayPrompt("Start date (" + DATE_PARSING_FORMAT + "), ");
                            String endDateStr = displayPrompt("End date (" + DATE_PARSING_FORMAT + "), ");
                            String numberOfPeople = displayPrompt("Number of people (1 - 10): ");
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PARSING_FORMAT);
                            LocalDate startDate = LocalDate.parse(startDateStr, dateTimeFormatter);
                            LocalDate endDate = LocalDate.parse(endDateStr, dateTimeFormatter);
                            model.changeReservationsById(Integer.valueOf(input),guestId, roomId, startDate, endDate,
                                    numberOfPeople);
                        } else display(DISPLAY_WRONG_INPUT);
                    }
                    else {
                        System.out.println("\nNot a valid option.\n");
                    }
                    break;
                case "c": input = displayPrompt(DISPLAY_CHECKIN_SUBMENU);
                    if (input.toLowerCase().equals("d") ) {
                        String startDateStr = displayPrompt("Start date (" + DATE_PARSING_FORMAT + "), ");
                        String endDateStr = displayPrompt("End date (" + DATE_PARSING_FORMAT + "), ");
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PARSING_FORMAT);
                        LocalDate startDate = LocalDate.parse(startDateStr, dateTimeFormatter);
                        LocalDate endDate = LocalDate.parse(endDateStr, dateTimeFormatter);
                        model.displayVacantRoomsByDate(startDate,endDate);
                    } else if (input.toLowerCase().equals("i") ) {
                        input = displayPrompt("Check-In by resrvation ID. ");
                        if (input.matches("[0-9]+")) {
                            String worker = displayPrompt("Please enter your employee ID. ");
                            if (input.matches("[0-9]+")) {
                                model.checkIn(Integer.valueOf(input), Integer.valueOf(worker));
                            } else display(DISPLAY_WRONG_INPUT);
                        } else display(DISPLAY_WRONG_INPUT);
                    } else if (input.toLowerCase().equals("o") ) {
                        input = displayPrompt("Check-Out by resrvation ID. ");
                        if (input.matches("[0-9]+")) {
                            String worker = displayPrompt("Please enter your employee ID. ");
                            if (input.matches("[0-9]+")) {
                                model.checkOut(Integer.valueOf(input), Integer.valueOf(worker));
                            } else display(DISPLAY_WRONG_INPUT);
                        } else display(DISPLAY_WRONG_INPUT);
                    }else if (input.toLowerCase().equals("r") ){
                        String date = displayPrompt("Set cut-off date for archiving MM/DD/YYYY. ");
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PARSING_FORMAT);
                        LocalDate dateFormatted= LocalDate.parse(date, dateTimeFormatter);
                        model.archiveTransactions(dateFormatted);
                    }else if (input.toLowerCase().equals("a") ){

                        model.displayAllTransactions();
                    }
                    break;
                // GUEST MENU
                case "g": input = displayPrompt(DISPLAY_GUEST_SUBMENU);
                    if (input.toLowerCase().equals("a")) {
                        String firstName = displayPrompt("First name, ");
                        String lastName = displayPrompt("Last name, ");
                        model.createGuest(firstName,lastName);
                        break;
                    } else if (input.toLowerCase().equals("s") ) {
                        input = displayPrompt("Search for guest by ID. ");
                        if (input.matches("[0-9]+")) {
                            model.displayGuestById(Integer.valueOf(input));
                        } else
                            display(DISPLAY_WRONG_INPUT);
                        break;
                    } else if (input.toLowerCase().equals("e") ) {
                        String name = displayPrompt("Enter first or last name ");
                        model.displayGuestByName(name);
                        break;
                    }
                case "m": input = displayPrompt(DISPLAY_MAINTAINANCE_SUBMENU);
                    if (input.toLowerCase().equals("e")) {
                        input = displayPrompt("Insert a new guest as... ");
                    } else if (input.toLowerCase().equals("g") ){
                        input = displayPrompt("Search room type by room number. ");
                        if (input.matches("[0-9]+")) {
                            model.displayRoomTypeById(Integer.valueOf(input));
                        } else display(DISPLAY_WRONG_INPUT);
                    } else if (input.toLowerCase().equals("r")){
                        String roomID = displayPrompt("room ID ");
                        String service = displayPrompt("service type, ");
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        model.createServiceRequest(Integer.valueOf(roomID),service,timestamp, false);
                    } else if (input.toLowerCase().equals("s")) {
                        String roomID = displayPrompt("room # ");
                        String reqID = displayPrompt("service req # ");
                        String staffID = displayPrompt("staff id #");
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        model.serviceRoom(Integer.valueOf(roomID),Integer.valueOf(reqID),Integer.valueOf(staffID),timestamp);
                    } else if (input.toLowerCase().equals("d")){
                        model.displayServiceRequests();
                    } else
                        break;
                    break;
                case "k": input = displayPrompt(DISPLAY_KEYING_SUBMENU);
                    if (input.toLowerCase().equals("c")) {
                        String roomID = displayPrompt("room # ");
                        String guestID = displayPrompt("guest # ");
                        String pwd = displayPrompt("password ");
                        model.createKey(Integer.valueOf(roomID),Integer.valueOf(guestID),pwd);
                    } else if (input.toLowerCase().equals("u")) {
                        String roomID = displayPrompt("room # ");
                        String guestID = displayPrompt("guest # ");
                        String pwd = displayPrompt("password ");
                        model.changeKeyforGuest(Integer.valueOf(roomID),Integer.valueOf(guestID),pwd);
                    }

                    break;

                case "o":input = displayPrompt(DISPLAY_OTHERS_SUBMENU);
                    displayMonth(LocalDate.now()); break;
                case "q": isQuit = true; break;
                default: display(INVALID_SELECTION); break;
            }
        }
    }

    // private helper function
    private String displayPrompt(String typeOfPrompt){
        display(typeOfPrompt);
        display(DISPLAY_PROMPT);
        return scanner.nextLine();
    }


    /**
     * Helper method to print to standard output
     * @param str the message to print
     */
    public void display(String str){
        System.out.print(str);
    }




    /**
     * Displays a month view with marking of current day
     * @param localDate the date of the month to display
     * @param daysWithEvents a set with dates that contain at least one event
     */
    public void displayMonth(LocalDate localDate, HashSet<Integer> daysWithEvents){
        int monthLength = localDate.lengthOfMonth();
        int firstDayOfMonth = LocalDate.of(localDate.getYear(),localDate.getMonth(),1).getDayOfWeek().getValue();
        int dayOfMonth = 1;
        display("\n\t\t" + localDate.getMonth().toString() + " " + localDate.getYear() + "\n");
        display(DISPLAY_CALENDAR_HEADER);
        for (int row = 0; dayOfMonth <= localDate.lengthOfMonth(); row++) {
            for (int column = 0; column < 7 && dayOfMonth <= localDate.lengthOfMonth(); column++) {
                if (row == 0) {
                    if (column >= firstDayOfMonth) {
                        if (daysWithEvents.contains(dayOfMonth)) display("{" + dayOfMonth++ + "}");
                        else display(" " + dayOfMonth++ + "\t");
                    }
                    else display("\t");
                } else {
                    if (daysWithEvents.contains(dayOfMonth)) display("{" + dayOfMonth++ + "}");
                    else display(" " + dayOfMonth++ + "\t");
                }
            }
            display("\n\n");
        }
    }


    /**
     * Displays a month view with marking of current day
     * @param localDate the date of the month to display
     */
    public void displayMonth(LocalDate localDate){

        int monthLength = localDate.lengthOfMonth();
        int firstDayOfMonth = LocalDate.of(localDate.getYear(),localDate.getMonth(),1).getDayOfWeek().getValue();
        int dayOfMonth = 1;
        display("\n\t\t" + localDate.getMonth().toString() + " " + localDate.getYear() + "\n");
        display(DISPLAY_CALENDAR_HEADER);
        for (int row = 0; dayOfMonth <= monthLength; row++) {
            for (int column = 0; column < 7 && dayOfMonth <= monthLength; column++) {
                if (row == 0) {
                    if (column >= firstDayOfMonth) {
                        if (dayOfMonth == localDate.getDayOfMonth()) display("[" + dayOfMonth++ + "]\t");
                        else display(" " + dayOfMonth++ + "\t");
                    }
                    else display("\t");
                } else {
                    if (dayOfMonth == localDate.getDayOfMonth()) display("[" + dayOfMonth++ + "]\t");
                    else display(" " + dayOfMonth++ + "\t");
                }
            }
            display("\n");
        }
    }
}
