package icommons.checklist;

/**
 * Created by Tushar on 8/2/16.
 */
public class User_item {
    private int uniqueID_;
    private int studentID_;
    private boolean iCommonsSA_;
    private String lastname_;
    private String firstname_;
    private int RFID_;

    public int getUniqueID_() {
        return uniqueID_;
    }

    public void setUniqueID_(int uniqueID_) {
        this.uniqueID_ = uniqueID_;
    }

    public int getStudentID_() {
        return studentID_;
    }

    public void setStudentID_(int studentID_) {
        this.studentID_ = studentID_;
    }

    public boolean isiCommonsSA_() {
        return iCommonsSA_;
    }

    public void setiCommonsSA_(boolean iCommonsSA_) {
        this.iCommonsSA_ = iCommonsSA_;
    }

    public String getLastname_() {
        return lastname_;
    }

    public void setLastname_(String lastname_) {
        this.lastname_ = lastname_;
    }

    public String getFirstname_() {
        return firstname_;
    }

    public void setFirstname_(String firstname_) {
        this.firstname_ = firstname_;
    }

    public int getRFID_() {
        return RFID_;
    }

    public void setRFID_(int RFID_) {
        this.RFID_ = RFID_;
    }

    public User_item(int uniqueID_, int studentID_, boolean iCommonsSA_, String lastname_, String firstname_, int RFID_) {

        this.uniqueID_ = uniqueID_;
        this.studentID_ = studentID_;
        this.iCommonsSA_ = iCommonsSA_;
        this.lastname_ = lastname_;
        this.firstname_ = firstname_;
        this.RFID_ = RFID_;
    }
}
