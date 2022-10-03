import java.util.Scanner;

/**
 * User Interface class that processes command lines, calls the corresponding methods,
 * and runs member and fitnessClass methods.
 * Adds and delete members, checks if members are valid, checks members into fitness
 * classes, and checks if member check-ins are valid.
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
            String[] words = line.split(" ");
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
     * Sets the values of Member to match the input, checks if the Member is valid using the checkIfValid
     * method, adds the member into MemberDatabase database, and then outputs the result.
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
     *
     * @param m
     * @param location
     * @return
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

    private void displayMembers() {
        if (database.isEmpty()) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println("-list of members-");
        database.print();
        System.out.println("-end of list-");
    }

    private void checkInMember(String[] memberInfo) {
        Member m = new Member();
        int count = 1;

        String fclass = memberInfo[count++];

        m.setFname(memberInfo[count++]);
        m.setLname(memberInfo[count++]);

        Date dob = new Date(memberInfo[count]);
        m.setDob(dob);

        if(validCheckIn(m, fclass)) {
            if (!fitnessData.checkIn(m, fclass)) {
                System.out.println(m.getFname() + " " + m.getLname() + " has already checked in "
                        + fclass.substring(0,1).toUpperCase() + fclass.substring(1).toLowerCase() + ".");
            } else {
                System.out.println(m.getFname() + " " + m.getLname() + " checked in "
                        + fclass.substring(0,1).toUpperCase() + fclass.substring(1).toLowerCase() + ".");
            }
        }
    }

    private boolean validCheckIn(Member m, String fclass) {
        Date today = new Date();
        boolean classExists = false;
        FitnessClasses classType = null;
        FitnessClasses classConflict = null;

        if(!m.getDob().isValid()) {
            System.out.println("DOB " + m.getDob() + ": invalid calendar date!");
            return false;
        }
        if(!database.memberCheck(m)) {
            System.out.println(m.getFname() + " " + m.getLname() + " " + m.getDob() + " is not in the database.");
            return false;
        }
        if(m.getExpire().compareTo(today) < 0) {
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
        }
        return true;
    }
    
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
                System.out.println(m.getFname() + " " + m.getLname() + " is not a participant "
                        + fclass.substring(0,1).toUpperCase() + fclass.substring(1).toLowerCase() + ".");
            }
            else {
                System.out.println(m.getFname() + " " + m.getLname() + " dropped "
                        + fclass.substring(0,1).toUpperCase() + fclass.substring(1).toLowerCase() + ".");
            }

        }
    }

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


}
