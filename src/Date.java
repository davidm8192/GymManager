import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int THIRTY_DAY_MONTH = 30;
    public static final int THIRTY_ONE_DAY_MONTH = 31;
    public static final int FEBRUARY_NONLEAP = 28;
    public static final int FEBRUARY_LEAP = 29;
    public static final int MIN_DAY = 1;
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
        if(this.year > date.getYear()) {
            return 1;
        }
        if(this.year < date.getYear()) {
            return -1;
        }

        if(this.month > date.getMonth()) {
            return 1;
        }
        if(this.month < date.getMonth()) {
            return -1;
        }

        if(this.day > date.getDay()) {
            return 1;
        }
        if(this.day < date.getDay()) {
            return -1;
        }

        return 0;
    }
    public boolean isValid() {

        if (getDay() < MIN_DAY) return false;
        if (getMonth() < JANUARY || getMonth() > DECEMBER) return false;
        if (getMonth() == APRIL || getMonth() == JUNE || getMonth() == SEPTEMBER || getMonth() == NOVEMBER) {
            if (getDay() > THIRTY_DAY_MONTH) return false;
        }
        else if (getMonth() == FEBRUARY && !isLeapYear(getYear())) {
            if (getDay() > FEBRUARY_NONLEAP) return false;
        }
        else if (getMonth() == FEBRUARY && isLeapYear(getYear())) {
            if (getDay() > FEBRUARY_LEAP) return false;
        }
        else {
            if (getDay() > THIRTY_ONE_DAY_MONTH) return false;
        }
        return true;

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

    public static void main(String[] args) {
        //test case 1
        Date testcase1 = new Date("2/29/2018");
        System.out.print("Test case 1; expected input false; test input ");
        if (testcase1.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 2
        Date testcase2 = new Date("2/29/2020");
        System.out.print("Test case 2; expected input true; test input ");
        if (testcase2.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 3
        Date testcase3 = new Date("2/30/2020");
        System.out.print("Test case 3; expected input false; test input ");
        if (testcase3.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 4
        Date testcase4 = new Date("0/20/2020");
        System.out.print("Test case 4; expected input false; test input ");
        if (testcase4.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 5
        Date testcase5 = new Date("13/20/2020");
        System.out.print("Test case 5; expected input false; test input ");
        if (testcase5.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 6
        Date testcase6 = new Date("1/0/2020");
        System.out.print("Test case 6; expected input false; test input ");
        if (testcase6.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 7
        Date testcase7 = new Date("1/32/2020");
        System.out.print("Test case 7; expected input false; test input ");
        if (testcase7.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 8
        Date testcase8 = new Date("3/32/2020");
        System.out.print("Test case 8; expected input false; test input ");
        if (testcase8.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 9
        Date testcase9 = new Date("4/31/2020");
        System.out.print("Test case 9; expected input false; test input ");
        if (testcase9.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 10
        Date testcase10 = new Date("5/32/2020");
        System.out.print("Test case 10; expected input false; test input ");
        if (testcase10.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 11
        Date testcase11 = new Date("6/31/2020");
        System.out.print("Test case 11; expected input false; test input ");
        if (testcase11.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 12
        Date testcase12 = new Date("7/32/2020");
        System.out.print("Test case 12; expected input false; test input ");
        if (testcase12.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 13
        Date testcase13 = new Date("8/32/2020");
        System.out.print("Test case 13; expected input false; test input ");
        if (testcase13.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 14
        Date testcase14 = new Date("9/31/2020");
        System.out.print("Test case 14; expected input false; test input ");
        if (testcase14.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 15
        Date testcase15 = new Date("10/32/2020");
        System.out.print("Test case 15; expected input false; test input ");
        if (testcase15.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 16
        Date testcase16 = new Date("11/31/2020");
        System.out.print("Test case 16; expected input false; test input ");
        if (testcase16.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 17
        Date testcase17 = new Date("12/32/2020");
        System.out.print("Test case 17; expected input false; test input ");
        if (testcase17.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 18
        Date testcase18 = new Date("1/31/2020");
        System.out.print("Test case 18; expected input true; test input ");
        if (testcase18.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 19
        Date testcase19 = new Date("3/31/2020");
        System.out.print("Test case 19; expected input true; test input ");
        if (testcase19.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 20
        Date testcase20 = new Date("4/30/2020");
        System.out.print("Test case 20; expected input true; test input ");
        if (testcase20.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 21
        Date testcase21 = new Date("5/31/2020");
        System.out.print("Test case 21; expected input true; test input ");
        if (testcase21.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 22
        Date testcase22 = new Date("6/30/2020");
        System.out.print("Test case 22; expected input true; test input ");
        if (testcase22.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 23
        Date testcase23 = new Date("7/31/2020");
        System.out.print("Test case 23; expected input true; test input ");
        if (testcase23.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 24
        Date testcase24 = new Date("8/31/2020");
        System.out.print("Test case 24; expected input true; test input ");
        if (testcase24.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 25
        Date testcase25 = new Date("9/30/2020");
        System.out.print("Test case 25; expected input true; test input ");
        if (testcase25.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 26
        Date testcase26 = new Date("10/31/2020");
        System.out.print("Test case 26; expected input true; test input ");
        if (testcase26.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 27
        Date testcase27 = new Date("11/30/2020");
        System.out.print("Test case 27; expected input true; test input ");
        if (testcase27.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 28
        Date testcase28 = new Date("12/31/2020");
        System.out.print("Test case 28; expected input true; test input ");
        if (testcase28.isValid()) System.out.println("true");
        else System.out.println("false");

    }
}