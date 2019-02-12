package icommons.checklist.Pojo_classes;

/**
 * Created by Tushar on 2/5/17.
 */
public class Checklist_items {

    /**
     * id : 1
     * itemID : 1
     * item_name : Windows
     * item_description : Make sure all the windows are closed
     * active_status : true
     */

    private int id;
    private int itemID;
    private String item_name;
    private String item_description;
    private boolean active_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public boolean isActive_status() {
        return active_status;
    }

    public void setActive_status(boolean active_status) {
        this.active_status = active_status;
    }
}
