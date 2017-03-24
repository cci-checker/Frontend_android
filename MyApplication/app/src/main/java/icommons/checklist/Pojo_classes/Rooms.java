
package icommons.checklist.Pojo_classes;

import java.util.List;

public class Rooms {


    /**
     * id : 6
     * roomID : 2
     * building_name : Rush
     * floor_number : 0
     * room_number : 14
     * opening_list : [1,2]
     * closing_list : [1,2]
     */

    private int id;
    private int roomID;
    private String building_name;
    private int floor_number;
    private int room_number;
    private List<Integer> opening_list;
    private List<Integer> closing_list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public List<Integer> getOpening_list() {
        return opening_list;
    }

    public void setOpening_list(List<Integer> opening_list) {
        this.opening_list = opening_list;
    }

    public List<Integer> getClosing_list() {
        return closing_list;
    }

    public void setClosing_list(List<Integer> closing_list) {
        this.closing_list = closing_list;
    }
}
