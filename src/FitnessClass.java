public class FitnessClass {
    private MemberDatabase pilates;
    private MemberDatabase spinning;
    private MemberDatabase cardio;

    public FitnessClass() {
        pilates = new MemberDatabase();
        spinning = new MemberDatabase();
        cardio = new MemberDatabase();
    }

    public void checkIn(Member m, String className) {
        switch(className.toUpperCase()) {
            case "PILATES":
                pilates.add(m);
                break;
            case "SPINNING":
                spinning.add(m);
                break;
            case "CARDIO":
                cardio.add(m);
                break;
        }
    }

    public void dropClass(Member m, String className) {
        switch(className.toUpperCase()) {
            case "PILATES":
                pilates.remove(m);
                break;
            case "SPINNING":
                spinning.remove(m);
                break;
            case "CARDIO":
                cardio.remove(m);
                break;
        }
    }

    public void printSchedule() {
        System.out.println("-Fitness classes-");
        System.out.println(FitnessClasses.PILATES.toString());
        if(!pilates.isEmpty()) {
            System.out.println("\t** participants **");
            for (int i = 0; i < pilates.getSize(); i++) {
                System.out.print;
            }
        }

        System.out.println(FitnessClasses.SPINNING.toString());
        if(!spinning.isEmpty()) {
            System.out.println("\t** participants **");
        }

        System.out.println(FitnessClasses.CARDIO.toString());
        if(!cardio.isEmpty()) {
            System.out.println("\t** participants **");
        }

    }




}
