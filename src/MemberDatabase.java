import java.util.Calendar;

public class MemberDatabase {
    private Member[] mlist;
    private int size;

    public static final int INITIAL_CAPACITY = 4;
    public static final int GROWTH_RATE = 4;
    public static final int NOT_FOUND = 4;

    public MemberDatabase() {
        mlist = new Member[INITIAL_CAPACITY];
        size = 0; // number of members
    }

    private int find(Member member) {
        for (int i = 0; i < size; i++) {
            if (member.equals(mlist[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        Member[] newMlist = new Member[mlist.length + GROWTH_RATE];
        for (int i = 0; i < size; i++) {
            newMlist[i] = mlist[i];
        }
        mlist = newMlist;
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
        if (find(member) != NOT_FOUND) {
            for (int i = memberIndex; i < size; i++) {
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
        quickSort(0, size-1);
        print();

    } //sort by last name and then first name

    public void quickSort(int lowerBounds, int upperBounds) {
        if (lowerBounds < upperBounds) {
            int index = partition(lowerBounds, upperBounds);
            quickSort(lowerBounds, index - 1);
            quickSort(index + 1, upperBounds);
        }
    }

    public int partition(int lowerBounds, int upperBounds) {
        Member pivot = mlist[upperBounds];
        int lowPtr = lowerBounds - 1;
        for (int i = lowerBounds; i < upperBounds; i++) {
            if (mlist[i].compareTo(pivot) < 0) { //TEMP should it be < or <=
                lowPtr++;
                swap(lowPtr, i);
            }
        }

        swap(lowPtr + 1, upperBounds);
        return lowPtr + 1;
    }

    public void swap(int first, int second) {
        Member temp = mlist[first];
        mlist[first] = mlist[second];
        mlist[second] = temp;
    }


    public boolean isEmpty() {
        if(size != 0) {
            return false;
        }
        return true;
    }
}
