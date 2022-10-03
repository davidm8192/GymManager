import java.util.Scanner;

public class GymManager {

    private MemberDatabase database;

    public GymManager() {
        database = new MemberDatabase();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String line = "";
        boolean run = true;
        System.out.println("Gym Manager Running...");

        while (run) {
            line = sc.nextLine();
            String[] words = line.split(" ");
            run = readCommand(words);

        }

        sc.close();
        System.out.println();
    }

    private boolean readCommand(String[] words) {
        switch (words[0]) {
            case "A": {
                // Add member
                addMember(words);
                //System.out.println("hi");
                break;
            }
            case "R": {
                // Delete member
                deleteMember(words);
                break;
            }
            case "P": {
                database.print();
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
                System.out.println("Gym Manager terminated.");
                return false;
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

        switch(location.toUpperCase()) {
            case "BRIDGEWATER":
                m.setLocation(Location.BRIDGEWATER);
                break;
            case "EDISON":
                m.setLocation(Location.EDISON);
                break;
            case "FRANKLIN":
                m.setLocation(Location.FRANKLIN);
                break;
            case "PISCATAWAY":
                m.setLocation(Location.PISCATAWAY);
                break;
            case "SOMERVILLE":
                m.setLocation(Location.SOMERVILLE);
                break;
            default:
                break;
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

        Date dob = new Date(memberInfo[count++]);
        m.setDob(dob);

        if(!database.remove(m)) {
            System.out.println(m.getFname() + " " + m.getLname() + " is not in the database.");
        }
        else {
            System.out.println(m.getFname() + " " + m.getLname() + " removed.");
        }
    }

    private void displayMembers() {}

    private void displayByCounty() {}

    private void displayByName() {}

    private void displayByDate() {}

    private void displayFitnessSched() {}

    private void checkIn() {}

    private void terminate() {}




}
