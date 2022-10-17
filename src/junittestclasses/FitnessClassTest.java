package junittestclasses;

import fitnesschainmanager.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test classes for the methods that add and remove members and guests from a fitness class.
 * @author David Ma, Ethan Kwok
 */
class FitnessClassTest {

    /**
     * Return true if the Member does not exist in the database and is thus able to be successfully checked in.
     */
    @Test
    void check_in_standard() {
        FitnessClass fc = new FitnessClass();
        Member m = new Member();
        assertTrue(fc.checkIn(m));
    }

    /**
     * Return true if the Family Member does not exist in the database and is thus able to be successfully checked in.
     */
    @Test
    void check_in_family() {
        FitnessClass fc = new FitnessClass();
        Family f = new Family();
        assertTrue(fc.checkIn(f));
    }

    /**
     * Return true if the Premium Member does not exist in the database and is thus able to be successfully checked in.
     */
    @Test
    void check_in_premium() {
        FitnessClass fc = new FitnessClass();
        Premium p = new Premium();
        assertTrue(fc.checkIn(p));
    }

    /**
     * Return false if the Member exists in the database and is thus unable to be successfully checked in.
     */
    @Test
    void check_in_existing_member() {
        FitnessClass fc = new FitnessClass();
        Member m1 = new Member();
        m1.setFname("John");
        m1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        m1.setDob(d1);
        fc.checkIn(m1);

        Member m2 = new Member();
        m2.setFname("John");
        m2.setLname("Doe");
        Date d2 = new Date("8/22/2002");
        m2.setDob(d2);

        assertFalse(fc.checkIn(m2));
    }

    /**
     * Return false if the Family Member exists in the database and is thus unable to be successfully checked in.
     */
    @Test
    void check_in_existing_family() {
        FitnessClass fc = new FitnessClass();
        Family f1 = new Family();
        f1.setFname("John");
        f1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        f1.setDob(d1);
        fc.checkIn(f1);

        Family f2 = new Family();
        f2.setFname("John");
        f2.setLname("Doe");
        Date d2 = new Date("8/22/2002");
        f2.setDob(d2);

        assertFalse(fc.checkIn(f2));
    }

    /**
     * Return false if the Premium Member exists in the database and is thus unable to be successfully checked in.
     */
    @Test
    void check_in_existing_premium() {
        FitnessClass fc = new FitnessClass();
        Premium p1 = new Premium();
        p1.setFname("John");
        p1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        p1.setDob(d1);
        fc.checkIn(p1);

        Premium p2 = new Premium();
        p2.setFname("John");
        p2.setLname("Doe");
        Date d2 = new Date("8/22/2002");
        p2.setDob(d2);

        assertFalse(fc.checkIn(p2));
    }

    /**
     * Return true if the Family Member has enough guest passes to successfully check in a guest.
     */
    @Test
    void check_in_family_guest_with_passes() {
        FitnessClass fc = new FitnessClass();
        Family f1 = new Family();
        f1.setFname("John");
        f1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        f1.setDob(d1);
        f1.setNumGuestPass((int)MembershipFees.FAMILY_GUEST_PASSES.getValue());

        assertTrue(fc.checkInGuest(f1));
    }

    /**
     * Return false if the Family Member does not have enough guest passes to successfully check in a guest.
     */
    @Test
    void check_in_family_guest_without_passes() {
        FitnessClass fc = new FitnessClass();
        Family f1 = new Family();
        f1.setFname("John");
        f1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        f1.setDob(d1);
        f1.setNumGuestPass((int)MembershipFees.FAMILY_GUEST_PASSES.getValue());
        fc.checkInGuest(f1); //Guest pass used

        assertFalse(fc.checkInGuest(f1));
    }

    /**
     * Return true if the Premium Member has enough guest passes to successfully check in a guest.
     */
    @Test
    void check_in_premium_guest_with_passes() {
        FitnessClass fc = new FitnessClass();
        Premium p1 = new Premium();
        p1.setFname("John");
        p1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        p1.setDob(d1);
        p1.setNumGuestPass((int)MembershipFees.PREMIUM_GUEST_PASSES.getValue());

        assertTrue(fc.checkInGuest(p1));
    }

