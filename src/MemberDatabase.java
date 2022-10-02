import java.util.Calendar;

public class MemberDatabase {
    private Member[] mlist;
    private int size;

    public static final int MLIST_GROWTH_RATE = 4;
    public MemberDatabase() {
        mlist = new Member[Constants.INITIAL_CAPACITY.getConstant()];
        size = 0; // number of members
    }

    private int find(Member member) {
        for (int i = 0; i < size; i++) {
            if (member.equals(mlist[i])) {
                return i;
            }
        }
        return Constants.NOT_FOUND.getConstant();
    }
    private void grow() {

    }
    public boolean add(Member member) {

        if (find(member) != NOT_FOUND) {
            return false;
        }

        mlist[size] = member;
        size++;

        if (size == mlist.length) {
            grow();
            return true;
        }

        return true;
    }

    public boolean remove(Member member) {
        int memberIndex = find(member);
        if(find(member) != Constants.NOT_FOUND.getConstant()) {
            for(int i = memberIndex; i < size; i++) {
                mlist[i] = mlist[i + 1];
            }
            size--;
            return true;
        }
        return false;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println("-list of members-");
        for (int i = 0; i < size; i++) {
            if (mlist[i] != null) {
                System.out.println(mlist[i].toString());
            }
        }
        //System.out.println(mlist[0].toString());
        System.out.println("-end of list-");
        return;
    } //print the array contents as is

    public void printByCounty() {
    } //sort by county and then zipcode

    public void printByExpirationDate() {
    } //sort by the expiration date

    public void printByName() {
        if (isEmpty()) {
            System.out.println("Member database is empty!");
            return;
        }

    } //sort by last name and then first name

    /*public Member [] quickSort(Member [] memberList, int lowerBounds, int upperBounds) {
        if (lowerBounds < upperBounds) {
            int pivot = partition(memberList, lowerBounds, upperBounds)
        }
    }

    public int partition(Member [] memberList, int upperBounds, int lowerBounds) {

    }*/

    public boolean isEmpty() {
        if(size != 0) {
            return false;
        }
        return true;
    }
}
