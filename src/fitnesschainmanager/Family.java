package fitnesschainmanager;

public class Family extends Member {
    private int numGuestPass;

    public Family() {
        super();
        numGuestPass = 0;
    }

    public Family(String fname, String lname, Date dob, Date expire, Location location, int numGuestPass) {
        super(fname, lname, dob, expire, location);
        this.numGuestPass = numGuestPass;
    }

    public void setNumGuestPass(int numGuestPass) {
        this.numGuestPass = numGuestPass;
    }

    @Override
    public double membershipFee() {
        return MembershipFees.ONE_TIME_FEE.getValue() + MembershipFees.STANDARD_LENGTH.getValue()
                * MembershipFees.FAMILY_MONTHLY_FEE.getValue();
    }
}
