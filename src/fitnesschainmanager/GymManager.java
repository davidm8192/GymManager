package fitnesschainmanager;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Gym Manager Class is the User Interface class that processes command lines, calls the corresponding methods, and
 * runs member and fitnessClass methods.
 * Adds and delete members, checks if members are valid, checks members into fitness classes, and checks if member
 * check-ins are valid.
 * @author David Ma, Ethan Kwok
 */
public class GymManager {

    private MemberDatabase database;
    private FitnessClass fitnessData;

    /**
     * Creates a database and fitnessData object.
     */
    public GymManager() {
        database = new MemberDatabase();
        fitnessData = new FitnessClass();
    }

    /**
     * Continuously reads command line inputs.
     * Outputs when the Gym Manager is running and calls the isReadCommand method for each input.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String line = "";
        boolean run = true;
        System.out.println("Gym Manager Running...");

        while (run) {
            line = sc.nextLine();
            String[] words = line.split("\\s+");
            run = isReadCommand(words);

        }

        sc.close();
        System.out.println();
    }

    /**
     * Reads the operation code (first word in the command line) and runs the corresponding method.
     * @param words array of Strings representing the command line input.
     * @return false if the Gym Manager is terminated, true otherwise.
     */
    private boolean isReadCommand(String[] words) {
        switch (words[0]) {
            case "A": {
                addMember(words);
                break;
            }
            case "R": {
                deleteMember(words);
                break;
            }
            case "P": {
                displayMembers();
                break;
            }
            case "PC": {
                database.printByCounty();
                break;
            }
            case "PN": {
                database.printByName();
                break;
            }
            case "PD": {
                database.printByExpirationDate();
                break;
            }
            case "S": {
                fitnessData.printSchedule();
                break;
            }
            case "C": {
                checkInMember(words);
                break;
            }
            case "Q": {
                System.out.println("Gym Manager terminated.");
                return false;
            }
            case "D": {
                dropClass(words);
                break;
            }
            case "LS": {
                readFitnessSched("classSchedule.txt");
                break;
            }
            case "LM": {
                readMemberList("memberList.txt");
                break;
            }
            case "": {
                break;
            }
            default: {
                System.out.println(words[0] + " is an invalid command!");
                break;
            }
        }
        return true;
    }

    /**
     * Creates and adds new Member with values according to the command line input if valid.
     * Sets the values of Member to match the input, checks if the Member is valid using the checkIfValid method,
     * adds the member into MemberDatabase database, and then outputs the result.
     * @param memberInfo array of Strings representing the information associated with the new Member.
     */
    private void addMember(String[] memberInfo) {
        Member m = new Member();
        int count = 1;

        m.setFname(memberInfo[count++]);
        m.setLname(memberInfo[count++]);

        Date dob = new Date(memberInfo[count++]);
        m.setDob(dob);

        Date expire = new Date(memberInfo[count++]);
        m.setExpire(expire);

        String location = memberInfo[count];

        for(Location loc : Location.values()) {
            if(location.toUpperCase().equals(loc.name())) {
                m.setLocation(loc);
            }
        }

        // Add member to database
        if(checkIfValid(m, location)) {
            if(!database.add(m)) {
                System.out.println(m.getFname() + " " + m.getLname() + " is already in the database.");
            }
            else {
                System.out.println(m.getFname() + " " + m.getLname() + " added.");
            }
        }
    }

    /**
     * Checks if a Member fulfills the requirements to be valid.
     * Checks to make sure the date of birth and expiration date are valid calendar dates, the DoB is not today or
     * a future date, the Member is above 18 years of age, and the location is valid.
     * @param m the new Member whose validity is being checked.
     * @param location the location of the new Member's gym.
     * @return true if the member is valid, false if the member is not valid.
     */
    private boolean checkIfValid(Member m, String location) {
        if (!m.isDobValid()) {
            System.out.println("DOB " + m.getDob().toString() + ": invalid calendar date!");
            return false;
        }

        if (!m.isExpireValid()) {
            System.out.println("Expiration date " + m.getExpire().toString() + ": invalid calendar date!");
            return false;
        }

        if (!m.isDobPast()) {
            System.out.println("DOB " + m.getDob().toString() + ": cannot be today or a future date!");
            return false;
        }

        if(!m.isAbove18()) {
            System.out.println("DOB " + m.getDob().toString() + ": must be 18 or older to join!");
            return false;
        }

        if(!m.isValidLocation()) {
            System.out.println(location + ": invalid location!");
            return false;
        }

        return true;
    }

    /**
     * Checks if a Member can be deleted from a database and, if so, calls the remove() method in MemberDatabase
     * to delete them.
     * Outputs whether the member has been removed.
     * @param memberInfo array of Strings representing the Member's first name, last name, and date of birth as given
     *                   from the command line input.
     */
    private void deleteMember(String[] memberInfo) {
        Member m = new Member();
        int count = 1;

        m.setFname(memberInfo[count++]);
        m.setLname(memberInfo[count++]);

        Date dob = new Date(memberInfo[count]);
        m.setDob(dob);

        if(!database.remove(m)) {
            System.out.println(m.getFname() + " " + m.getLname() + " is not in the database.");
        }
        else {
            System.out.println(m.getFname() + " " + m.getLname() + " removed.");
        }
    }

    /**
     * Checks if the Member database is empty and, if not, runs the unsorted print() method.
     * If the database is empty, the output prints that the database is empty.
     */
    private void displayMembers() {
        if (database.isEmpty()) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println("-list of members-");
        database.print();
        System.out.println("-end of list-");
    }

