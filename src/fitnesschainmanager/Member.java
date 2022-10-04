package fitnesschainmanager;

/**
 * Member Class holds data used for Member objects and returns the data and information comparing the data.
 * The data stored is a Member's first name, last name, date of birth, membership expiration date, and location. All
 * information can be changed and retrieved with getter and setter methods. The validity of dates can be checked, and
 * names and locations can be compared for sorting purposes.
 * @author David Ma, Ethan Kwok
 */
public class Member implements Comparable<Member>{
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    /**
     * Creates a Member with no information.
     * That is, the Member's first and last names are blank, and their dob, expiration date, and location are all null.
     */
    public Member() {
        fname = "";
        lname = "";
        dob = null;
        expire = null;
        location = null;
    }

    /**
     * Creates a Member with information according to the parameters.
     * @param fname first name of the Member.
     * @param lname fast name of the Member.
     * @param dob Date object representing the date of birth of the Member.
     * @param expire Date object representing the membership expiration date of the Member.
     * @param location Location of the Member's gym.
     */
    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    /**
     * Sets the first name of the Member according to the parameter.
     * @param fname first name of the Member.
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * Sets the last name of the Member according to the parameter.
     * @param lname last name of the Member.
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * Sets the date of birth of the Member according to the parameter.
     * @param dob Date object representing the date of birth of the Member.
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Sets the expiration date of the Member's membership according to the parameter.
     * @param expire Date object representing the membership expiration date of the Member.
     */
    public void setExpire(Date expire) {
        this.expire = expire;
    }

    /**
     * Sets the location of the Member according to the parameter.
     * @param location Location of the Member's gym.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the first name of the Member.
     * @return first name of the Member.
     */
    public String getFname() {
        return fname;
    }

    /**
     * Gets the last name of the Member.
     * @return last name of the Member.
     */
    public String getLname() {
        return lname;
    }

    /**
     * Gets the date of birth of the Member.
     * @return Date object date of birth of the Member.
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Gets the location of the Member.
     * @return Location of the Member's gym.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the expiration date of the Member's membership.
     * @return Date object representing the expiration date of the Member's membership.
     */
    public Date getExpire() {
        return expire;
    }

    /**
     * Checks if the date of birth of a member is a valid calendar date using the isValid() method in Date class.
     * @return true if the date is a valid calendar date, false otherwise.
     */
    public boolean isDobValid() {
        return dob.isValid();
    }

    /**
     * Checks if the Member's membership expiration date is a valid calendar date using the isValid() method in
     * Date class.
     * @return true if the date is a valid calendar date, false otherwise.
     */
    public boolean isExpireValid() {
        return expire.isValid();
    }

    /**
     * Checks if the Member is 18 years of age or older.
     * Creates a new date set to the date when the program is run, and compares that date to the date of birth.
     * @return true if the Member is 18 or older, false if the Member is younger than 18 years.
     */
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

    /**
     * Checks if the Member's date of birth is a past date, today's date, or a future date.
     * Creates a new date set to the date when the program is run, and compares that date to the date of birth.
     * @return true if the date of birth is in the past, false if the date of birth is today or in the future.
     */
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

    /**
     * Checks if the location is valid.
     * The location existing is defined by whether it is in the enum class. That is, it must be one of the
     * five existing gyms.
     * @return true if the location exists, false if the location is null.
     */
    public boolean isValidLocation() {
        if(location == null) {
            return false;
        }
        return true;
    }

    /**
     * Converts a Member's information to a String.
     * The format matches the output given in the expected output page. If the Membership expiration date has past,
     * the output uses past tense "expired" instead of future tense "expires."
     * @return String representing the Member's information following the format:
     *         First Name Last Name, DOB: mm/dd/yy: Membership expires mm/dd/yy, Location: TOWN, zipcode, COUNTY
     */
    @Override
    public String toString() {
        String output = "";
        output += fname + " " + lname + ", DOB: " + dob.toString() + ", ";
        if (expire.compareTo(new Date()) < 0) output += "Membership expired ";
        else output += "Membership expires ";
        output += expire.toString() + ", Location: " + location.toString();
        return output;
    }

    /**
     * Checks if 2 Members are the same person or not.
     * The "same person" is defined by if they have the same first name, last name, AND date of birth.
     * @param obj The Member being compared to.
     * @return true if the Members are the same, false if not.
     */
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

    /**
     * Compares 2 Members' names alphabetically by last name then first name.
     * Used to sort Members by last name then first name.
     * @param member the object to be compared (as opposed to the current Member).
     * @return 1 if the current Member's name is lexicographically after the parameter Member's name. -1 if the current
     *         Member's name is lexicographically before the parameter Member's name. 0 if their names are the same.
     */
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

    /**
     * Compares 2 Members' names alphabetically by county and then, if the counties are the same, least to greatest
     * by zipcode.
     * Used to sort Members by location.
     * @param member the object to be compared (as opposed to the current Member).
     * @return 0 if their locations are the same. 1 if the current Member's county is lexicographically after the
     *         parameter Member's location or, when the county is the same, if the zipcode is greater. -1 otherwise.
     */
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

    /**
     * Testbed main method to test the compareTo() method.
     * @param args necessary, unused parameter in main method.
     */
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