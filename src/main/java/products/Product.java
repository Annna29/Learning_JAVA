package products;

import util.*;


public class Product {
    private Category category;
    private int id;
    private String name;
    private String description;
    private int price;
    private int discount;
    private int countPopularity;
    private int priceWithDiscount;

    public Product() {
        priceWithDiscount = price -(price*discount/100);
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

    public void populateProduct() {

        boolean isOptionValid =false;

        System.out.println("Enter the product's name: ");
        name= Validation.readAString(ValidationType.DETAILS);

        System.out.println("Enter the description: ");
        description = Validation.readAString(ValidationType.DETAILS);

        System.out.println("Enter the price (mandatory to be bigger than 0) : ");
        price = Validation.readANumber(ValidationType.NUMBER);


        System.out.println("Enter the discount ( choose between 0-100):");
        discount = Validation.readANumber(ValidationType.DISCOUNT);


        System.out.println("SELECT the category for this product : \n1. Animal Food\n2. Clothes\n3. Dermatocosmetics\n4. Food and Drinks \n5. Electronics ");

       do {
           int opt = OnlineShopMenu.readMenuOption();

            switch (opt) {
                case 1:
                    this.setCategory(Category.ANIMALE);
                    isOptionValid=true;
                    break;
                case 2:
                    this.setCategory(Category.VESTIMENTATIE);
                    isOptionValid=true;
                    break;
                case 3:
                    this.setCategory(Category.COSMETICE);
                    isOptionValid=true;
                    break;
                case 4:
                    this.setCategory(Category.ALIMENTE);
                    isOptionValid=true;
                    break;
                case 5:
                    this.setCategory(Category.ELECTRONICE);
                    isOptionValid=true;
                    break;
                default:
                    System.out.println(Constants.MESSAGE_INVALID_OPTION_1);
                    isOptionValid=false;
                    break;
            }
        }
        while (!isOptionValid);

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
}