    /**
     * Checks if a Member can be checked into the class they specify by calling validCheckIn(), and, if so, calls the
     * checkIn() method in FitnessClass to add them to the class database.
     * Outputs whether the Member has checked in.
     * @param memberInfo array of Strings representing the name of the fitness class the Member want to check into,
     *                   the Member's first name, last name, and date of birth as given from the command line input.
     */
    private void checkInMember(String[] memberInfo) {
        Member m = new Member();
        int count = 1;

        String fclass = memberInfo[count++];

        m.setFname(memberInfo[count++]);
        m.setLname(memberInfo[count++]);

        Date dob = new Date(memberInfo[count]);
        m.setDob(dob);

        if(validCheckIn(m, fclass)) {
            if (!fitnessData.checkIn(database.getMember(m), fclass)) {
                System.out.println(m.getFname() + " " + m.getLname() + " has already checked in "
                        + fclass.substring(0,1).toUpperCase() + fclass.substring(1).toLowerCase() + ".");
            } else {
                System.out.println(m.getFname() + " " + m.getLname() + " checked in "
                        + fclass.substring(0,1).toUpperCase() + fclass.substring(1).toLowerCase() + ".");
            }
        }
    }

    /**
     * Checks if Member is able to check into the fitness class.
     * Checks if the Member's date of birth is valid, if Member exists in the Member Database, if Member's expiration
     * date has not passed, if the specified class exists, and if there exists a class conflict by calling
     * checkTimeConflict() from FitnessClass.
     * Prints reason for Member being unable to check in if they do not satisfy all the conditions.
     * @param m the Member to be checked in.
     * @param fclass the name of the inputted fitness class the Member wants to check into.
     * @return true if Member meets all the conditions to be checked in, false if not
     */
    private boolean validCheckIn(Member m, String fclass) {
        Date today = new Date();
        boolean classExists = false;
        FitnessClasses classType = null;
        FitnessClasses classConflict = null;

        if(!m.getDob().isValid()) {
            System.out.println("DOB " + m.getDob() + ": invalid calendar date!");
            return false;
        }
        if(database.getMember(m) == null) {
            System.out.println(m.getFname() + " " + m.getLname() + " " + m.getDob() + " is not in the database.");
            return false;
        }
        if(database.getMember(m).getExpire().compareTo(today) < 0) {
            System.out.println(m.getFname() + " " + m.getLname() + " " + m.getDob() + " membership expired.");
            return false;
        }
        for(FitnessClasses classes : FitnessClasses.values()) {
            if(fclass.toUpperCase().equals(classes.name())) {
                classExists = true;
                classType = classes;
            }
        }
        if(!classExists) {
            System.out.println(fclass + " class does not exist.");
            return false;
        }
        classConflict = fitnessData.checkTimeConflict(m, classType);
        if(classConflict != null) {
            System.out.println(classType.getClassName() + " time conflict -- " + m.getFname() + " " + m.getLname()
                    + " has already checked in " + classConflict.getClassName() + ".");
            return false;
        }
        return true;
    }

    /**
     * Checks if a Member can drop the class they specify by calling validDropClass(), and, if so, calls the
     * drop() method from FitnessClass to remove the Member from the class database.
     * Outputs whether the member has dropped the class.
     * @param memberInfo array of Strings representing the name of the fitness class the Member want to drop,
     *                   the Member's first name, last name, and date of birth as given from the command line input.
     */
    private void dropClass(String[] memberInfo) {
        Member m = new Member();
        int count = 1;

        String fclass = memberInfo[count++];

        m.setFname(memberInfo[count++]);
        m.setLname(memberInfo[count++]);

        Date dob = new Date(memberInfo[count]);
        m.setDob(dob);

        if(validDropClass(m, fclass)) {
            if(!fitnessData.drop(m, fclass)) {
                System.out.println(m.getFname() + " " + m.getLname() + " is not a participant in "
                        + fclass.substring(0,1).toUpperCase() + fclass.substring(1).toLowerCase() + ".");
            }
            else {
                System.out.println(m.getFname() + " " + m.getLname() + " dropped "
                        + fclass.substring(0,1).toUpperCase() + fclass.substring(1).toLowerCase() + ".");
            }

        }
    }

    /**
     * Checks if Member is able to drop the fitness class.
     * Checks if the Member's date of birth is valid and if the specified class exists.
     * Prints reason for Member being unable to drop the class if they do not satisfy all the conditions.
     * @param m the Member to be removed from the class.
     * @param fclass the name of the inputted fitness class the Member wants to drop.
     * @return true if Member meets all the conditions to drop the class, false if not
     */
    private boolean validDropClass(Member m, String fclass) {
        Date today = new Date();
        boolean classExists = false;
        FitnessClasses classType = null;

        if(!m.getDob().isValid()) {
            System.out.println("DOB " + m.getDob() + ": invalid calendar date!");
            return false;
        }
        for(FitnessClasses classes : FitnessClasses.values()) {
            if(fclass.toUpperCase().equals(classes.name())) {
                classExists = true;
                classType = classes;
            }
        }
        if(!classExists) {
            System.out.println(fclass + " class does not exist.");
            return false;
        }
        return true;
    }
    
    private void readFitnessSched(String filename) {
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            String line = "";

            while(sc.hasNextLine()) {
                line = sc.nextLine();
                String[] words = line.split(" ");
                //something fitness class related
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readMemberList(String filename) {
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            String line = "";

            System.out.println("-list of members loaded-");

            while(sc.hasNextLine()) {
                line = sc.nextLine();
                String[] words = line.split("\\s+");
                addMemberFromFile(words);
            }

            //database.print();
            System.out.println("-end of list-");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}