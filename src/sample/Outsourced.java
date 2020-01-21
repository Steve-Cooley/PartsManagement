package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Outsourced extends Part {

    private SimpleStringProperty companyName;

    public Outsourced(IntegerProperty id, SimpleStringProperty name, DoubleProperty price, IntegerProperty stock,
                      IntegerProperty min, IntegerProperty max, SimpleStringProperty companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    //    public Outsourced(int id, String name, double price, int stock, int min, int max) {  //I'm keeping all this for now bc I can't remember why I had two constructors. fixme
//        super(id, name, price, stock, min, max);
//    }
//
//    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
//        super(id, name, price, stock, min, max);
//        this.companyName = companyName;
//    }
}
