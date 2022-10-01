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

    public void setName(String fname, String lname) {
        this.fname = fname;
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

    public boolean isValid() {
        Date today = new Date();

        // Check if date is valid
        if(!dob.isValid() || !expire.isValid()) {
            return false;
        }
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

        // Check if member is 18 or over
        if(today.getYear() - dob.getYear() < 18) {
            return false;
        }
        if(today.getYear() - dob.getYear() == 18) {
            if(dob.getMonth() < today.getMonth()) {
                return false;
            }
            else if(dob.getMonth() == today.getMonth()) {
                if(dob.getDay() < today.getDay()) {
                    return false;
                }
            }
        }

        //Check if location is valid
        if(location == null) {
            return false;
        }

        return true;
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