package fitnesschainmanager;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

/**
 * Gym Manager Class is the User Interface class that processes command lines, calls the corresponding methods, and
 * runs member and fitnessClass methods.
 * Adds and delete members, checks if members are valid, checks members into fitness classes, and checks if member
 * check-ins are valid.
 * @author David Ma, Ethan Kwok
 */
public class GymManager {

    private MemberDatabase database;
    private ClassSchedule schedule;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Creates a MemberDatabase and ClassSchedule object.
     */
    public GymManager() {
        database = new MemberDatabase();
        schedule = new ClassSchedule();
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
                addStandardMember(words);
                break;
            }
            case "AF": {
                addFamilyMember(words);
                break;
            }
            case "AP": {
                addPremiumMember(words);
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
                schedule.printSchedule();
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
            case "PF": {
                printMembershipFees();
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
     * Creates and adds new Member with a standard membership and values according to the command line input if valid.
     * The expiration date of the membership is set to STANDARD_LENGTH months after the date of being added.
     * Sets the values of Member to match the input, checks if the Member is valid using the checkIfValid method,
     * adds the member into MemberDatabase database, and then prints the result.
     * @param memberInfo array of Strings representing the information associated with the new Member.
     */
    private void addStandardMember(String[] memberInfo) {
        Member m = new Member();
        int count = 0;

        m.setFname(memberInfo[++count]);
        m.setLname(memberInfo[++count]);

        Date dob = new Date(memberInfo[++count]);
        m.setDob(dob);

        Date expire = new Date();
        expire.addMonths((int) MembershipFees.STANDARD_LENGTH.getValue());
        m.setExpire(expire);

        String location = memberInfo[++count];

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
     * Creates and adds new Member with a family membership and values according to the command line input if valid.
     * The expiration date of the membership is set to FAMILY_LENGTH months after the date of being added.
     * Sets the values of Member to match the input, checks if the Member is valid using the checkIfValid method,
     * adds the member into MemberDatabase database, and then prints the result.
     * @param memberInfo array of Strings representing the information associated with the new Member.
     */
    private void addFamilyMember(String[] memberInfo) {
        Family m = new Family();
        int count = 0;

        m.setFname(memberInfo[++count]);
        m.setLname(memberInfo[++count]);

        Date dob = new Date(memberInfo[++count]);
        m.setDob(dob);

        Date expire = new Date();
        expire.addMonths((int) MembershipFees.FAMILY_LENGTH.getValue());
        m.setExpire(expire);

        String location = memberInfo[++count];

        for(Location loc : Location.values()) {
            if(location.toUpperCase().equals(loc.name())) {
                m.setLocation(loc);
            }
        }

        m.setNumGuestPass((int)MembershipFees.FAMILY_GUEST_PASSES.getValue());

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
     * Creates and adds new Member with a premium membership and values according to the command line input if valid.
     * The expiration date of the membership is set to PREMIUM_LENGTH months after the date of being added.
     * Sets the values of Member to match the input, checks if the Member is valid using the checkIfValid method,
     * adds the member into MemberDatabase database, and then prints the result.
     * @param memberInfo array of Strings representing the information associated with the new Member.
     */
    private void addPremiumMember(String[] memberInfo) {
        Premium m = new Premium();
        int count = 0;

        m.setFname(memberInfo[++count]);
        m.setLname(memberInfo[++count]);

        Date dob = new Date(memberInfo[++count]);
        m.setDob(dob);

        Date expire = new Date();
        expire.addMonths((int) MembershipFees.PREMIUM_LENGTH.getValue());
        m.setExpire(expire);

        String location = memberInfo[++count];

        for(Location loc : Location.values()) {
            if(location.toUpperCase().equals(loc.name())) {
                m.setLocation(loc);
            }
        }

        m.setNumGuestPass((int)MembershipFees.PREMIUM_GUEST_PASSES.getValue());

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
     * Reads and adds new Members with standard memberships from a text file, with values according to the text
     * lines if valid. The expiration date of the membership is set based off the inputs in the text file.
     * Sets the values of each Member to match the input, checks if the Member is valid using the checkIfValid method,
     * adds the member into MemberDatabase database, and then prints the list of Members added.
     * @param memberInfo array of Strings representing the information associated with the new Member.
     */
    private void addMemberFromFile(String[] memberInfo) {
        Member m = new Member();
        int count = 0;

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
            if (database.add(m)) {
                System.out.println(m.toString());
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
     * checkIn() method in FitnessClass to add them to the class.
     * Outputs whether the Member has checked in.
     * @param memberInfo array of Strings representing the fitness class the Member want to check into,
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
     * Checks if a Member is able to check into a given fitness class. Specifically, it checks the conditions in the
     * validConditions() method, then checks if the class is at the same location as the membership, if the membership
     * has not expired, and if the class time does not conflict with another. If there is an issue with validity,
     * the reasoning is printed.
     * @param m Member object representing the Member to be checked.
     * @param className String representing the name of the fitness class.
     * @param instructorName String representing the name of the fitness class's instructor.
     * @param locationName String representing the location of the fitness class.
     * @param fitClass FitnessClass object representing the Fitness Class data.
     * @return false if the member or class is invalid or does not exist, if the location and membership do not match,
     *         if the membership has expired or if the class time conflicts with another. True otherwise.
     */
    private boolean validCheckIn(Member m, String className, String instructorName,
                                    String locationName, FitnessClass fitClass) {
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
        classConflict = checkTimeConflict(m, classType);
        if(classConflict != null) {
            System.out.println(classType.getClassName() + " time conflict -- " + m.getFname() + " " + m.getLname()
                    + " has already checked in " + classConflict.getClassName() + ".");
            return false;
        }
        return true;
    }

    /**
     * Check the location of a fitness class if it exists. Used in validConditions().
     * @param fclass FitnessClass object with data representing the fitness class to be checked.
     * @return Location object representing the location of the fitness class if it exists, or null if it does not.
     */
    private Location locationCheck(FitnessClass fclass) {
        for(int i = 0; i < schedule.getNumClasses(); i++) {
            FitnessClass fitClass = schedule.getFitnessClass(i);
            for(int j = 0; j < fitClass.getLength(); j++) {
                if(fclass.getFitClass().equals(fitClass.getFitClass()) &&
                        fclass.getInstructor().equals(fitClass.getInstructor()) &&
                        !fclass.getLocation().equals(fitClass.getLocation())) {
                    return fclass.getLocation();
                }
            }
        }
        return null;
    }

    /**
     * Check if a class's time conflicts with another class's. Used in validCheckIn().
     * @param m Member object representing the owner of the membership.
     * @param fclass FitnessClass object with data representing the fitness class to be checked.
     * @return FitnessClass object if it exists and does not conflict with another time, null otherwise.
     */
    private FitnessClass checkTimeConflict(Member m, FitnessClass fclass) {
        Time time = fclass.getTime();

        for(int i = 0; i < schedule.getNumClasses(); i++) {
            FitnessClass fitClass = schedule.getFitnessClass(i);
            for(int j = 0; j < fitClass.getLength(); j++) {
                if(!classType.equals(fitClass) && time.equals(fitClass.getTime()) {
                    return fitClass.getClassName();
                }
            }
        }

        return null;
    }


    /**
     * Checks if a Member can drop the class they specify by calling validConditions(), and, if so, calls the
     * drop() method from FitnessClass to remove the Member from the class.
     * Outputs whether the member has dropped the class.
     * @param memberInfo array of Strings representing the fitness class the Member want to drop, the Member's first
     *                   name, last name, and date of birth as given from the command line input.
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
     * Checks if a Member's guest can drop the class they specify by calling validConditions(), and, if so, calls the
     * dropGuest() method from FitnessClass to remove the Member's guest from the class.
     * Outputs whether the Member's guest has dropped the class.
     * @param memberInfo array of Strings representing the fitness class the Member want to drop, the Member's first
     *                   name, last name, and date of birth as given from the command line input.
     */
    private void dropClassGuest(String[] memberInfo) {
        FitnessClass fitClass = new FitnessClass();
        Member m = new Member();
        int count = 0;

        String className = memberInfo[++count];
        FitnessClasses fclass = findClass(className);
        fitClass.setClass(fclass);

        String instructorName = memberInfo[++count];
        Instructors instructor = findInstructor(instructorName);
        fitClass.setInstructor(instructor);

        String locationName = memberInfo[++count];
        Location location = findLocation(locationName);
        fitClass.setLocation(location);

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
            //int lines = getLineNumbers(file);

            Scanner sc = new Scanner(file);
            String line = "";


            while(sc.hasNextLine()) {
                line = sc.nextLine();
                String[] words = line.split(" ");

                //something fitness class related
            }
            
            sc.close(); 

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*private int getLineNumbers(File file) {
        try {
            int lines = 0;
            Scanner sc = new Scanner(file);
            while (sc.nextLine() != null) {
                lines++;
            }
            return lines;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }*/

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
            sc.close(); 
            System.out.println("-end of list-");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void printMembershipFees() {
        for (int i = 0; i < database.getSize(); i++) {
            System.out.print(database.getMember(i).toString());

            if (database.getMember(i).membershipType().equals("Family")) {
                System.out.print(", (Family) guest-pass remaining: " + database.getMember(i).getNumGuestPass());
            }
            if (database.getMember(i).membershipType().equals("Premium")) {
                System.out.print(", (Premium) guest-pass remaining: " + database.getMember(i).getNumGuestPass());
            }

            System.out.println(", Membership fee: $" + df.format(database.getMember(i).membershipFee()));
        }
    }


}