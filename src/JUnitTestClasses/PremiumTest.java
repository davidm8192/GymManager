package JUnitTestClasses;

import fitnesschainmanager.Family;
import fitnesschainmanager.Member;
import fitnesschainmanager.Premium;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PremiumTest {

    /**
     * The standard membership is different from the premium membership. It should be:
     * one-time fee + (quarterly fee cost per month * number of months in a quarter)
     * So $29.99 + ($39.99 * 3)
     */
    @Test
    public void test_standard_member_fee() {
        Member m = new Member();
        double expected = 149.96;
        double actual = m.membershipFee();
        assertEquals(expected, actual);
    }

    /**
     * The family membership is different from the premium membership. It should be:
     * one-time fee + (quarterly fee cost per month * number of months in a quarter)
     * So $29.99 + ($59.99 * 3)
     */
    @Test
    public void test_family_fee() {
        Family f = new Family();
        double expected = 209.96;
        double actual = f.membershipFee();
        assertEquals(expected, actual);
    }

    /**
     * The family membership should be:
     * annual fee cost per month * (number of months in a year - 1 free month)
     * $59.99 * (12 - 1)
     */
    @Test
    public void test_premium_fee() {
        Premium p = new Premium();
        double expected = 659.89;
        double actual = p.membershipFee();
        assertEquals(expected, actual);
    }

}