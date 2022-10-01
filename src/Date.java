import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int THIRTYDAYMONTH = 30;
    public static final int THIRTYONEDAYMONTH = 31;
    public static final int FEBRUARYNONLEAP = 28;
    public static final int FEBRUARYLEAP = 29;
    public static final int MINDAY = 1;
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;


    public Date() {
        Calendar c = Calendar.getInstance();
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DATE);
        year = c.get(Calendar.YEAR);
    } //create an object with today’s date (see Calendar class)

    public Date(String date) {
        int tempNum, count = 0;
        String temp = "";

        for(int i = 0; i < date.length(); i++) {
            char c = date.charAt(i);
            if(c == '/' && count < 2) {
                tempNum = Integer.parseInt(temp);
                temp = "";
                if(count == 0) {
                    month = tempNum;
                    count++;
                }
                else {
                    day = tempNum;
                    count++;
                }
            }
            else if(count == 2 && i == date.length() - 1) {
                temp += c;
                tempNum = Integer.parseInt(temp);
                year = tempNum;
            }
            else {
                temp += c;
            }
        }

    } //take “mm/dd/yyyy” and create a Date object

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public int compareTo(Date date) {
        // Current year is greater: current date is AFTER
        if(this.getYear() > date.getYear()) {
            return 1;
        }
        // If years are same, compare months
        else if(this.getYear() == date.getYear()) {
            // Current year is the same, month is greater: current date is AFTER
            if(this.getMonth() > date.getMonth()) {
                return 1;
            }
            // If months are same, compare days
            else if(this.getMonth() == date.getMonth()) {
                // Current year, month are the same, day is greater: current date is AFTER
                if(this.getDay() > date.getDay()) {
                    return 1;
                }
                // Current year, month, day are the same: dates are the same
                else if(this.getDay() == date.getDay()) {
                    return 0;
                }
                // Current year, month are the same, day is less: current date is BEFORE
                else {
                    return -1;
                }
            }
            // Current year is same, month is less: current date is BEFORE
            else {
                return -1;
            }
        }
        // Current year is less: current date is BEFORE
        else {
            return -1;
        }
    }
    public boolean isValid() {

        if (getDay() < MINDAY) return false;
        if (getMonth() < JANUARY || getMonth() > DECEMBER) return false;
        if (getMonth() == APRIL || getMonth() == JUNE || getMonth() == SEPTEMBER || getMonth() == NOVEMBER) {
            if (getDay() > THIRTYDAYMONTH) return false;
        }
        else if (getMonth() == FEBRUARY && !isLeapYear(getYear())) {
            if (getDay() > FEBRUARYNONLEAP) return false;
        }
        else if (getMonth() == FEBRUARY && isLeapYear(getYear())) {
            if (getDay() > FEBRUARYLEAP) return false;
        }
        else {
            if (getDay() > THIRTYONEDAYMONTH) return false;
        }

    } //check if a date is a valid calendar date

    public boolean isLeapYear(int year) {
        if (year%QUADRENNIAL == 0) {
            if (year%CENTENNIAL == 0 && year%QUATERCENTENNIAL == 0) {
                return true;
            }
            if (year%CENTENNIAL == 0 && year%QUATERCENTENNIAL != 0) {
                return false;
            }
            return true;
        }
        return false;
    }
}