    /**
     * Return false if the Premium Member does not have enough guest passes to successfully check in a guest.
     */
    @Test
    void check_in_premium_guest_without_passes() {
        FitnessClass fc = new FitnessClass();
        Premium p1 = new Premium();
        p1.setFname("John");
        p1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        p1.setDob(d1);
        p1.setNumGuestPass((int)MembershipFees.PREMIUM_GUEST_PASSES.getValue());
        fc.checkInGuest(p1); //Guest pass used
        fc.checkInGuest(p1); //Guest pass used
        fc.checkInGuest(p1); //Guest pass used

        assertFalse(fc.checkInGuest(p1));
    }

    /**
     * Return false if the Family Member tries to drop a guest but does not have any guests in the class.
     */
    @Test
    void drop_family_guest_without_guests() {
        FitnessClass fc = new FitnessClass();
        Family f1 = new Family();
        f1.setFname("John");
        f1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        f1.setDob(d1);
        f1.setNumGuestPass((int)MembershipFees.FAMILY_GUEST_PASSES.getValue());

        assertFalse(fc.dropGuest(f1));
    }

    /**
     * Return true if the Family Member tries to drop a guest and does have a guest in the class.
     */
    @Test
    void drop_family_guest_with_guests() {
        FitnessClass fc = new FitnessClass();
        Family f1 = new Family();
        f1.setFname("John");
        f1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        f1.setDob(d1);
        f1.setNumGuestPass((int)MembershipFees.FAMILY_GUEST_PASSES.getValue());
        fc.checkInGuest(f1); //Guest pass used

        assertTrue(fc.dropGuest(f1));
    }

    /**
     * Return false if the Premium Member tries to drop a guest but does not have any guests in the class.
     */
    @Test
    void drop_premium_guest_without_guests() {
        FitnessClass fc = new FitnessClass();
        Premium p1 = new Premium();
        p1.setFname("John");
        p1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        p1.setDob(d1);
        p1.setNumGuestPass((int)MembershipFees.PREMIUM_GUEST_PASSES.getValue());

        assertFalse(fc.dropGuest(p1));
    }

    /**
     * Return true if the Premium Member tries to drop a guest and does have a guest in the class.
     */
    @Test
    void drop_premium_guest_with_guests() {
        FitnessClass fc = new FitnessClass();
        Premium p1 = new Premium();
        p1.setFname("John");
        p1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        p1.setDob(d1);
        p1.setNumGuestPass((int)MembershipFees.PREMIUM_GUEST_PASSES.getValue());
        fc.checkInGuest(p1); //Guest pass used
        fc.checkInGuest(p1); //Guest pass used
        fc.checkInGuest(p1); //Guest pass used

        assertTrue(fc.dropGuest(p1));
    }

    /**
     * Return false if the Member being dropped is not already checked in and in the FitnessClass.
     */
    @Test
    void drop_not_checked_in_standard() {
        FitnessClass fc = new FitnessClass();
        Member m = new Member();
        assertFalse(fc.drop(m));
    }

    /**
     * Return false if the Family Member being dropped is not already checked in and in the FitnessClass.
     */
    @Test
    void drop_not_checked_in_family() {
        FitnessClass fc = new FitnessClass();
        Family f = new Family();
        assertFalse(fc.drop(f));
    }

    /**
     * Return false if the Premium Member being dropped is not already checked in and in the FitnessClass.
     */
    @Test
    void drop_not_checked_in_premium() {
        FitnessClass fc = new FitnessClass();
        Premium p = new Premium();
        assertFalse(fc.drop(p));
    }

    /**
     * Return true if the Member being dropped is in the FitnessClass and can thus be successfully dropped.
     */
    @Test
    void drop_existing_member() {
        FitnessClass fc = new FitnessClass();
        Member m1 = new Member();
        m1.setFname("John");
        m1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        m1.setDob(d1);
        fc.checkIn(m1);

        assertTrue(fc.drop(m1));
    }

    /**
     * Return true if the Family Member being dropped is in the FitnessClass and can thus be successfully dropped.
     */
    @Test
    void drop_existing_family() {
        FitnessClass fc = new FitnessClass();
        Family f1 = new Family();
        f1.setFname("John");
        f1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        f1.setDob(d1);
        fc.checkIn(f1);

        assertTrue(fc.drop(f1));
    }

    /**
     * Return true if the Premium Member being dropped is in the FitnessClass and can thus be successfully dropped.
     */
    @Test
    void drop_existing_premium() {
        FitnessClass fc = new FitnessClass();
        Premium p1 = new Premium();
        p1.setFname("John");
        p1.setLname("Doe");
        Date d1 = new Date("8/22/2002");
        p1.setDob(d1);
        fc.checkIn(p1);

        assertTrue(fc.drop(p1));
    }

}