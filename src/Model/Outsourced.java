package Model;

import Model.Part;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {

    private SimpleStringProperty companyName;

/*    //fixme probably redundant constructor
    public Outsourced(IntegerProperty id, SimpleStringProperty name, DoubleProperty price, IntegerProperty stock,
                      IntegerProperty min, IntegerProperty max, SimpleStringProperty companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }*/

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = new SimpleStringProperty(companyName);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
}
