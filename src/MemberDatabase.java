public class MemberDatabase {
    private Member [] mlist;
    private int size;
    private int find(Member member) { }
    private void grow() { }
    public boolean add(Member member) {
        // isValid

        int counter = size - 1;

        if(mlist[counter] != null) {
            // Increase array size
            mList[counter + 1] = member;
            return true;
        }

        while(mlist[counter] == null) {
            counter--;
        }
        mList[counter + 1] = member;

        return true;
    }
    public boolean remove(Member member) {
        // Iterate through array until member is found, set index to null
        return true;
    }
    public void print () { } //print the array contents as is
    public void printByCounty() { } //sort by county and then zipcode
    public void printByExpirationDate() { } //sort by the expiration date
    public void printByName() { } //sort by last name and then first name
}
