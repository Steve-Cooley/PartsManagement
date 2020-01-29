package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * will hold both static lists
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    //Fields to hold ID numbers.
    private static int partID = 90_000;
    private static int productID = 80_000;

    public static int genPartID() {
        return ++partID;
    }

    public static int genProductID() {
        return ++productID;
    }

    public static void modPart(Part modPart){
        System.out.println("modPart is executing");
        System.out.println("Object is type: " + modPart.getClass());
        for (int i = 0; i < allParts.size(); ++i) {
            if (allParts.get(i).getID() == modPart.getID()) {
                allParts.remove(allParts.get(i));
                allParts.add(modPart);
            }
        }
        if (modPart instanceof InHouse) {
            System.out.println("mac ID: " + ((InHouse) modPart).getMachineID());
        } else if (modPart instanceof Outsourced) {
            System.out.println("company name: " + ((Outsourced) modPart).getCompanyName());
        }
    }

    /**
     * Fixme I think this needs to generate the ID number
     * @param newPart
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Traverse the list searching for matches to the partID? fixme
     * @param partID
     * @return
     */
    public static Part lookUpPart(int partID) {
        for(int i = 0; i < allParts.size(); ++i){
            if(allParts.get(i).getID() == partID) {
                return allParts.get(i);
            }
        }

        //default return
        return null;  //fixme  Is this right?
    }

    /**
     *
     * @param productID
     * @return
     */
    public static Product lookUpProduct(int productID) {
        for(int i = 0; i < allProducts.size(); ++i){
            if(allProducts.get(i).getId() == productID) {
                return allProducts.get(i);
            }
        }

        return null;
    }

    /**
     * This is pretty much a dummy method.  The UML diagram requires that I include this method, but I don't
     * think that it makes any sense to the project to return a whole ObservableList when my other lookUP method return
     * Parts.  My CI said that I should
     * go ahead and follow the directions even if they don't make sense. Maybe it will make sense later.  fixme
     * **figured it out.
     * @param partName
     * @return ObservableList
     */
    public static ObservableList<Part> lookUpPart(String partName){
        ObservableList<Part> outp = FXCollections.observableArrayList();
        for(int i=0; i < allParts.size(); ++i) {
            if (allParts.get(i).getName().contains(partName)) {
                outp.add(allParts.get(i));
            }
        }

        return outp;
    }

    /**
     * Edit:  OK, I've figured out what this method is for.  It's for use with a search feature
     * and it does actually make sense that it returns a list.  Basically it looks for a match
     * of a set of characters in the name of parts, and returns a list of all matches.
     * Pretty cool actually.
     * @param productName
     * @return
     */
    public static ObservableList<Product> lookUpProduct(String productName) {
        ObservableList outp = FXCollections.observableArrayList();
        for (int i=0; i < allProducts.size(); ++i) {
            if (allProducts.get(i).getName().contains(productName)) {
                outp.add(allProducts.get(i));
            }
        }
        return outp;
    }

    /**
     * I think this method is supposed to delete the part at index, and replace it with selectedPart fixme
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.remove(index);
        allParts.add(index, selectedPart);
    }

    /**
     *   fixme
     * @param index
     * @param selectedProduct
     */
    public static void updateProduct(int index, Product selectedProduct){
        //
        allProducts.remove(index);
        allProducts.add(index,selectedProduct);
    }

    /**
     * Traverses list looking for matches.  If match found, deletes that object.
     * @param selectedPart
     * @return
     */
    public static boolean deletePart(Part selectedPart) {
        int len = allParts.size();
        for (int i=0; i<len; ++i) {
            if (allParts.get(i) == selectedPart) {
                allParts.remove(i);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        System.out.println("deleting product");
        int len = allProducts.size();
        for (int i=0; i<len; ++i) {
            if (allProducts.get(i) == selectedProduct) {
                allProducts.remove(i);
                return true;
            }
        }
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * could copy paste some stuff from testerMethod() later.
     */
    public static void loadSampleData(){ //todo improve this test data
        //add InHouse objects
        addPart(new InHouse(genPartID(), "ih0", 2.50, 14, 3, 20, 1234 ));
        addPart(new InHouse(genPartID(), "ih1", 2.50, 14, 3, 20, 1234 ));
        addPart(new InHouse(genPartID(), "ih2", 2.50, 14, 3, 20, 1234 ));
        addPart(new InHouse(genPartID(), "ih3", 2.50, 14, 3, 20, 1234 ));
        addPart(new InHouse(genPartID(), "ih4", 2.50, 14, 3, 20, 1234 ));
        //add OutSourced objects
        addPart(new Outsourced(genPartID(), "OS0", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(genPartID(), "OS1", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(genPartID(), "OS2", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(genPartID(), "OS3", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(genPartID(), "OS4", 2.13, 12, 1, 200, "company1"));
        //add Products
        addProduct(new Product(genProductID(), "prod0", 2.99, 100, 14, 201) );
        addProduct(new Product(genProductID(), "prod1", 2.99, 100, 14, 201) );
        addProduct(new Product(genProductID(), "prod2", 2.99, 100, 14, 201) );
        addProduct(new Product(genProductID(), "prod3", 2.99, 100, 14, 201) );
        addProduct(new Product(genProductID(), "prod4", 2.99, 100, 14, 201) );
    }



}

