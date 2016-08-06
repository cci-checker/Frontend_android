package icommons.checklist;

import android.app.Activity;
import android.media.Image;

/**
 * Created by Tushar on 8/2/16.
 */
public class IssueReport_item extends Activity {
    private int issueID_;
    private int roomID_;
    String comment_;

    public IssueReport_item(int issueID_, int roomID_, String comment_, Image photo_, int studentID_, boolean completed_) {
        this.issueID_ = issueID_;
        this.roomID_ = roomID_;
        this.comment_ = comment_;
        Photo_ = photo_;
        StudentID_ = studentID_;
        this.completed_ = completed_;
    }

    public int getIssueID_() {
        return issueID_;
    }

    public void setIssueID_(int issueID_) {
        this.issueID_ = issueID_;
    }

    public int getRoomID_() {
        return roomID_;
    }

    public void setRoomID_(int roomID_) {
        this.roomID_ = roomID_;
    }

    public String getComment_() {
        return comment_;
    }

    public void setComment_(String comment_) {
        this.comment_ = comment_;
    }

    public Image getPhoto_() {
        return Photo_;
    }

    public void setPhoto_(Image photo_) {
        Photo_ = photo_;
    }

    public int getStudentID_() {
        return StudentID_;
    }

    public void setStudentID_(int studentID_) {
        StudentID_ = studentID_;
    }

    public boolean isCompleted_() {
        return completed_;
    }

    public void setCompleted_(boolean completed_) {
        this.completed_ = completed_;
    }

    Image Photo_;
    int StudentID_;
    boolean completed_;

}
