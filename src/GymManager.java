import java.util.Scanner;

public class GymManager {

    public void run() {
        Scanner sc = new Scanner(System.in);
        String line = "";
        int counter = 0;

        while ((line = sc.nextLine()) != null) { //TEMP continuously read command lines so that it works with >1 command line input
            String[] words = line.split(" ");

            switch (words[0]) {
                case "A": {
                    // Add member
                    break;
                }
                case "R": {
                    // Delete member
                    break;
                }
                case "P": {
                    // Display list of members without sorting
                    break;
                }
                case "PC": {
                    // Display list of members ordered by county names, then zip codes if the locations are in the same county
                    break;
                }
                case "PN": {
                    // Display list of members ordered by last names, then first names if last names are the same
                    break;
                }
                case "PD": {
                    // Display list of members ordered by expiration dates; if same, order does not matter
                    break;
                }
                case "S": {
                    // Display fitness class schedule
                    break;
                }
                case "C": {
                    // Check in
                    // Do not allow check-in:
                    // the membership has expired
                    // the member does not exist
                    // the date of birth is invalid
                    // the fitness class does not exist
                    // there is a time conflict with other fitness classes
                    // the member has already checked in
                    break;
                }
                case "Q": {
                    // Stop the program execution and display "Gym Manager terminated.",
                    break;
                }
                default: {
                    System.out.println(words[0] + " is an invalid command!");
                    break;
                }
            }
        }
    }

    public static void addMember() {}

    public static void deleteMember() {}

    public static void displayMembers() {}

    public static void displayByCounty() {}

    public static void displayByName() {}

    public static void displayByDate() {}

    public static void displayFitnessSched() {}

    public static void checkIn() {}

    public static void terminate() {}




}
