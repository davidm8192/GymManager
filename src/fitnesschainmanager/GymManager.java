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
            case "CG": {
                checkInGuest(words);
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
            case "DG": {
                dropClassGuest(words);
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

        m.setFname(memberInfo[++count]);
        m.setLname(memberInfo[++count]);
        Date dob = new Date(memberInfo[++count]);
        m.setDob(dob);

        if(validCheckIn(m, className, instructorName, locationName, fitClass)) {
            if (!schedule.getFitnessClass(fitClass).checkIn(database.getMember(m))) {
                System.out.println(m.getFname() + " " + m.getLname() + " already checked in.");
            } else {
                System.out.println(m.getFname() + " " + m.getLname() + " checked in "
                        + schedule.getFitnessClass(fitClass).toString());
            }
        }
    }

    /**
     * Checks if a guest can be checked into the class they specify by calling validGuestCheckIn(), and, if so, calls
     * the guestCheckIn() method in FitnessClass to add them to the class.
     * Outputs whether the guest has checked in.
     * @param memberInfo array of Strings representing the fitness class the Member's guest wants to check into,
     *                   the Member's first name, last name, and date of birth as given from the command line input.
     */
    private void checkInGuest(String[] memberInfo) {
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

        m.setFname(memberInfo[++count]);
        m.setLname(memberInfo[++count]);
        Date dob = new Date(memberInfo[++count]);
        m.setDob(dob);

        if(validGuestCheckIn(m, className, instructorName, locationName, fitClass)) {
            if (!schedule.getFitnessClass(fitClass).checkInGuest((Family) database.getMember(m))) {
                System.out.println(m.getFname() + " " + m.getLname() + " ran out of guest passes.");
            }
            else {
                System.out.println(m.getFname() + " " + m.getLname() + " (guest) checked in " +
                        schedule.getFitnessClass(fitClass).toString());
            }
        }
    }

    /**
     * Searches the FitnessClasses enum to see if the name of the given class exists. If so, it returns the
     * FitnessClasses enum representing the class.
     * @param className String name of the fitness class to be searched.
     * @return FitnessClasses enum values of the fitness class if it exists, or null if it does not.
     */
    private FitnessClasses findClass(String className) {
        FitnessClasses fitClass = null;
        for(FitnessClasses classes : FitnessClasses.values()) {
            if(className.toUpperCase().equals(classes.name())) {
                fitClass = classes;
            }
        }
        return fitClass;
    }

    /**
     * Searches the FitnessClasses enum to see if the name of the given instructor exists. If so, it returns the
     * Instructors enum representing the class instructor.
     * @param instructorName String name of the instructor to be searched.
     * @return Instructors enum values of the instructor if they exists, or null if they does not.
     */
    private Instructors findInstructor(String instructorName) {
        Instructors instructor = null;
        for(Instructors i : Instructors.values()) {
            if(instructorName.toUpperCase().equals(i.name())) {
                instructor = i;
            }
        }
        return instructor;
    }

    /**
     * Searches the FitnessClasses enum to see if the location of the given class exists. If so, it returns the
     * Location enum representing the class's location.
     * @param locationName String name of the location to be searched.
     * @return Location enum values of the location if it exists, or null if it does not.
     */
    private Location findLocation(String locationName) {
        Location location = null;
        for(Location l : Location.values()) {
            if(locationName.toUpperCase().equals(l.name())) {
                location = l;
            }
        }
        return location;
    }

    /**
     * Searches the FitnessClasses enum to see if there is a class at a given time. If so, it returns the
     * Time enum representing the class's time.
     * @param timeStr String time used to check if a class exists at that time.
     * @return Time enum values of the class's time if it exists, or null if it does not.
     */
    private Time findTime(String timeStr) {
        Time time = null;
        for(Time t : Time.values()) {
            if(timeStr.toUpperCase().equals(t.name())) {
                time = t;
            }
        }
        return time;
    }

    /**
     * Checks if the data of a Member is all valid and whether the fitness class exists. Specifically, it checks
     * the validity of the calendar date, whether the Member is in the Member Database, if the class exists, if the
     * instructor exists, if the gym is at a location, and if the fitness class exists. Prints the output and error
     * if it is not valid.
     * @param m Member object representing the Member to be checked.
     * @param className String representing the name of the fitness class.
     * @param instructorName String representing the name of the fitness class's instructor.
     * @param locationName String representing the location of the fitness class.
     * @param fitClass FitnessClass object representing the Fitness Class data.
     * @return false if the member or class is invalid or does not exist, true otherwise.
     */
    private boolean validConditions(Member m, String className, String instructorName,
                                    String locationName, FitnessClass fitClass) {
        if(!m.getDob().isValid()) {
            System.out.println("DOB " + m.getDob() + ": invalid calendar date!");
            return false;
        }
        if(database.getMember(m) == null) {
            System.out.println(m.getFname() + " " + m.getLname() + " " + m.getDob() + " is not in the database.");
            return false;
        }
        if(fitClass.getFitClass() == null) {
            System.out.println(className + " - class does not exist.");
            return false;
        }
        if(fitClass.getInstructor() == null) {
            System.out.println(instructorName + " - instructor does not exist.");
            return false;
        }
        if(fitClass.getLocation() == null) {
            System.out.println(locationName + " - invalid location.");
            return false;
        }
        if(schedule.getFitnessClass(fitClass) == null) {
            Location falseLocation = locationCheck(fitClass);
            if(falseLocation != null) {
                System.out.println(fitClass.getFitClass().getClassName() + " by " +
                        fitClass.getInstructor().toString() + " does not exist at " + falseLocation.name());
            }
            return false;
        }
        return true;
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
        if(validConditions(m, className, instructorName, locationName, fitClass)) {
            if (!(database.getMember(m) instanceof Family)) {
                if(!database.getMember(m).getLocation().equals(fitClass.getLocation())) {
                    System.out.println(m.getFname() + " " + m.getLname() + " checking in " +
                            fitClass.getLocation().toString() + " - standard membership location restriction.");
                    return false;
                }
            }
            if(database.getMember(m).getExpire().compareTo(today) < 0) {
                System.out.println(m.getFname() + " " + m.getLname() + " " + m.getDob() + " membership expired.");
                return false;
            }
            FitnessClass classConflict= checkTimeConflict(m, schedule.getFitnessClass(fitClass));
            if(classConflict != null) {
                System.out.println("Time conflict - " + classConflict.getFitClass().name() + " - " +
                        classConflict.getInstructor().name() + ", " + classConflict.getTime().toString() + ", " +
                        classConflict.getLocation().toString());
                return false;
            }
            return true;
       }
       return false;
    }

    /**
     * Checks if a Member's (Family or Premium membership) guest can be checked in. Specifically, it checks the
     * membership type, if the membership has expired, and if the location of the class matches the location
     * of the membership. If it's not valid, it prints the issue.
     * @param m Member object representing the Member to be checked.
     * @param className String representing the name of the fitness class.
     * @param instructorName String representing the name of the fitness class's instructor.
     * @param locationName String representing the location of the fitness class.
     * @param fitClass FitnessClass object representing the Fitness Class data.
     * @return false if the membership is standard, the membership has expired, or the location of the class does not
     * match the location of the membership. True otherwise.
     */
    private boolean validGuestCheckIn(Member m, String className, String instructorName,
                                      String locationName, FitnessClass fitClass) {
        Date today = new Date();
        if(!(database.getMember(m) instanceof Family)) {
            System.out.println("Standard membership - guest check-in is not allowed.");
            return false;
        }
        if(!validConditions(m, className, instructorName, locationName, fitClass)) {
            return false;
        }
        if(database.getMember(m).getExpire().compareTo(today) < 0) {
            System.out.println(m.getFname() + " " + m.getLname() + " " + m.getDob() + " membership expired.");
            return false;
        }
        if(!database.getMember(m).getLocation().equals(fitClass.getLocation())) {
            System.out.println(m.getFname() + " " + m.getLname() + " Guest checking in " +
                    fitClass.getLocation().toString() + " - guest location restriction.");
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
                if(!fclass.equals(fitClass) && time.equals(fitClass.getTime()) &&
                        fitClass.memberCheck(m)) {
                    return fitClass;
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

        m.setFname(memberInfo[++count]);
        m.setLname(memberInfo[++count]);
        Date dob = new Date(memberInfo[++count]);
        m.setDob(dob);

        if(validConditions(m, className, instructorName, locationName, fitClass)) {
            if(!schedule.getFitnessClass(fitClass).drop(m)) {
                System.out.println(m.getFname() + " " + m.getLname() + " did not check in.");
            }
            else {
                System.out.println(m.getFname() + " " + m.getLname() + " done with the class.");
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

        m.setFname(memberInfo[++count]);
        m.setLname(memberInfo[++count]);
        Date dob = new Date(memberInfo[++count]);
        m.setDob(dob);

        if(validConditions(m, className, instructorName, locationName, fitClass)) {
            if (!schedule.getFitnessClass(fitClass).dropGuest((Family) database.getMember(m))) {
                System.out.println(m.getFname() + " " + m.getLname() + " has no guests checked in");
            }
            else {
                System.out.println(m.getFname() + " " + m.getLname() + " Guest done with the class.");
            }
        }

    }

    /**
     * Reads in the specified fitness schedule file and creates a new fitness class for each line.
     * The created fitness classes are added into the class schedule.
     * The loaded fitness classes from the file are printed out.
     * @param filename the name of the fitness schedule file to be read.
     */
    private void readFitnessSched(String filename) {
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            String line = "";

            System.out.println("-Fitness classes loaded-");

            while(sc.hasNextLine()) {
                line = sc.nextLine();
                String[] words = line.split(" ");
                int count = 0;

                FitnessClass fitClass = new FitnessClass();
                String className = words[count++];
                FitnessClasses fclass = findClass(className);
                fitClass.setClass(fclass);

                String instructorName = words[count++];
                Instructors instructor = findInstructor(instructorName);
                fitClass.setInstructor(instructor);

                String timeStr = words[count++];
                Time time = findTime(timeStr);
                fitClass.setTime(time);

                String locationName = words[count];
                Location location = findLocation(locationName);
                fitClass.setLocation(location);

                schedule.addClass(fitClass);
                System.out.println(fitClass.toString());
            }
            System.out.println("-end of class list-");
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads in the specified member list file and creates a new standard member for each line.
     * The created members are added into the member database.
     * The laoded members from the file are printed out.
     * @param filename the name of the member list file to be read.
     */
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

            sc.close();
            System.out.println("-end of list-");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the members in the database with the membership fee they owe.
     */
    private void printMembershipFees() {
        for (int i = 0; i < database.getSize(); i++) {
            System.out.print(database.getMember(i).toString());

            System.out.println(", Membership fee: $" + df.format(database.getMember(i).membershipFee()));
        }
    }
}