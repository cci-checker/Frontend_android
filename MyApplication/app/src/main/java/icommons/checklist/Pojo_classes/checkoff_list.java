package icommons.checklist.Pojo_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tushar on 2/12/17.
 */
public class checkoff_list {

    /**
     * id : 3
     * checkoff_listID : 3
     * date_time_stamp : 2016-11-24T23:56:41Z
     * instance_name : post call
     * studentUID : 5
     * roomID : 6
     * issueReport : 1
     * task_list : [1,2]
     * task_completedlist : [1,2]
     */

    @SerializedName("date_time_stamp")
    @Expose
    private String date_time_stamp;
    @SerializedName("instance_name")
    @Expose
    private String instance_name;
    @SerializedName("studentUID")
    @Expose
    private int studentUID;
    @SerializedName("roomID")
    @Expose
    private int roomID;
    @SerializedName("issueReport")
    @Expose
    private int issueReport;
    @SerializedName("task_list")
    @Expose
    private List<Integer> task_list;
    @SerializedName("task_completedlist")
    @Expose
    private List<Integer> task_completedlist;



    public String getDate_time_stamp() {
        return date_time_stamp;
    }

    public void setDate_time_stamp(String date_time_stamp) {
        this.date_time_stamp = date_time_stamp;
    }

    public String getinstance_name() {
        return instance_name;
    }

    public void setinstance_name(String instance_name) {
        this.instance_name = instance_name;
    }

    public int getStudentUID() {
        return studentUID;
    }

    public void setStudentUID(int studentUID) {
        this.studentUID = studentUID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getIssueReport() {
        return issueReport;
    }

    public void setIssueReport(int issueReport) {
        this.issueReport = issueReport;
    }

    public List<Integer> getTask_list() {
        return task_list;
    }

    public void setTask_list(List<Integer> task_list) {
        this.task_list = task_list;
    }

    public List<Integer> getTask_completedlist() {
        return task_completedlist;
    }

    public void setTask_completedlist(List<Integer> task_completedlist) {
        this.task_completedlist = task_completedlist;
    }

    @Override
    public String toString() {
        return "{" +
                "\"date_time_stamp\":\"" + date_time_stamp + "\"" +
                ",\"instance_name\":\"" + instance_name + "\"" +
                ", \"studentUID\":" + studentUID +
                ", \"roomID\":" + roomID +
                ", \"issueReport\":" + issueReport +
                ", \"task_list\":" + task_list +
                ", \"task_completedlist\":" + task_completedlist +
                '}';
    }
}
