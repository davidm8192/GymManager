package fitnesschainmanager;

/**
 * Family subclass Premium holds data used for Premium objects and returns the data and information comparing the data.
 * The information and methods can be retrieved from its super class Family. The data stored is the number of guest
 * passes owned by the Premium Member. This int variable can be changed and retrieved with setter and getter methods.
 * The fee and type of membership can also be set and returned.
 * @author David Ma, Ethan Kwok
 */
public class Premium extends Family {

    /**
     * Inherits the data from Family superclass into the Premium Member.
     */
    public Premium() {
        super();
    }

    /**
     * Gets the number of guest passes that the Family Member has left.
     * @return number of guest passes of the Family Member.
     */
    @Override
    public int getNumGuestPass() {
        return super.getNumGuestPass();
    }

    /**
     * Calculates and returns a member's fees for a premium membership. This cost is given by the cost of a one-time
     * fee, the cost of the monthly fees, and the payment frequency.
     * @return double representing the unrounded fee in dollars of the Premium Member.
     */
    @Override
    public double membershipFee() {
        return MembershipFees.PREMIUM_LENGTH.getValue() * MembershipFees.FAMILY_MONTHLY_FEE.getValue() -
                MembershipFees.FAMILY_MONTHLY_FEE.getValue(); //1 free month
    }

    /**
     * Adds to the Premium Member's information String given in the superclass's toString() method. Specifically,
     * it adds the membership type "Premium". It then adds the number of guest passes remaining for the Premium Member.
     * @return String representing the Member's information following the format:
     *         First Name Last Name, DOB: mm/dd/yy: Membership expires mm/dd/yy, Location: TOWN, zipcode, COUNTY,
     *         (Premium) guest-pass remaining: numberOfGuestPasses
     */
    @Override
    public String toString() {
        String output = super.toString();
        output = output + ", (Premium) guest-pass remaining: " + getNumGuestPass();
        return output;
    }
}