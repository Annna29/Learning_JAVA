package DB;

import Products.Product;

import java.util.ArrayList;

public class DB_PRODUCTS {

   private ArrayList<Product> listOfProduct;

    public DB_PRODUCTS() {
        listOfProduct = new ArrayList<>();

    }

    public ArrayList<Product> getListOfProduct() {
        return listOfProduct;
    }
}
