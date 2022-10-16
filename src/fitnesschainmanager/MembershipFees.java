package fitnesschainmanager;

public enum MembershipFees {

    STANDARD_LENGTH(3),
    FAMILY_LENGTH(3),
    PREMIUM_LENGTH(12),
    ONE_TIME_FEE(29.99),
    STANDARD_MONTHLY_FEE(39.99),
    FAMILY_MONTHLY_FEE(59.99),
    FAMILY_GUEST_PASSES(1),
    PREMIUM_GUEST_PASSES(3);

    private final double value;

    MembershipFees(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
