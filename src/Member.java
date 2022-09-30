public class Member implements Comparable<Member>{
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    public Member() {

    }

    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }


    @Override
    public String toString() {
        String output = fname + " " + lname + ",  DOB: " + /*dob*/ + " , Membership expires " + /*expire*/ + ", Location: " + /*Location*/;
    }
    @Override
    public boolean equals(Object obj) {

    }
    @Override
    public int compareTo(Member member) { }
}