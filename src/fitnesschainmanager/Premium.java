package fitnesschainmanager;

public class Premium extends Family {
    public Premium() {
        super();
    }

    public Premium(String fname, String lname, Date dob, Date expire, Location location, int numGuestPasses) {
        super(fname, lname, dob, expire, location, numGuestPasses);
    }

    @Override
    public double membershipFee() {
        return MembershipFees.PREMIUM_LENGTH.getValue()
                * MembershipFees.FAMILY_MONTHLY_FEE.getValue();
    }
}
