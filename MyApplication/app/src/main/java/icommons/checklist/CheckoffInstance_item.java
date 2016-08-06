package icommons.checklist;

import java.util.Vector;

/**
 * Created by Tushar on 8/3/16.
 */
public class CheckoffInstance_item {
    private int ID_;
    private int StudentUID_;
    private int RoomID_;
    private String DateTimeStamp_;
    private Vector<Integer> itemIDs_;
    public CheckoffInstance_item(int ID_, int studentUID_, int roomID_, String dateTimeStamp_, Vector<Integer> itemIDs_) {
        this.ID_ = ID_;
        StudentUID_ = studentUID_;
        RoomID_ = roomID_;
        DateTimeStamp_ = dateTimeStamp_;
        this.itemIDs_ = itemIDs_;
    }

    public int getID_() {
        return ID_;
    }

    public void setID_(int ID_) {
        this.ID_ = ID_;
    }

    public int getStudentUID_() {
        return StudentUID_;
    }

    public void setStudentUID_(int studentUID_) {
        StudentUID_ = studentUID_;
    }

    public int getRoomID_() {
        return RoomID_;
    }

    public void setRoomID_(int roomID_) {
        RoomID_ = roomID_;
    }

    public String getDateTimeStamp_() {
        return DateTimeStamp_;
    }

    public void setDateTimeStamp_(String dateTimeStamp_) {
        DateTimeStamp_ = dateTimeStamp_;
    }

    public Vector<Integer> getItemIDs_() {
        return itemIDs_;
    }

    public void setItemIDs_(Vector<Integer> itemIDs_) {
        this.itemIDs_ = itemIDs_;
    }
}
