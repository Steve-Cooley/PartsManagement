package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class InHouse extends Part {

    private SimpleIntegerProperty machineID;

    public InHouse(IntegerProperty id, SimpleStringProperty name, DoubleProperty price, IntegerProperty stock,
                   IntegerProperty min, IntegerProperty max, SimpleIntegerProperty machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID.get();
    }

    public SimpleIntegerProperty machineIDProperty() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
}
