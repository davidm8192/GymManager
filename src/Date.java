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

    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public static void main(String[] args) {
        //test case 1
        Date testcase1 = new Date("2/29/2003");
        System.out.print("Test case 1; expected output false; test output ");
        if (testcase1.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 2
        Date testcase2 = new Date("2/29/2000");
        System.out.print("Test case 2; expected output true; test output ");
        if (testcase2.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 3
        Date testcase3 = new Date("2/30/2011");
        System.out.print("Test case 3; expected output false; test output ");
        if (testcase3.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 4
        Date testcase4 = new Date("-1/31/2003");
        System.out.print("Test case 4; expected output false; test output ");
        if (testcase4.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 5
        Date testcase5 = new Date("13/31/2003");
        Date testcase5Second = new Date("13/8/1977");
        System.out.print("Test case 5; expected output false & false; test output ");
        if (testcase5.isValid()) System.out.print("true");
        else System.out.print("false");
        if (testcase5Second.isValid()) System.out.println(" & true");
        else System.out.println(" & false");

        //test case 6
        Date testcase6 = new Date("1/0/2020");
        System.out.print("Test case 6; expected output false; test output ");
        if (testcase6.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 7
        Date testcase7 = new Date("1/32/2020");
        System.out.print("Test case 7; expected output false; test output ");
        if (testcase7.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 8
        Date testcase8 = new Date("3/32/2003");
        System.out.print("Test case 8; expected output false; test output ");
        if (testcase8.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 9
        Date testcase9 = new Date("4/31/2003");
        Date testcase9Second = new Date("4/31/2022");
        System.out.print("Test case 9; expected output false & false; test output ");
        if (testcase9.isValid()) System.out.print("true");
        else System.out.print("false");
        if (testcase9Second.isValid()) System.out.println(" & true");
        else System.out.println(" & false");

        //test case 10
        Date testcase10 = new Date("5/32/2020");
        System.out.print("Test case 10; expected output false; test output ");
        if (testcase10.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 11
        Date testcase11 = new Date("6/31/2020");
        System.out.print("Test case 11; expected output false; test output ");
        if (testcase11.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 12
        Date testcase12 = new Date("7/32/2020");
        System.out.print("Test case 12; expected output false; test output ");
        if (testcase12.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 13
        Date testcase13 = new Date("8/32/2020");
        System.out.print("Test case 13; expected output false; test output ");
        if (testcase13.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 14
        Date testcase14 = new Date("9/31/2020");
        System.out.print("Test case 14; expected output false; test output ");
        if (testcase14.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 15
        Date testcase15 = new Date("10/32/2020");
        System.out.print("Test case 15; expected output false; test output ");
        if (testcase15.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 16
        Date testcase16 = new Date("11/31/2020");
        System.out.print("Test case 16; expected output false; test output ");
        if (testcase16.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 17
        Date testcase17 = new Date("12/32/1989");
        System.out.print("Test case 17; expected output false; test output ");
        if (testcase17.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 18
        Date testcase18 = new Date("1/31/2023");
        Date testcase18Second = new Date("1/20/2004");
        Date testcase18Third = new Date("1/20/2023");
        System.out.print("Test case 18; expected output true & true & true; test output ");
        if (testcase18.isValid()) System.out.print("true");
        else System.out.print("false");
        if (testcase18Second.isValid()) System.out.print(" & true");
        else System.out.print(" & false");
        if (testcase18Third.isValid()) System.out.println(" & true");
        else System.out.println(" & false");

        //test case 19
        Date testcase19 = new Date("3/30/2023");
        Date testcase19Second = new Date("3/30/2021");
        Date testcase19Third = new Date("3/31/1990");
        Date testcase19Fourth = new Date("3/31/2023");
        System.out.print("Test case 19; expected output true & true & true & true; test output ");
        if (testcase19.isValid()) System.out.print("true");
        else System.out.print("false");
        if (testcase18Second.isValid()) System.out.print(" & true");
        else System.out.print(" & false");
        if (testcase19Third.isValid()) System.out.print(" & true");
        else System.out.print(" & false");
        if (testcase19Fourth.isValid()) System.out.println(" & true");
        else System.out.println(" & false");

        //test case 20
        Date testcase20 = new Date("4/30/2003");
        System.out.print("Test case 20; expected output true; test output ");
        if (testcase20.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 21
        Date testcase21 = new Date("5/1/1996");
        Date testcase21Second = new Date("5/31/2023");
        Date testcase21Third = new Date("5/1/1999");
        System.out.print("Test case 21; expected output true & true & true; test output ");
        if (testcase21.isValid()) System.out.print("true");
        else System.out.print("false");
        if (testcase21Second.isValid()) System.out.print(" & true");
        else System.out.print(" & false");
        if (testcase21Third.isValid()) System.out.println(" & true");
        else System.out.println(" & false");

        //test case 22
        Date testcase22 = new Date("6/30/2023");
        Date testcase22Second = new Date("6/30/1999");
        System.out.print("Test case 22; expected output true & true; test output ");
        if (testcase22.isValid()) System.out.print("true");
        else System.out.print("false");
        if (testcase22Second.isValid()) System.out.println(" & true");
        else System.out.println(" & false");

        //test case 23
        Date testcase23 = new Date("7/15/1977");
        System.out.print("Test case 23; expected output true; test output ");
        if (testcase23.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 24
        Date testcase24 = new Date("8/8/1977");
        System.out.print("Test case 24; expected output true; test output ");
        if (testcase24.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 25
        Date testcase25 = new Date("9/2/2022");
        Date testcase25Second = new Date("9/30/2023");
        Date testcase25Third = new Date("9/30/2020");
        Date testcase25Fourth = new Date("9/9/1977");
        System.out.print("Test case 25; expected output true & true & true & true; test output ");
        if (testcase25.isValid()) System.out.print("true");
        else System.out.print("false");
        if (testcase25Second.isValid()) System.out.print(" & true");
        else System.out.print(" & false");
        if (testcase25Third.isValid()) System.out.print(" & true");
        else System.out.print(" & false");
        if (testcase25Fourth.isValid()) System.out.println(" & true");
        else System.out.println(" & false");

        //test case 26
        Date testcase26 = new Date("10/7/1991");
        System.out.print("Test case 26; expected output true; test output ");
        if (testcase26.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 27
        Date testcase27 = new Date("11/20/2003");
        System.out.print("Test case 27; expected output true; test output ");
        if (testcase27.isValid()) System.out.println("true");
        else System.out.println("false");

        //test case 28
        Date testcase28 = new Date("12/2/2022");
        Date testcase28Second = new Date("12/20/2004");
        Date testcase28Third = new Date("12/1/1989");
        Date testcase28Fourth = new Date("12/31/2023");
        System.out.print("Test case 28; expected output true & true & true & true; test output ");
        if (testcase28.isValid()) System.out.print("true");
        else System.out.print("false");
        if (testcase28Second.isValid()) System.out.print(" & true");
        else System.out.print(" & false");
        if (testcase28Third.isValid()) System.out.print(" & true");
        else System.out.print(" & false");
        if (testcase28Fourth.isValid()) System.out.println(" & true");
        else System.out.println(" & false");

    }
}