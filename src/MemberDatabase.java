public class MemberDatabase {
    private Member [] mlist;
    private int size;

    public static final int MLIST_GROWTH_RATE = 4;
    public MemberDatabase() {
        mlist = new Member[Constants.INITIAL_CAPACITY.getConstant()];
        size = 0; // number of members
    }

    private int find(Member member) {
        for(int i = 0; i < size; i++) {
            if(member.equals(mlist[i])) {
                return i;
            }
        }
        return Constants.NOT_FOUND.getConstant();
    }
    private void grow() {

    }
    public boolean add(Member member) {
        if(!member.isValid()) {
            return false;
        }
        if(find(member) == Constants.NOT_FOUND.getConstant()) {
            return false;
        }
//NOTE TO SELF/DAVID: WE GOTTA USE GROW METHOD PROLLY BUT IDK IF UR FINISHED WITH THIS YET
        mlist[size] = member;
        size += MLIST_GROWTH_RATE;

        if (size == mlist.length) {
            // Increase array size
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
    public void print () { } //print the array contents as is
    public void printByCounty() { } //sort by county and then zipcode
    public void printByExpirationDate() { } //sort by the expiration date
    public void printByName() { } //sort by last name and then first name
}
