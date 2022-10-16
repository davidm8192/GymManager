package fitnesschainmanager;

import java.util.ArrayList;

/**
 * FitnessClass holds the member databases for all the classes offered by the gym.
 * Classes are modified through a member check in and drop method.
 * @author David Ma, Ethan Kwok
 */
public class FitnessClass {
    /*private MemberDatabase pilates;
    private MemberDatabase spinning;
    private MemberDatabase cardio;*/

    private FitnessClasses className;
    private Instructors instructor;
    private Time time;
    private Location location;
    private ArrayList<Member> fitClass;

    /**
     * Creates a FitnessClass object and initializes the class databases.
     */
    public FitnessClass() {
        /*pilates = new MemberDatabase();
        spinning = new MemberDatabase();
        cardio = new MemberDatabase();*/

        className = null;
        instructor = null;
        time = null;
        location = null;
        fitClass = new ArrayList<Member>();
    }

    public FitnessClass(FitnessClasses className, Instructors instructor, Time time, Location location) {
        this.className = className;
        this.instructor = instructor;
        this.time = time;
        this.location = location;
        fitClass = new ArrayList<Member>();
    }

    /**
     * Checks member into the specified class if they are not already checked in.
     * Matches the inputted class name with the FitnessClasses enum and adds member to the class database.
     * @param member the Member to be checked into the fitness class.
     * @param className the name of the fitness class the Member wants to check into.
     * @return true if Member was checked into the class, false if were not.
     */
    public boolean checkIn(Member member) {
        /*switch(className.toUpperCase()) {
            case "PILATES":
                return pilates.add(member);
            case "SPINNING":
                return spinning.add(member);
            case "CARDIO":
                return cardio.add(member);
            default:
                return false;
        }*/

        if(fitClass.contains(member)) {
            return false;
        }
        else {
            fitClass.add(member);
            return true;
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
    /*public FitnessClasses checkTimeConflict(Member member, FitnessClasses fclass) {
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
    }*/

    /**
     * Drops member from specified class if they are in it.
     * Matches the inputted class name with the FitnessClasses enum and removes member from the class database.
     * @param member the Member in the specified fitness class that wants to drop it.
     * @param className the name of the fitness class the Member wants to drop.
     * @return true if Member dropped the class, false if they were not able to.
     */
    public boolean drop(Member member) {
        /*switch(className.toUpperCase()) {
            case "PILATES":
                return pilates.remove(member);
            case "SPINNING":
                return spinning.remove(member);
            case "CARDIO":
                return cardio.remove(member);
            default:
                return false;
        }*/

        if (!fitClass.contains(member)) {
            return false;
        }
        else {
            fitClass.remove(member);
        }
    }

    public FitnessClasses getClassName() {
        return className;
    }

    public Instructors getInstructor() {
        return instructor;
    }

    public Location getLocation() {
        return location;
    }

    public Time getTime() {
        return time;
    }
    public Member getMember(Member member) {
        int index = fitClass.indexOf(member);
        if(index != -1) {
            return fitClass.get(index);
        }
        return null;
    }

    public int getLength() {
        return fitClass.size();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FitnessClass) {
            FitnessClass fitClass = (FitnessClass) obj;
            if(fitClass.getClassName().equals(this.getClassName()) &&
                    fitClass.getInstructor().equals(this.getInstructor()) &&
                    fitClass.getLocation().equals(this.getLocation())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String str = "";
        str = getClassName().name() + "-" + getInstructor().name() + ", "
                + getTime().toString() + ", " + getLocation().name();

        if(!fitClass.isEmpty()) {
            str += "\n- Participants -\n\t";

            for(Member m : fitClass) {
                str = str + m.toString() + "\n";
            }
        }

        return str;
    }


    /**
     * Prints schedule of the fitness classes.
     * Lists the name of the fitness class and all the members in the class database for all fitness classes.
     */
    /*public void printSchedule() {
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
    }*/

}