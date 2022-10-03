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
                return pilates.add(m);
            case "SPINNING":
                return spinning.add(m);
            case "CARDIO":
                return cardio.add(m);
            default:
                return false;
        }
    }

    public FitnessClasses checkTimeConflict(Member m, FitnessClasses fclass) {
        if(fclass != FitnessClasses.PILATES && pilates.memberCheck(m)) {
            if(fclass.getTime().equals(FitnessClasses.PILATES.getTime())) {
                return FitnessClasses.PILATES;
            }
        }
        if(fclass != FitnessClasses.SPINNING && spinning.memberCheck(m)) {
            if(fclass.getTime().equals(FitnessClasses.SPINNING.getTime())) {
                return FitnessClasses.SPINNING;
            }
        }
        if(fclass != FitnessClasses.CARDIO && cardio.memberCheck(m)) {
            if(fclass.getTime().equals(FitnessClasses.CARDIO.getTime())) {
                return FitnessClasses.CARDIO;
            }
        }
        return true;
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
