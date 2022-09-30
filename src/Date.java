public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date() {

    } //create an object with today’s date (see Calendar class)

    public Date(String date) {
        int tempNum, count = 0;
        String temp = "";

        for(int i = 0; i < date.length(); i++) {
            char c = date.charAt(i);
            if(c == '/') {
                tempNum = Integer.parseInt(temp);
                temp = "";
                if(count == 0) {
                    month = tempNum;
                    count++;
                }
                else if(count == 1) {
                    day = tempNum;
                    count++;
                }
                else {
                    year = tempNum;
                }
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