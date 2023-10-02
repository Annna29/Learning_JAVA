package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OnlineShopMenu {

    // ----- MAIN MENU ----- //
    public static void printLoginMenu(){
        System.out.println("--------------------------------------");
        System.out.println(" Choose an option from the below ones:");
        System.out.println("--------------------------------------");
        System.out.println("  1. SIGN UP                         ");
        System.out.println("  2. LOG IN                          ");
        System.out.println("  9. EXIT SHOP                       ");
        System.out.println("--------------------------------------");

    }

    // ----- User MENU ----- //

    public static void printSecondMenuUser(){
        System.out.println("---------------------------------------------------");
        System.out.println(" Choose an option from the below ones: ");
        System.out.println("---------------------------------------------------");
        System.out.println("  1. SEARCH A PRODUCT");
        System.out.println("  2. DISPLAY PRODUCTS BY PRICE");
        System.out.println("  3. DISPLAY PRODUCTS BY POPULARITY");
        System.out.println("  4. DISPLAY PRODUCTS BY ALPHABETICAL ORDER");
        System.out.println("  5. ADD MONEY");
        System.out.println("  6. ADD PRODUCTS TO YOUR CART AND BUY");
        System.out.println("  7. DISPLAY PRODUCTS BY PRICE WITH DISCOUNT");
        System.out.println("  8. VIEW YOUR SOLD");
        System.out.println("  9. GO BACK TO MAIN MENU");
        System.out.println("---------------------------------------------------");

    }

    // 1. SEARCH A PRODUCT
    public static void printFilterProductsByTextMenu(){
        System.out.println("---------------------------------------------------");
        System.out.println(" Choose an option from the below ones: ");
        System.out.println("---------------------------------------------------");
        System.out.println("  1. FILTER ALL PRODUCTS BY TEXT");
        System.out.println("  2. FILTER A CATEGORY OF PRODUCTS BY TEXT");
        System.out.println("  3. RETURN TO PREVIOUS MENU ");
        System.out.println("---------------------------------------------------");

    }

    // 2. DISPLAY PRODUCTS BY PRICE
    public static void printDisplayProductByPriceMenu(){
        System.out.println("---------------------------------------------------");
        System.out.println(" Choose an option from the below ones: ");
        System.out.println("---------------------------------------------------");
        System.out.println("  1. DISPLAY ALL PRODUCTS BY PRICE (ascending order) ");
        System.out.println("  2. DISPLAY ALL PRODUCTS BY PRICE (descending order) ");
        System.out.println("  3. DISPLAY A CATEGORY OF PRODUCTS BY PRICE (ascending order)");
        System.out.println("  4. DISPLAY A CATEGORY OF PRODUCTS BY PRICE (descending order)");
        System.out.println("  5. RETURN TO PREVIOUS MENU ");
        System.out.println("---------------------------------------------------");

    }

    // 3. DISPLAY PRODUCTS BY POPULARITY
    public static void printDisplayProductByPopularityMenu(){
        System.out.println("---------------------------------------------------");
        System.out.println(" Choose an option from the below ones: ");
        System.out.println("---------------------------------------------------");
        System.out.println("  1. DISPLAY ALL PRODUCTS BY POPULARITY (ascending order) ");
        System.out.println("  2. DISPLAY ALL PRODUCTS BY POPULARITY (descending order) ");
        System.out.println("  3. DISPLAY A CATEGORY OF PRODUCTS BY POPULARITY (ascending order)");
        System.out.println("  4. DISPLAY A CATEGORY OF PRODUCTS BY POPULARITY (descending order)");
        System.out.println("  5. RETURN TO PREVIOUS MENU ");
        System.out.println("---------------------------------------------------");
    }

    // 4. DISPLAY PRODUCTS BY ALPHABETICAL ORDER
    public static void printDisplayProductByNameMenu(){
        System.out.println("---------------------------------------------------");
        System.out.println(" Choose an option from the below ones: ");
        System.out.println("---------------------------------------------------");
        System.out.println("  1. DISPLAY ALL PRODUCTS BY NAME (ascending order) ");
        System.out.println("  2. DISPLAY ALL PRODUCTS BY NAME (descending order) ");
        System.out.println("  3. DISPLAY A CATEGORY OF PRODUCTS BY NAME (ascending order)");
        System.out.println("  4. DISPLAY A CATEGORY OF PRODUCTS BY NAME (descending order)");
        System.out.println("  5. RETURN TO PREVIOUS MENU ");
        System.out.println("---------------------------------------------------");
    }


    // 7. DISPLAY PRODUCTS BY PRICE WITH DISCOUNT
    public static void printDisplayProductByDiscountPriceMenu(){
        System.out.println("---------------------------------------------------");
        System.out.println(" Choose an option from the below ones: ");
        System.out.println("---------------------------------------------------");
        System.out.println("  1. DISPLAY ALL PRODUCTS BY PRICE WITH DISCOUNT (ascending order) ");
        System.out.println("  2. DISPLAY ALL PRODUCTS BY PRICE WITH DISCOUNT(descending order) ");
        System.out.println("  3. DISPLAY A CATEGORY OF PRODUCTS BY PRICE WITH (ascending order)");
        System.out.println("  4. DISPLAY A CATEGORY OF PRODUCTS BY PRICE WITH (descending order)");
        System.out.println("  5. RETURN TO PREVIOUS MENU ");
        System.out.println("---------------------------------------------------");

    }

    // ----- Admin MENU ----- //
    public static void printSecondMenuAdmin(){
        System.out.println("---------------------------------------------------");
        System.out.println(" Choose an option from the below ones: ");
        System.out.println("---------------------------------------------------");
        System.out.println("  1. ADD PRODUCTS");
        System.out.println("  2. MODIFY THE PRICE OF PRODUCTS");
        System.out.println("  3. MODIFY  THE DESCRIPTION OF PRODUCTS");
        System.out.println("  4. REMOVE PRODUCTS");
        System.out.println("  5. SEE ALL PRODUCTS");
        System.out.println("  6. SEE ALL PERSONS");
        System.out.println("  7. REMOVE A PERSON");
        System.out.println("  8. ADD A NEW ADMIN");
        System.out.println("  9. MODIFY THE DISCOUNT OF PRODUCTS");
        System.out.println(" 10. GO BACK TO MAIN MENU");
        System.out.println("---------------------------------------------------");

    }

    public static int readMenuOption(){
        int option = -1;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                option = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("You did not enter a number!");
                scanner.nextLine();
            }
        }

        return option;
    }
}
