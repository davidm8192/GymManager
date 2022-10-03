import java.util.Calendar;

public class MemberDatabase {
    private Member[] mlist;
    private int size;

    public static final int INITIAL_CAPACITY = 4;
    public static final int GROWTH_RATE = 4;
    public static final int NOT_FOUND = 4;

    public static final int SORT_BY_NAME = 0;

    public static final int SORT_BY_EXPIRATION = 1;

    public static final int SORT_BY_LOCATION = 2;

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
        System.out.println("-end of list-");
    } //print the array contents as is

    public void printByCounty() {
        if (isEmpty()) {
            System.out.println("Member database is empty!");
            return;
        }
        int sortType = SORT_BY_LOCATION;
        quickSort(0, size-1, sortType);

        System.out.println("-list of members sorted by county and zipcode-");
        for (int i = 0; i < size; i++) {
            if (mlist[i] != null) {
                System.out.println(mlist[i].toString());
            }
        }
        System.out.println("-end of list-");
    } //sort by county and then zipcode

    public void printByExpirationDate() {
        if (isEmpty()) {
            System.out.println("Member database is empty!");
            return;
        }
        int sortType = SORT_BY_EXPIRATION;
        quickSort(0, size-1, sortType);

        System.out.println("-list of members sorted by membership expiration date-");
        for (int i = 0; i < size; i++) {
            if (mlist[i] != null) {
                System.out.println(mlist[i].toString());
            }
        }
        System.out.println("-end of list-");
    } //sort by the expiration date

    public void printByName() {
        if (isEmpty()) {
            System.out.println("Member database is empty!");
            return;
        }
        int sortType = SORT_BY_NAME;
        quickSort(0, size-1, sortType);

        System.out.println("-list of members sorted by last name, and first name-");
        for (int i = 0; i < size; i++) {
            if (mlist[i] != null) {
                System.out.println(mlist[i].toString());
            }
        }
        System.out.println("-end of list-");
    } //sort by last name and then first name

    public void quickSort(int lowerBounds, int upperBounds, int sortType) {
        if (lowerBounds < upperBounds) {
            int index = partition(lowerBounds, upperBounds, sortType);
            quickSort(lowerBounds, index - 1, sortType);
            quickSort(index + 1, upperBounds, sortType);
        }
    }

    public int partition(int lowerBounds, int upperBounds, int sortType) {
        Member pivot = mlist[upperBounds];
        int lowPtr = lowerBounds - 1;
        for (int i = lowerBounds; i < upperBounds; i++) {

            if (sortType == SORT_BY_NAME) {
                if (mlist[i].compareTo(pivot) <= 0) {
                    lowPtr++;
                    swap(lowPtr, i);
                }
            }

            if (sortType == SORT_BY_EXPIRATION) {
                if (mlist[i].getExpire().compareTo(pivot.getExpire()) <= 0) {
                    lowPtr++;
                    swap(lowPtr, i);
                }
            }

            if (sortType == SORT_BY_LOCATION) {
                if (mlist[i].compareLocation(pivot) <= 0) {
                    lowPtr++;
                    swap(lowPtr, i);
                }
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