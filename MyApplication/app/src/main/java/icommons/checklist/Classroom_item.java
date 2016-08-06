package icommons.checklist;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Tushar on 7/11/16.
 */
public class Classroom_item {
    private int _RoomID;
    private String _Building;
    private int _Floor;
    private int _Room_number;
    private Vector<Integer> _Checklist_itemid;

    public int get_RoomID() {
        return _RoomID;
    }

    public void set_RoomID(int _RoomID) {
        this._RoomID = _RoomID;
    }

    public String get_Building() {
        return _Building;
    }

    public void set_Building(String _Building) {
        this._Building = _Building;
    }

    public int get_Floor() {
        return _Floor;
    }

    public void set_Floor(int _Floor) {
        this._Floor = _Floor;
    }

    public int get_Room_number() {
        return _Room_number;
    }

    public void set_Room_number(int _Room_number) {
        this._Room_number = _Room_number;
    }

    public Vector<Integer> get_Checklist_itemid() {
        return _Checklist_itemid;
    }

    public void set_Checklist_itemid(Vector<Integer> _Checklist_itemid) {
        this._Checklist_itemid = _Checklist_itemid;
    }




}
