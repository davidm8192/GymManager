package fitnesschainmanager;

public class ClassSchedule {
    private FitnessClass[] fitClasses;
    private int numClasses;

    public static final int INITIAL_CAPACITY = 3;
    
    public static final int GROWTH_RATE = 3;

    public ClassSchedule() {
        numClasses = 0;
        fitClasses = new FitnessClass[INITIAL_CAPACITY];
    }

    private void grow() {
        FitnessClass[] newSchedule = new FitnessClass[fitClasses.length + GROWTH_RATE];
        for (int i = 0; i < numClasses; i++) {
            newSchedule[i] = fitClasses[i];
        }
        fitClasses = newSchedule;
    }

    public void addClass(FitnessClass fitClass) {
        fitClasses[numClasses] = fitClass;
        numClasses++;

        if (numClasses == fitClasses.length) {
            grow();
        }
    }

    public int getNumClasses() {
        return numClasses;
    }

    public FitnessClass getFitnessClass(int index) {
        if (index >= numClasses || index < 0) return null;
        return fitClasses[index];
    }


    public void printSchedule() {
        System.out.println("-Fitness classes-");
        for(int i = 0; i < numClasses; i++) {
            System.out.println(fitClasses[i].toString());
        }
        System.out.println("-end of class list-");
    }

}
