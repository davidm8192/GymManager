import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

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

    @Override
    public int compareTo(Date date) { }
    public boolean isValid() { } //check if a date is a valid calendar date
}