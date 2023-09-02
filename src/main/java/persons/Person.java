package persons;

import products.Product;
import util.*;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Person {
    private int id;
    private String secondName;
    private String firstName;
    private String dateOfBirth;
    private String address;
    private String username;
    //private String password;
    private byte[] password;

    public Person() {
    }

    public Person(String username, byte[] password) {
        this.username = username;
        this.password = password;
    }

    public Person(String secondName, String firstName, String dateOfBirth, String address) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }


    public void setAccountDetails() {

        System.out.println("PLease complete some personal data... ");

        System.out.println("---> firstname: ");
        firstName = Validation.readAString(ValidationType.DETAILS);

        System.out.println("---> secondname: ");
        secondName = Validation.readAString(ValidationType.DETAILS);

        System.out.println("---> address : ");
        address = Validation.readAString(ValidationType.DETAILS);

        System.out.println("---> date of birth : ");
        dateOfBirth = Validation.readAString(ValidationType.DETAILS);

    }


    public void filterAllProductsByText(String text, ArrayList<Product> listOfProduct) {
        boolean ok = false;

        for (Product p : listOfProduct) {
            if ( p.getName().toLowerCase().contains(text) || p.getName().toUpperCase().contains(text) )  {
                System.out.println("Name: " + p.getName() + " description: " + p.getDescription() + " price: " + p.getPrice());
                ok = true;
            }
        }
        if (!ok) {
            System.out.println("There are no products in the list...");
        }
        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();

    }

    public void filterOneCategoryProductsByText(String text, Category category, ArrayList<Product> listOfProduct) {
        boolean ok = false;
        for (Product p : listOfProduct) {
            if (p.getCategory().equals(category)) {
                if ( p.getName().toLowerCase().contains(text) || p.getName().toUpperCase().contains(text) ) {
                    System.out.println("Name: " + p.getName() + " description: " + p.getDescription() + " price: " + p.getPrice());
                    ok = true;
                }
            }
        }

        if (!ok) {
            System.out.println("There are no products in the list...");
        }

        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();

    }

    public void comparingAllProductsByPriceAscending(ArrayList<Product> listOfProduct){
        Comparator<Product> comparatorByPrice = Comparator.comparing(Product::getPrice);
          listOfProduct.sort(comparatorByPrice);
        for (Product p :listOfProduct) {
            System.out.println(p);
        }
        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();


    }
    public void comparingAllProductsByPriceDescending(ArrayList<Product> listOfProduct){
        Comparator<Product> comparatorByPrice = Comparator.comparing(Product::getPrice).reversed();
         listOfProduct.sort(comparatorByPrice);
        for (Product p :listOfProduct) {
            System.out.println(p);
        }

        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
    }

   public void comparingOneCategoryProductsByPriceAscending(int opt, ArrayList<Product> listOfProduct){
        ArrayList<Product> newCategoryList = new ArrayList<>();
        Category x = null ;
           switch (opt){
               case 1: x = Category.ANIMALE;
                       break;
               case 2: x = Category.VESTIMENTATIE;
                       break;
               case 3: x = Category.COSMETICE;
                      break;
               case 4: x = Category.ALIMENTE;
                      break;
               case 5,6: x = Category.ELECTRONICE;
                      break;
       }

       for (Product p: listOfProduct ) {
           if(p.getCategory().equals(x))
               newCategoryList.add(p);
       }

       Comparator<Product> comparatorByPrice = Comparator.comparing(Product::getPrice);
       newCategoryList.sort(comparatorByPrice);
       for (Product p: newCategoryList ) {
           System.out.println(p);

       }
       System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
       Scanner sc = new Scanner(System.in);
       String xy = sc.nextLine();

   }

    public void comparingOneCategoryProductsByPriceDescending( int opt, ArrayList<Product> listOfProduct){
        ArrayList<Product> newCategoryList = new ArrayList<>();
        Category x = null ;
        switch (opt){
            case 1: x = Category.ANIMALE;
                break;
            case 2: x = Category.VESTIMENTATIE;
                break;
            case 3: x = Category.COSMETICE;
                break;
            case 4: x = Category.ALIMENTE;
                break;
            case 5,6: x = Category.ELECTRONICE;
                break;

        }
        for (Product p: listOfProduct ) {
            if(p.getCategory().equals(x))
                newCategoryList.add(p);

        }

        Comparator<Product> comparatorByPrice = Comparator.comparing(Product::getPrice).reversed();
        newCategoryList.sort(comparatorByPrice);
        for (Product p: newCategoryList ) {
            System.out.println(p);

        }
        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String xy = sc.nextLine();
    }

    public void comparingAllProductsByDiscountPriceAscending(ArrayList<Product> listOfProduct){
        Comparator<Product> comparatorByPriceWithDiscount = Comparator.comparing(Product::getPriceWithDiscount);
        listOfProduct.sort(comparatorByPriceWithDiscount);
        for (Product p :listOfProduct) {
            System.out.println(p);
        }
        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();


    }
    public void comparingAllProductsByDiscountPriceDescending(ArrayList<Product> listOfProduct){
        Comparator<Product> comparatorByPriceWithDiscount = Comparator.comparing(Product::getPriceWithDiscount).reversed();
        listOfProduct.sort(comparatorByPriceWithDiscount);
        for (Product p :listOfProduct) {
            System.out.println(p);
        }

        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
    }

    public void comparingOneCategoryProductsByDiscountPriceAscending(int opt, ArrayList<Product> listOfProduct){
        ArrayList<Product> newCategoryList = new ArrayList<>();
        Category x = null ;
        switch (opt){
            case 1: x = Category.ANIMALE;
                break;
            case 2: x = Category.VESTIMENTATIE;
                break;
            case 3: x = Category.COSMETICE;
                break;
            case 4: x = Category.ALIMENTE;
                break;
            case 5,6: x = Category.ELECTRONICE;
                break;
        }

        for (Product p: listOfProduct ) {
            if(p.getCategory().equals(x))
                newCategoryList.add(p);
        }

        Comparator<Product> comparatorByPriceWithDiscount = Comparator.comparing(Product::getPriceWithDiscount);
        newCategoryList.sort(comparatorByPriceWithDiscount);
        for (Product p: newCategoryList ) {
            System.out.println(p);

        }
        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String xy = sc.nextLine();

    }

    public void comparingOneCategoryProductsByDiscountPriceDescending( int opt, ArrayList<Product> listOfProduct){
        ArrayList<Product> newCategoryList = new ArrayList<>();
        Category x = null ;
        switch (opt){
            case 1: x = Category.ANIMALE;
                break;
            case 2: x = Category.VESTIMENTATIE;
                break;
            case 3: x = Category.COSMETICE;
                break;
            case 4: x = Category.ALIMENTE;
                break;
            case 5,6: x = Category.ELECTRONICE;
                break;

        }
        for (Product p: listOfProduct ) {
            if(p.getCategory().equals(x))
                newCategoryList.add(p);

        }

        Comparator<Product> comparatorByPriceWithDiscount = Comparator.comparing(Product::getPriceWithDiscount).reversed();
        newCategoryList.sort(comparatorByPriceWithDiscount);
        for (Product p: newCategoryList ) {
            System.out.println(p);

        }
        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String xy = sc.nextLine();
    }



    public void comparingAllProductsByPopularityAscending(ArrayList<Product> listOfProduct){

        Comparator<Product> comparatorByPopularity = Comparator.comparing(Product::getCountPopularity);
        listOfProduct.sort(comparatorByPopularity);
        for (Product p :listOfProduct) {
            System.out.println(p);
        }
        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
    }

    public void comparingAllProductsByPopularityDescending(ArrayList<Product> listOfProduct){

        Comparator<Product> comparatorByPrice = Comparator.comparing(Product::getCountPopularity).reversed();
        listOfProduct.sort(comparatorByPrice);
        for (Product p :listOfProduct) {
            System.out.println(p);
        }

        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
    }

    public void comparingOneCategoryProductsByPopularityAscending(int opt, ArrayList<Product> listOfProduct){
        ArrayList<Product> newCategoryList = new ArrayList<>();
        Category x = null ;
        switch (opt){
            case 1: x = Category.ANIMALE;
                break;
            case 2: x = Category.VESTIMENTATIE;
                break;
            case 3: x = Category.COSMETICE;
                break;
            case 4: x = Category.ALIMENTE;
                break;
            case 5,6: x = Category.ELECTRONICE;
                break;
        }
        for (Product p: listOfProduct ) {
            if(p.getCategory().equals(x))
                newCategoryList.add(p);
        }

        Comparator<Product> comparatorByPopularity = Comparator.comparing(Product::getCountPopularity);
        newCategoryList.sort(comparatorByPopularity);
        for (Product p: newCategoryList ) {
            System.out.println(p);
        }

        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String xy = sc.nextLine();
    }

    public void comparingOneCategoryProductsByPopularityDescending( int opt, ArrayList<Product> listOfProduct){
        ArrayList<Product> newCategoryList = new ArrayList<>();
        Category x = null ;
        switch (opt){
            case 1: x = Category.ANIMALE;
                break;
            case 2: x = Category.VESTIMENTATIE;
                break;
            case 3: x = Category.COSMETICE;
                break;
            case 4: x = Category.ALIMENTE;
                break;
            case 5,6: x = Category.ELECTRONICE;
                break;
        }
        for (Product p: listOfProduct ) {
            if(p.getCategory().equals(x))
                newCategoryList.add(p);
        }

        Comparator<Product> comparatorByPopularity = Comparator.comparing(Product::getCountPopularity).reversed();
        newCategoryList.sort(comparatorByPopularity);
        for (Product p: newCategoryList ) {
            System.out.println(p);

        }
        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String xy = sc.nextLine();
    }

    public void comparingAllProductsByNameAscending(ArrayList<Product> listOfProduct){

        Comparator<Product> comparatorByName = Comparator.comparing(Product::getName);
        listOfProduct.sort(comparatorByName);
        for (Product p :listOfProduct) {
            System.out.println(p);
        }

        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
    }

    public void comparingAllProductsByNameDescending(ArrayList<Product> listOfProduct){

        Comparator<Product> comparatorByName = Comparator.comparing(Product::getName).reversed();
        listOfProduct.sort(comparatorByName);
        for (Product p :listOfProduct) {
            System.out.println(p);
        }

        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
    }

    public void comparingOneCategoryProductsByNameAscending(int opt, ArrayList<Product> listOfProduct){
        ArrayList<Product> newCategoryList = new ArrayList<>();
        Category x = null ;
        switch (opt){
            case 1: x = Category.ANIMALE;
                break;
            case 2: x = Category.VESTIMENTATIE;
                break;
            case 3: x = Category.COSMETICE;
                break;
            case 4: x = Category.ALIMENTE;
                break;
            case 5,6: x = Category.ELECTRONICE;
                break;
        }
        for (Product p: listOfProduct ) {
            if(p.getCategory().equals(x))
                newCategoryList.add(p);
        }

        Comparator<Product> comparatorByPopularity = Comparator.comparing(Product::getName);
        newCategoryList.sort(comparatorByPopularity);
        for (Product p: newCategoryList ) {
            System.out.println(p);
        }

        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String xy = sc.nextLine();
    }

    public void comparingOneCategoryProductsByNameDescending( int opt, ArrayList<Product> listOfProduct){
        ArrayList<Product> newCategoryList = new ArrayList<>();
        Category x = null ;
        switch (opt){
            case 1: x = Category.ANIMALE;
                break;
            case 2: x = Category.VESTIMENTATIE;
                break;
            case 3: x = Category.COSMETICE;
                break;
            case 4: x = Category.ALIMENTE;
                break;
            case 5,6: x = Category.ELECTRONICE;
                break;
        }
        for (Product p: listOfProduct ) {
            if(p.getCategory().equals(x))
                newCategoryList.add(p);
        }

        Comparator<Product> comparatorByName = Comparator.comparing(Product::getName).reversed();
        newCategoryList.sort(comparatorByName);
        for (Product p: newCategoryList ) {
            System.out.println(p);
        }

        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        Scanner sc = new Scanner(System.in);
        String xy = sc.nextLine();

    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
