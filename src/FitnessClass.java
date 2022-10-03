public class FitnessClass {
    private MemberDatabase pilates;
    private MemberDatabase spinning;
    private MemberDatabase cardio;

    public FitnessClass() {
        pilates = new MemberDatabase();
        spinning = new MemberDatabase();
        cardio = new MemberDatabase();
    }

    public boolean checkIn(Member m, String className) {
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
            default:
                return false;
        }
        return true;
        //Compare times to check for overlap between classes
    }

    public boolean dropClass(Member m, String className) {
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
            default:
                return false;
        }
        return true;
    }

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
