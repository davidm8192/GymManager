package JUnitTestClasses;

import fitnesschainmanager.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    /**
     * Ensure February has < 29 days for nonleap years
     */
    @Test
    public void test_days_in_Feb_nonLeap() {
        Date date1 = new Date("2/29/2003");
        assertFalse(date1.isValid());

        Date date2 = new Date("2/28/2000");
        assertTrue(date2.isValid());
    }

    /**
     * Ensure February has <= 29 days for leap years
     */
    @Test
    public void test_days_in_Feb_leap() {
        Date date1 = new Date("2/30/2000");
        assertFalse(date1.isValid());

        Date date2 = new Date("2/29/2000");
        assertTrue(date2.isValid());
    }

    /**
     * Valid range for the month shall be 1-12
     */
    @Test
    public void test_month_range() {
        Date date1 = new Date("-1/31/2003");
        assertFalse(date1.isValid());

        Date date2 = new Date("13/31/2003");
        assertFalse(date2.isValid());

        Date date3 = new Date("1/30/2003");
        assertTrue(date3.isValid());

        Date date4 = new Date("12/30/2003");
        assertTrue(date4.isValid());

        Date date5 = new Date("0/30/2003");
        assertFalse(date5.isValid());
    }

    /**
     * Number of days in any month shall not be less than 1
     */
    @Test
    public void test_day_min() {
        Date date1 = new Date("1/0/2020");
        assertFalse(date1.isValid());

        Date date2 = new Date("12/0/2020");
        assertFalse(date2.isValid());

        Date date3 = new Date("1/1/2020");
        assertTrue(date3.isValid());

        Date date4 = new Date("12/1/2020");
        assertTrue(date4.isValid());
    }

    /**
     * Number of days in a given month shall not be greater than the
     * maximum number of days that month should have
     */
    @Test
    public void test_day_max() {
        Date date1 = new Date("1/32/2020");
        assertFalse(date1.isValid());

        Date date2 = new Date("3/32/2003");
        assertFalse(date2.isValid());

        Date date3 = new Date("4/31/2003");
        assertFalse(date3.isValid());

        Date date4 = new Date("4/31/2022");
        assertFalse(date4.isValid());

        Date date5 = new Date("5/32/2020");
        assertFalse(date5.isValid());

        Date date6 = new Date("6/31/2020");
        assertFalse(date6.isValid());

        Date date7 = new Date("7/32/2020");
        assertFalse(date7.isValid());

        Date date8 = new Date("8/32/2020");
        assertFalse(date8.isValid());

        Date date9 = new Date("9/31/2020");
        assertFalse(date9.isValid());

        Date date10 = new Date("10/32/2020");
        assertFalse(date10.isValid());

        Date date11 = new Date("11/31/2020");
        assertFalse(date11.isValid());

        Date date12 = new Date("12/32/1989");
        assertFalse(date12.isValid());
    }

    /**
     * Number of days in a given month shall be less than or equal
     * to the maximum number of days that month should have
     */
    @Test
    public void test_day_allowed() {
        Date date1 = new Date("1/20/2004");
        assertTrue(date1.isValid());

        Date date2 = new Date("1/20/2003");
        assertTrue(date2.isValid());

        Date date3 = new Date("1/31/2023");
        assertTrue(date3.isValid());

        Date date4 = new Date("3/30/2023");
        assertTrue(date4.isValid());

        Date date5 = new Date("3/30/2021");
        assertTrue(date5.isValid());

        Date date6 = new Date("3/31/1990");
        assertTrue(date6.isValid());

        Date date7 = new Date("3/31/2023");
        assertTrue(date7.isValid());

        Date date8 = new Date("4/3/2003");
        assertTrue(date8.isValid());

        Date date9 = new Date("5/1/1996");
        assertTrue(date9.isValid());

        Date date10 = new Date("5/31/2023");
        assertTrue(date10.isValid());

        Date date11 = new Date("5/1/1999");
        assertTrue(date11.isValid());

        Date date12 = new Date("6/30/2023");
        assertTrue(date12.isValid());

        Date date13 = new Date("6/30/1999");
        assertTrue(date13.isValid());

        Date date14 = new Date("7/15/1977");
        assertTrue(date14.isValid());

        Date date15 = new Date("8/8/1977");
        assertTrue(date15.isValid());

        Date date16 = new Date("9/2/2022");
        assertTrue(date16.isValid());

        Date date17 = new Date("9/30/2023");
        assertTrue(date17.isValid());

        Date date18 = new Date("9/30/2020");
        assertTrue(date18.isValid());

        Date date19 = new Date("9/9/1977");
        assertTrue(date19.isValid());

        Date date20 = new Date("10/7/1991");
        assertTrue(date20.isValid());

        Date date21 = new Date("11/20/2003");
        assertTrue(date21.isValid());

        Date date22 = new Date("12/2/2022");
        assertTrue(date22.isValid());

        Date date23 = new Date("12/20/2004");
        assertTrue(date23.isValid());

        Date date24 = new Date("12/1/1989");
        assertTrue(date24.isValid());

        Date date25 = new Date("12/31/2023");
        assertTrue(date25.isValid());
    }

}