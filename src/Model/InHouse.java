package Model;

import javafx.beans.property.SimpleIntegerProperty;

public class InHouse extends Part {

    private SimpleIntegerProperty machineID;

    /**
     * @param id        id number
     * @param name      Name of part
     * @param price     price of part
     * @param stock     quantity on hand
     * @param min       minimum quantity
     * @param max       max that can be stored
     * @param machineID not sure
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = new SimpleIntegerProperty(machineID);
    }

    /**
     * @return
     */
    public int getMachineID() {
        return machineID.get();
    }

    /**
     * @return
     */
    public SimpleIntegerProperty machineIDProperty() {
        return machineID;
    }

    /**
     * @param machineID
     */
    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
}
