import java.util.Calendar;

public class Member implements Comparable<Member>{
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    public Member() {
        fname = "";
        lname = "";
        dob = null;
        expire = null;
        location = null;
    }

    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getDob() {
        return dob;
    }

    public Location getLocation() {
        return location;
    }

    public Date getExpire() {
        return expire;
    }

    public boolean isDobValid() {
        return dob.isValid();
    }

    public boolean isExpireValid() {
        return expire.isValid();
    }

    public boolean isAbove18() {
        Date today = new Date();
        if(today.getYear() - dob.getYear() < 18) {
            return false;
        }
        if(today.getYear() - dob.getYear() == 18) {
            if(today.getMonth() < dob.getMonth()) {
                return false;
            }
            else if(today.getMonth() == dob.getMonth()) {
                if(today.getDay() < dob.getDay()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isDobPast() {
        Date today = new Date();

        if(dob.getYear() > today.getYear()) {
            return false;
        }
        if(dob.getYear() == today.getYear()) {
            if(dob.getMonth() > today.getMonth()) {
                return false;
            }
            else if(dob.getMonth() == today.getMonth()) {
                if(dob.getDay() >= today.getDay()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidLocation() {
        if(location == null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String output = "";
        output += fname + " " + lname + ", DOB: " + dob.toString() + ", ";
        if (expire.compareTo(new Date()) < 0) output += "Membership expired ";
        else output += "Membership expires ";
        output += expire.toString() + ", Location: " + location.toString();
        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member) {
            Member member = (Member) obj;
            if(member.getFname().toUpperCase().equals(this.fname.toUpperCase()) &&
               member.getLname().toUpperCase().equals(this.lname.toUpperCase()) &&
               member.getDob().compareTo(this.dob) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Member member) {
        int compareL = this.getLname().toUpperCase().compareTo(member.getLname().toUpperCase());
        int compareF = this.getFname().toUpperCase().compareTo(member.getFname().toUpperCase());
        if(compareL > 0) {
            return 1; // current member's name is after
        }
        if(compareL < 0) {
            return -1; // current member's name is before
        }

        if(compareF > 0) {
            return 1; // current member's name is after
        }
        if(compareF < 0) {
            return -1; // current member's name is before
        }

        return 0; // names are the same
    }

    public int compareLocation(Member member) {
        int compareCounty = this.getLocation().getCounty().compareTo(member.getLocation().getCounty());
        int compareZipcode = this.getLocation().getZipCode().compareTo(member.getLocation().getZipCode());
        if(compareCounty > 0) {
            return 1; // current member's county is after
        }
        if(compareCounty < 0) {
            return -1; // current member's county is before
        }

        if(compareZipcode > 0) {
            return 1; // current member's zipcode is after
        }
        if(compareZipcode < 0) {
            return -1; // current member's zipcode is before
        }

        return 0; // names are the same
    }

    public static void main(String[] args) {
        //test case 1
        Member testcase1First = new Member();
        Member testcase1Second = new Member();
        testcase1First.setFname("Zeta");
        testcase1First.setLname("Alpha");
        testcase1Second.setFname("Alpha");
        testcase1Second.setLname("Zeta");
        System.out.println("Test case 1; expected output -1; test output " + testcase1First.compareTo(testcase1Second));

        //test case 2
        Member testcase2First = new Member();
        Member testcase2Second = new Member();
        testcase2First.setFname("Jane");
        testcase2First.setLname("Doe");
        testcase2Second.setFname("John");
        testcase2Second.setLname("Doe");
        System.out.println("Test case 2; expected output -1; test output " + testcase2First.compareTo(testcase2Second));

        //test case 3
        Member testcase3First = new Member();
        Member testcase3Second = new Member();
        testcase3First.setFname("John");
        testcase3First.setLname("Smith");
        testcase3Second.setFname("John");
        testcase3Second.setLname("Smithh");
        System.out.println("Test case 3; expected output -1; test output " + testcase3First.compareTo(testcase3Second));

        //test case 4
        Member testcase4First = new Member();
        Member testcase4Second = new Member();
        testcase4First.setFname("john");
        testcase4First.setLname("doe");
        testcase4Second.setFname("JOHN");
        testcase4Second.setLname("DOE");
        System.out.println("Test case 4; expected output 0; test output " + testcase4First.compareTo(testcase4Second));

        //test case 5
        Member testcase5First = new Member();
        Member testcase5Second = new Member();
        testcase5First.setFname("April");
        testcase5First.setLname("March");
        testcase5Second.setFname("Mary");
        testcase5Second.setLname("Lindsey");
        System.out.println("Test case 5; expected output 1; test output " + testcase5First.compareTo(testcase5Second));

        //test case 6
        Member testcase6First = new Member();
        Member testcase6Second = new Member();
        testcase6First.setFname("Duke");
        testcase6First.setLname("Ellington");
        testcase6Second.setFname("Roy");
        testcase6Second.setLname("Brooks");
        System.out.println("Test case 6; expected output 1; test output " + testcase6First.compareTo(testcase6Second));

        //test case 7
        Member testcase7First = new Member();
        Member testcase7Second = new Member();
        testcase7First.setFname("Kate");
        testcase7First.setLname("Lindsey");
        testcase7Second.setFname("Carl");
        testcase7Second.setLname("Brown");
        System.out.println("Test case 7; expected output 1; test output " + testcase7First.compareTo(testcase7Second));

        //test case 8
        Member testcase8First = new Member();
        Member testcase8Second = new Member();
        testcase8First.setFname("Paul");
        testcase8First.setLname("Siegel");
        testcase8Second.setFname("Bill");
        testcase8Second.setLname("Scanlan");
        System.out.println("Test case 8; expected output 1; test output " + testcase8First.compareTo(testcase8Second));

        //test case 9
        Member testcase9First = new Member();
        Member testcase9Second = new Member();
        testcase9First.setFname("John");
        testcase9First.setLname("Doe");
        testcase9Second.setFname("John");
        testcase9Second.setLname("Doe");
        System.out.println("Test case 9; expected output 0; test output " + testcase9First.compareTo(testcase9Second));
    }
}