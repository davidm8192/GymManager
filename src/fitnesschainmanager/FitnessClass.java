package fitnesschainmanager;

/**
 * FitnessClass holds the member databases for all the classes offered by the gym.
 * Classes are modified through a member check in and drop method.
 * @author David Ma, Ethan Kwok
 */
public class FitnessClass {
    private MemberDatabase pilates;
    private MemberDatabase spinning;
    private MemberDatabase cardio;

    /**
     * Creates a FitnessClass object and initializes the class databases.
     */
    public FitnessClass() {
        pilates = new MemberDatabase();
        spinning = new MemberDatabase();
        cardio = new MemberDatabase();
    }

    /**
     * Checks member into the specified class if they are not already checked in.
     * Matches the inputted class name with the FitnessClasses enum and adds member to the class database.
     * @param member the Member to be checked into the fitness class.
     * @param className the name of the fitness class the Member wants to check into.
     * @return true if Member was checked into the class, false if were not.
     */
    public boolean checkIn(Member member, String className) {
        switch(className.toUpperCase()) {
            case "PILATES":
                return pilates.add(member);
            case "SPINNING":
                return spinning.add(member);
            case "CARDIO":
                return cardio.add(member);
            default:
                return false;
        }
    }

    /**
     * Checks if there exists a time conflict between the fitness class the member wants to check into and the other
     * fitness class(es) they are checked into, if any.
     * @param member the Member that wants to check into the specified fitness class.
     * @param fclass the FitnessClasses enum of the fitness class the Member wants to check into.
     * @return FitnessClasses enum of the class that has a time conflict with the specified class the Member wants
     * to check into.
     */
    public FitnessClasses checkTimeConflict(Member member, FitnessClasses fclass) {
        if(fclass != FitnessClasses.PILATES && pilates.getMember(member) != null) {
            if(fclass.getTime().equals(FitnessClasses.PILATES.getTime())) {
                return FitnessClasses.PILATES;
            }
        }
        if(fclass != FitnessClasses.SPINNING && spinning.getMember(member) != null) {
            if(fclass.getTime().equals(FitnessClasses.SPINNING.getTime())) {
                return FitnessClasses.SPINNING;
            }
        }
        if(fclass != FitnessClasses.CARDIO && cardio.getMember(member) != null) {
            if(fclass.getTime().equals(FitnessClasses.CARDIO.getTime())) {
                return FitnessClasses.CARDIO;
            }
        }
        return null;
    }

    /**
     * Drops member from specified class if they are in it.
     * Matches the inputted class name with the FitnessClasses enum and removes member from the class database.
     * @param member the Member in the specified fitness class that wants to drop it.
     * @param className the name of the fitness class the Member wants to drop.
     * @return true if Member dropped the class, false if they were not able to.
     */
    public boolean drop(Member member, String className) {
        switch(className.toUpperCase()) {
            case "PILATES":
                return pilates.remove(member);
            case "SPINNING":
                return spinning.remove(member);
            case "CARDIO":
                return cardio.remove(member);
            default:
                return false;
        }
    }

    /**
     * Prints schedule of the fitness classes.
     * Lists the name of the fitness class and all the members in the class database for all fitness classes.
     */
    public void printSchedule() {
        System.out.println("-Fitness classes-");
        System.out.println(FitnessClasses.PILATES.toString());
        if(!pilates.isEmpty()) {
            System.out.println("\t** participants **");
            pilates.print();
        }

        System.out.println(FitnessClasses.SPINNING.toString());
        if(!spinning.isEmpty()) {
            System.out.println("\t** participants **");
            spinning.print();
        }

        System.out.println(FitnessClasses.CARDIO.toString());
        if(!cardio.isEmpty()) {
            System.out.println("\t** participants **");
            cardio.print();
        }
    }

}