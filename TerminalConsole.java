import java.time.LocalDate;
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
    private static final String DISPLAY_GUEST_SUBMENU = "\n\tXXXX MENU\nPlease choose from the following:\n"+
            "[W]hatever\n[A]nother whatever\n[B]ack\n";
    private static final String DISPLAY_RESERVATIONS_MENU = "\n\tXXX MENU\nPlease choose from the following:\n"+
            "[W]hatever\n[B]ack\n";
    private static final String DISPLAY_CHECKIN_SUBMENU = "\n\tXXX MENU\nPlease choose from the following:\n"+
            "[W]hatever\n[B]ack\n";
    private static final String DISPLAY_MAINTAINANCE_SUBMENU = "\n\tXXX MENU\nPlease choose from the following:\n"+
            "[W]hatever\n[B]ack\n";
    private static final String DISPLAY_KEYING_SUBMENU = "\n\tXXX MENU\nPlease choose from the following:\n"+
            "[W]hatever\n[B]ack\n";
    private static final String DISPLAY_OTHERS_SUBMENU = "\n\tXXX MENU\nPlease choose from the following:\n"+
            "[W]hatever\n[B]ack\n";

    private static final String DISPLAY_GET_DATE = "Pick your desired date in the following format MM/DD/YYYY\n";
    private static final String DISPLAY_CALENDAR_HEADER = "Current month is:\n Su\t Mo\t Tu\t We\t Th\t Fr\t Sa\n";
    private static final String DISPLAY_NAVIGATE = "\nEnter [P]revious, [N]ext, or [G]o back to main menu.\n";

    private static final String DISPLAY_CHOOSE_STARTING_TIME = "Choose starting time, ";
    private static final String DISPLAY_CHOOSE_ENDING_TIME = "Choose ending time, ";
    private static final String DISPLAY_NO_EVENTS = "\nNo events to display.\n";
    private static final String DISPLAY_ONETIME_EVENTS = "\nOne-time events\n";
    private static final String DISPLAY_RECURRING_EVENTS = "\nRecurring events\n";
    private static final String DATE_DISPLAY_PATTERN = "E, MMM d yyyy";
    private static final String DATE_DISPLAY_PATTERN_SHORT = "E, MMM d";


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
                case "r": input = displayPrompt(DISPLAY_RESERVATIONS_MENU);
                    if (input.equals("d")) {

                    }
                    else if (input.equals("m")) {

                    }
                    else display(DISPLAY_WRONG_INPUT);
                    break;
                case "c": input = displayPrompt(DISPLAY_CHECKIN_SUBMENU);
                    String[] subInputArr = new String[6];
                    break;
                case "g": input = displayPrompt(DISPLAY_GUEST_SUBMENU); break;
                case "m": input = displayPrompt(DISPLAY_MAINTAINANCE_SUBMENU);
                subInputArr = new String[2];
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
