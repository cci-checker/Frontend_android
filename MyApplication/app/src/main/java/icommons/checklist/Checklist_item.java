package icommons.checklist;

import android.app.Activity;

/**
 * Created by Tushar on 7/18/16.
 */
public class Checklist_item extends Activity {
    private int Item_id;
    private String Item_name;
    private String Item_description;
    private boolean active;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getItem_description() {
        return Item_description;
    }

    public void setItem_description(String item_description) {
        Item_description = item_description;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public int getItem_id() {
        return Item_id;
    }

    public void setItem_id(int item_id) {
        Item_id = item_id;
    }

    private boolean completed;



}
