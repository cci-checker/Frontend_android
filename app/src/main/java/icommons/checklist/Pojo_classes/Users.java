package icommons.checklist.Pojo_classes;

/**
 * Created by Tushar on 2/4/17.
 */
public class Users {

    /**
     * id : 5
     * uniqueID : 1
     * studentID : 13736568
     * iCommonsSA_bool : true
     * lastname : Chawla
     * firstname : Tushar
     * rfid_number : 13532
     * Admin : false
     */

    private int id;
    private int uniqueID;
    private int studentID;
    private boolean iCommonsSA_bool;
    private String lastname;
    private String firstname;
    private int rfid_number;
    private boolean Admin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public boolean isICommonsSA_bool() {
        return iCommonsSA_bool;
    }

    public void setICommonsSA_bool(boolean iCommonsSA_bool) {
        this.iCommonsSA_bool = iCommonsSA_bool;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getRfid_number() {
        return rfid_number;
    }

    public void setRfid_number(int rfid_number) {
        this.rfid_number = rfid_number;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean Admin) {
        this.Admin = Admin;
    }
}
