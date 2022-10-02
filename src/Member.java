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

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getDob() {
        return dob;
    }

    public Date getExpire() { return expire; }

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
        String output = "";
        Calendar c = Calendar.getInstance();
        output += fname + " " + lname + ", DOB: " + dob.toString() + ", ";
        if (c.before(expire)) output += "Membership expired ";
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
               member.getDob().equals(this.dob)) {
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
}