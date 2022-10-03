public enum FitnessClasses {

    PILATES("Jennifer", Time.MORNING),
    SPINNING("Denise", Time.AFTERNOON),
    CARDIO("Kim", Time.AFTERNOON);

    private String instructorName;
    private Time time;

    FitnessClasses(String instructorName, Time time) {
        this.instructorName = instructorName;
        this.time = time;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public Time getTime() {
        return time;
    }

    public String getClassName() {
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase();
    }

    public String toString() {
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase()
                + " - " + instructorName.toUpperCase() + " " + time.toString();
    }

}
