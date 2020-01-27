package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * will hold both static lists
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

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
        addPart(new InHouse(10, "ih0", 2.50, 14, 3, 20, 1234 ));
        addPart(new InHouse(10, "ih1", 2.50, 14, 3, 20, 1234 ));
        addPart(new InHouse(10, "ih2", 2.50, 14, 3, 20, 1234 ));
        addPart(new InHouse(10, "ih3", 2.50, 14, 3, 20, 1234 ));
        addPart(new InHouse(10, "ih4", 2.50, 14, 3, 20, 1234 ));
        //add OutSourced objects
        addPart(new Outsourced(11, "OS0", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(11, "OS1", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(11, "OS2", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(11, "OS3", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(11, "OS4", 2.13, 12, 1, 200, "company1"));
        //add Products
        addProduct(new Product(138, "prod0", 2.99, 100, 14, 201) );
        addProduct(new Product(138, "prod1", 2.99, 100, 14, 201) );
        addProduct(new Product(138, "prod2", 2.99, 100, 14, 201) );
        addProduct(new Product(138, "prod3", 2.99, 100, 14, 201) );
        addProduct(new Product(138, "prod4", 2.99, 100, 14, 201) );
    }

    /**
     * This should probably be removed before submission
     */
    public static void testerMethod() {
        System.out.println("Starting method!");
        System.out.println("Number of parts: " + allParts.size());
        System.out.println("Number of products: " + allProducts.size());
        addPart(new InHouse(10, "inhouse a1", 2.50, 14, 3, 20, 1234 ));
        System.out.println("Attempted to add a part. new parts quantity: " + allParts.size());
        addPart(new Outsourced(11, "outsourced b1", 2.13, 12, 1, 200, "company1"));
        System.out.println("Number of products: " + allProducts.size());
        System.out.println("added outsourced: " + allParts.size());
        System.out.println(allParts.toString());
        System.out.println( "testing lookUpPart(): " + lookUpPart(10));
        updatePart(0, new Outsourced(838, "OSb2", 19.99, 13, 1, 500,
                "company2"));
        System.out.println("updated part0: " + allParts.toString());
        deletePart(allParts.get(0));
        System.out.println("Deleted part at index0: " + allParts.toString());

        addProduct(new Product(138, "prodTaco", 2.99, 100, 14, 201) );

    }


}

