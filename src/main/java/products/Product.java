package products;

import jakarta.persistence.*;
import util.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product {
    private Category category;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int price;
    private int discount;
    private int countPopularity;
    private int priceWithDiscount;

    public Product() {
        setCategory(Category.UNKNOWN);
        priceWithDiscount = price -( (price * discount) /100);
    }

    public Product(Category category, int id, String name, String description, int price, int discount, int countPopularity) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.countPopularity = countPopularity;
        priceWithDiscount = price-(price*discount/100);
    }
    public Product(Category category, String name, String description, int price, int discount, int countPopularity) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.countPopularity = countPopularity;
        priceWithDiscount = price-(price*discount/100);
    }

    public void populateProduct() {


        System.out.println("Enter the product's name: ");
        name= Validation.readAString(ValidationType.DETAILS);

        System.out.println("Enter the description: ");
        description = Validation.readAString(ValidationType.DETAILS);

        System.out.println("Enter the price (mandatory to be bigger than 0) : ");
        price = Validation.readANumber(ValidationType.NUMBER);


        System.out.println("Enter the discount ( choose between 0-100):");
        discount = Validation.readANumber(ValidationType.DISCOUNT);

        priceWithDiscount=price-(price*discount/100);


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCountPopularity() {
        return countPopularity;
    }

    public void setCountPopularity(int countPopularity) {
        this.countPopularity = countPopularity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(int priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "category=" + category +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", countPopularity=" + countPopularity +
                ", priceWithDiscount=" + priceWithDiscount +
                '}';
    }

    public String printTableHeader(){
        String textTable;
        textTable =             "_____________________________________________________________________________________________\n";
        textTable = textTable + "|   category   | id |   name   |     description     |  price  | discount | countPopularity |";

        return textTable;
    }

    public String printTalbeElm(){
        String textTable;
        textTable =             "|___________________________________________________________________________________________|\n";
        textTable = textTable + "|" +   category   + "|" +  id + "|" +  name  + "|" + description + "|" + price + "|" + discount +"|"+ countPopularity + "|";

        return textTable;
    }

    // Table Column Dimension
    @Transient
    HashMap<String, Integer> tableColDimHM = new HashMap<>(Map.of("catLen", 17,"idLen",
            8,"nameLen", 30,"descrLen", 40,"priceLen", 10,"discLen",
            12,"countPopLen",15,"price%Len",10));



    // Print a data Row
    public String printTableRow(String inStr, int colLen) {

        StringBuilder strBuilder = new StringBuilder();
        int insertSpaces = colLen - (2 + inStr.length() + 1);

        strBuilder.append("| ");
        if( (inStr.length()+4) > colLen ){
            strBuilder.append(inStr.substring(0,(colLen-3)));
        } else {
            strBuilder.append(inStr);
        }
        for(int i = 0; i<insertSpaces; i++) {
            strBuilder.append(" ");
        }
        strBuilder.append("|");

        return strBuilder.toString();
    }

    // Print the Table Header
    public String printTableHeader2(){

        StringBuilder strBuilder = new StringBuilder();


        int sumColDim = 0;

        // Sum all values in tableColDim
        for(int val : tableColDimHM.values()){
            sumColDim += val;
        }

        for(int i = 0; i<sumColDim; i++) {
            strBuilder.append("-");
        }

        strBuilder.append("\n"); // <- tell the string builder that we want a new line

        strBuilder.append(printTableRow("Category",        tableColDimHM.get("catLen"     )));
        strBuilder.append(printTableRow("ID",              tableColDimHM.get("idLen"      )));
        strBuilder.append(printTableRow("Name",            tableColDimHM.get("nameLen"    )));
        strBuilder.append(printTableRow("Description",     tableColDimHM.get("descrLen"   )));
        strBuilder.append(printTableRow("Price",           tableColDimHM.get("priceLen"   )));
        strBuilder.append(printTableRow("Discount",        tableColDimHM.get("discLen"    )));
        strBuilder.append(printTableRow("Price %",         tableColDimHM.get("price%Len"  )));
        strBuilder.append(printTableRow("countPopularity", tableColDimHM.get("countPopLen")));

        return strBuilder.toString();
    }

    public String printTableElm2(){

        try {
            StringBuilder strBuilder = new StringBuilder();
            int sumColDim = 0;

            // Sum all values in tableColDim
            for (int val : tableColDimHM.values()) {
                sumColDim += val;
            }

            for (int i = 0; i < sumColDim; i++) {
                strBuilder.append("-");
            }

            strBuilder.append("\n"); // <- tell the string builder that we want a new line

            strBuilder.append(printTableRow(getCategory().toString(), tableColDimHM.get("catLen")));
            strBuilder.append(printTableRow(String.valueOf(getId()), tableColDimHM.get("idLen")));
            strBuilder.append(printTableRow(getName(), tableColDimHM.get("nameLen")));
            strBuilder.append(printTableRow(getDescription(), tableColDimHM.get("descrLen")));
            strBuilder.append(printTableRow(String.valueOf(getPrice()), tableColDimHM.get("priceLen")));
            strBuilder.append(printTableRow(String.valueOf(getDiscount()), tableColDimHM.get("discLen")));
            strBuilder.append(printTableRow(String.valueOf(getPriceWithDiscount()),tableColDimHM.get("price%Len")));
            strBuilder.append(printTableRow(String.valueOf(getCountPopularity()), tableColDimHM.get("countPopLen")));

            return strBuilder.toString();
        } catch (Exception e) {
            System.out.println(e);
        }

        return "ERROR!!!";
    }


}
