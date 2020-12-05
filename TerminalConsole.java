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
            "[S]earch for a guest by ID\n[B]ack\n";
    private static final String DISPLAY_RESERVATIONS_MENU = "\n\tRESERVATIONS MENU\nPlease choose from the following:\n"+
            "[C]reate new reservation\n[F]ind reservation by ID\nFind reservation by [D]ate\n" +
            "Find reservation by [R]room number\nFind reservation by [G]uest ID\n[S]how all reservations\n[B]ack\n";
    private static final String DISPLAY_CHECKIN_SUBMENU = "\n\tXXX MENU\nPlease choose from the following:\n"+
            "[W]hatever\n[B]ack\n";
    private static final String DISPLAY_MAINTAINANCE_SUBMENU = "\n\tMAINTENANCE MENU\nPlease choose from the following:\n"+
            "[G]et room by ID\n[B]ack\n";
    private static final String DISPLAY_KEYING_SUBMENU = "\n\tXXX MENU\nPlease choose from the following:\n"+
            "[W]hatever\n[B]ack\n";
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
                    }
                    else {
                        System.out.println("\nNot a valid option.\n");
                    }
                    break;
                case "c": input = displayPrompt(DISPLAY_CHECKIN_SUBMENU);
                    String[] subInputArr = new String[6];
                    break;
                case "g": input = displayPrompt(DISPLAY_GUEST_SUBMENU);
                    if (input.toLowerCase().equals("e")) {
                        input = displayPrompt("Insert a new guest as... ");
                    } else if (input.toLowerCase().equals("s") ){
                        input = displayPrompt("Search for guest by ID. ");
                        if (input.matches("[0-9]+")) {
                            model.displayGuestById(Integer.valueOf(input));
                        } else display(DISPLAY_WRONG_INPUT);
                    } else
                        break;
                case "m": input = displayPrompt(DISPLAY_MAINTAINANCE_SUBMENU);
                    if (input.toLowerCase().equals("e")) {
                        input = displayPrompt("Insert a new guest as... ");
                    } else if (input.toLowerCase().equals("g") ){
                        input = displayPrompt("Search room type by room number. ");
                        if (input.matches("[0-9]+")) {
                            model.displayRoomTypeById(Integer.valueOf(input));
                        } else display(DISPLAY_WRONG_INPUT);
                    } else
                        break;
                case "k": input = displayPrompt(DISPLAY_KEYING_SUBMENU);
                    displayPrompt(DISPLAY_GET_DATE);
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
