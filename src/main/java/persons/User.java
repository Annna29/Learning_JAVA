package persons;
import products.Product;
import util.Constants;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Person {
    private int sold;
    private ArrayList<Product> shoppingBag;

    public User() {
        shoppingBag = new ArrayList<>();
    }

    public User(String username, byte[] password) {
        super(username, password);
        shoppingBag = new ArrayList<>();
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public ArrayList<Product> getShoppingBag() {
        return shoppingBag;
    }

    public void setShoppingBag(ArrayList<Product> shoppingBag) {
        this.shoppingBag = shoppingBag;
    }



   public void addMoney(){
        Scanner scanner= new Scanner(System.in);

        do {
            System.out.println("Please enter the sum of money you want to add to your account: ");
            int moneySum = 0;
            try {
                moneySum = scanner.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println(Constants.MESSAGE_ERROR_NUMBER_2);
                scanner.nextLine();
            }
            if(moneySum<=0){
                System.out.println("You have to add a bigger sum! ");

            }
            else {
                sold = sold + moneySum;
                System.out.println("You have " + sold + " credit !");
                break;
            }

        }
        while(true);
    }

    public void addToShoppingBag(String name,ArrayList<Product> arrayList){
      boolean  isAdding = false;
        Scanner sc = new Scanner(System.in);

        for (Product p:arrayList ) {
            if(p.getName().equalsIgnoreCase(name)) {
                shoppingBag.add(p);
                isAdding = true;
            }
        }

        if(isAdding) {
             System.out.println("The product was added to your cart!!!");
             System.out.println("Please press 1 if you want to buy the product and press 2 if you want to go to options menu");

             int opt = sc.nextInt();
             if(opt==1){
                buyProduct(name);}
             else if(opt==2) {
                 return;

             }
        }
        else {
            System.out.println("The product can not be added yo your cart !!!");
        }

        Scanner scc = new Scanner(System.in);
        System.out.println(Constants.MESSAGE_ENTER_TO_CONTINUE_14);
        String x = scc.nextLine();
    }

    public void buyProduct (String productName){

        boolean isInShoppingBag = false;
        Product currentProduct  = new Product();
        for (Product p:shoppingBag){
            if(p.getName().equalsIgnoreCase(productName)){
                currentProduct = p;
                isInShoppingBag= true;
            }

        }

        if(sold>=currentProduct.getPrice()&&isInShoppingBag) {
           sold = sold-(currentProduct.getPrice()- (currentProduct.getPrice()*currentProduct.getDiscount()/100));
            System.out.println("You bought the product: "+ productName);
            System.out.println("Your sold is: "+ sold);
            shoppingBag.remove(currentProduct);

            // increase popularity count
            currentProduct.setCountPopularity(currentProduct.getCountPopularity()+1);

        }
        else {
            System.out.println("You don't have enough money !");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.getId() +
                ", secondName='" + this.getSecondName() + '\'' +
                ", firstName='" + this.getFirstName() + '\'' +
                ", dateOfBirth='" + this.getDateOfBirth() + '\'' +
                ", address='" + this.getAddress() + '\'' +
                ", username='" + this.getUsername() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                '}';
    }
}
