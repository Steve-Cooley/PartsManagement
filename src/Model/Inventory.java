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

    public static void modProduct(Product modProd) {
        for (int i = 0; i < allProducts.size(); ++i) {
            if (allProducts.get(i).getID() == modProd.getID()) {
                allProducts.remove(allProducts.get(i));
                allProducts.add(modProd);
            }
        }
    }

    public static void modPart(Part modPart) {
        for (int i = 0; i < allParts.size(); ++i) {
            if (allParts.get(i).getID() == modPart.getID()) {
                allParts.remove(allParts.get(i));
                allParts.add(modPart);
            }
        }
    }

    /**
     * @param newPart
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Traverse the list searching for matches to the partID
     * not used, but meets UML requirements.  I like my method better.
     *
     * @param partID
     * @return
     */
    public static Part lookUpPart(int partID) {
        for (int i = 0; i < allParts.size(); ++i) {
            if (allParts.get(i).getID() == partID) {
                return allParts.get(i);
            }
        }
        //nonsense return, never executes
        return new InHouse(0, "SomethingWentWrong", 1, 1, 1, 1, 1);
    }

    /**
     * not used, but does meet requirements
     *
     * @param productID
     * @return
     */
    public static Product lookUpProduct(int productID) {
        for (int i = 0; i < allProducts.size(); ++i) {
            if (allProducts.get(i).getID() == productID) {
                return allProducts.get(i);
            }
        }

        //nonsense return, never executes, meets requirements
        return new Product(0, "SomethingWentWrong", 1, 1, 1, 1);
    }

    /**
     * Meets UML requirements, never used.
     *
     * @param partName
     * @return ObservableList
     */
    public static ObservableList<Part> lookUpPart(String partName) {
        ObservableList<Part> outp = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); ++i) {
            if (allParts.get(i).getName().contains(partName)) {
                outp.add(allParts.get(i));
            }
        }
        return outp;
    }

    /**
     * Never used, but meets UML requirements
     *
     * @param productName
     * @return
     */
    public static ObservableList<Product> lookUpProduct(String productName) {
        ObservableList outp = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); ++i) {
            if (allProducts.get(i).getName().contains(productName)) {
                outp.add(allProducts.get(i));
            }
        }
        return outp;
    }

    /**
     * Never called but meets UML requirements
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.remove(index);
        allParts.add(index, selectedPart);
    }

    /**
     * never called, but meets UML requirements
     *
     * @param index
     * @param selectedProduct
     */
    public static void updateProduct(int index, Product selectedProduct) {
        //
        allProducts.remove(index);
        allProducts.add(index, selectedProduct);
    }

    /**
     * Traverses list looking for matches.  If match found, deletes that object.
     *
     * @param selectedPart
     * @return
     */
    public static boolean deletePart(Part selectedPart) {
        int len = allParts.size();
        for (int i = 0; i < len; ++i) {
            if (allParts.get(i) == selectedPart) {
                allParts.remove(i);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        int len = allProducts.size();
        for (int i = 0; i < len; ++i) {
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
     *
     */
    public static void loadSampleData() {
        //add InHouse objects
        addPart(new InHouse(genPartID(), "ih0", 2.50, 14, 3, 20, 1234));
        addPart(new InHouse(genPartID(), "ih1", 2.50, 14, 3, 20, 1234));
        addPart(new InHouse(genPartID(), "ih2", 2.50, 14, 3, 20, 1234));
        addPart(new InHouse(genPartID(), "ih3", 2.50, 14, 3, 20, 1234));
        addPart(new InHouse(genPartID(), "ih4", 2.50, 14, 3, 20, 1234));
        //add OutSourced objects
        addPart(new Outsourced(genPartID(), "OS0", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(genPartID(), "OS1", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(genPartID(), "OS2", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(genPartID(), "OS3", 2.13, 12, 1, 200, "company1"));
        addPart(new Outsourced(genPartID(), "OS4", 2.13, 12, 1, 200, "company1"));
        //add Products
        addProduct(new Product(genProductID(), "prod0", 2.99, 100, 14, 201));
        addProduct(new Product(genProductID(), "prod1", 2.99, 100, 14, 201));
        addProduct(new Product(genProductID(), "prod2", 2.99, 100, 14, 201));
        addProduct(new Product(genProductID(), "prod3", 2.99, 100, 14, 201));
        addProduct(new Product(genProductID(), "prod4", 2.99, 100, 14, 201));
    }
}

