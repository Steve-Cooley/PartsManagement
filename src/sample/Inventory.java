package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * will hold both static lists
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Traverse the list searching for matches to the partID? fixme
     * @param partID
     * @return
     */
    public Part lookUpPart(int partID) {
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
    public Product lookUpProduct(int productID) {
        for(int i = 0; i < allProducts.size(); ++i){
            if(allProducts.get(i).getID() == productID) {
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
     * @param partName
     * @return ObservableList
     */
    public ObservableList<Part> lookUpPart(String partName){
        //for(int i=0; i < allParts.size(); ++i) {
        //    if (allParts.get(i).getName() == partName) {
        //        return allParts.get(i);
        //    }
        //}

        return allParts;
    }

    /**
     * Another dummy method. I think I'll leave these methods in, even if I also have to write methods that will be
     * more useful. fixme
     * @param productName
     * @return
     */
    public ObservableList<Product> lookUpProduct(String productName) {
        return allProducts;
    }

    /**
     * I think this method is supposed to delete the part at index, and replace it with selectedPart fixme
     */
    public void updatePart(int index, Part selectedPart) {}

    /**
     *   fixme
     * @param index
     * @param selectedProduct
     */
    public void updateProduct(int index, Product selectedProduct){
        //
    }

    public boolean deletePart(Part selectedPart) {
        int len = allParts.size();
        for (int i=0; i<len; ++i) {
            if (allParts.get(i) == selectedPart) {
                allParts.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(Product selectedProduct) {
        int len = allProducts.size();
        for (int i=0; i<len; ++i) {
            if (allProducts.get(i) == selectedProduct) {
                allProducts.remove(i);
                return true;
            }
        }
        return false;
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }


}

