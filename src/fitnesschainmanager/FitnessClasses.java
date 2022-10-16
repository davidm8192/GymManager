package fitnesschainmanager;

/**
 * FitnessClasses enum holds the gym's fitness classes.
 * Stores instructor name and time slot of class.
 * @author David Ma, Ethan Kwok
 */
public enum FitnessClasses {

    PILATES,
    SPINNING,
    CARDIO;

    private String instructorName;
    private Time time;

    /**
     * Creates FitnessClasses object.
     * @param instructorName String containing name of the instructor.
     * @param time Time enum of the time slot of the class.
     */
    FitnessClasses(String instructorName, Time time) {
        this.instructorName = instructorName;
        this.time = time;
    }

    /**
     * Gets the name of the instructor of a given class.
     * @return the String name of the instructor.
     */
    public String getInstructorName() {
        return instructorName;
    }

    /**
     * Gets the time of a given class.
     * @return the Time enum.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Gets the name of a given class as a String.
     * @return the String name of the class.
     */
    public String getClassName() {
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase();
    }

    /**
     * Converts a class to a String with proper capitalization.
     * Includes instructor name and class time.
     * @return the String representing information on the class.
     */
    public String toString() {
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase()
                + " - " + instructorName.toUpperCase() + " " + time.toString();
    }

}
