package persons;
import jakarta.persistence.*;
import keys.PersonKey;
import products.Product;
import util.ConnectionToDb;
import util.Constants;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

@Entity
public class User extends Person {
    private int sold;
    String idsList;

    public User() {
    }

    public User(String username, byte[] password) {
        super(username, password);
    }

    public User(String username, String pass, PersonKey personKey) {
        super(username, pass, personKey);
    }



    public String getIdsList() {
        return idsList;
    }

    public void setIdsList(String idsList) {
        this.idsList = idsList;
    }
    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
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
                EntityManager em = ConnectionToDb.connectToDb();
                try {
                    em.getTransaction().begin();
                    int rows = em.createQuery(String.format("UPDATE Person SET sold=%d WHERE username='%s'",sold,getUsername())).executeUpdate();
                    em.getTransaction().commit();
                }
                finally{
                    em.close();
                }
                System.out.println("You have " + sold + " credit !");
                break;
            }

        }
        while(true);
    }

    public void addToShoppingBag(String name){


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
           TypedQuery<Product> foundProduct = em.createQuery("FROM Product where name = :nameParam", Product.class);
           foundProduct.setParameter("nameParam",name);
           try {
               Product productToBuy = foundProduct.getSingleResult();
               System.out.println("The product was added to your cart!!!");
               System.out.println("Please press 1 if you want to buy the product and press 2 if you want to go to options menu");
               Scanner sc = new Scanner(System.in);
               int opt = sc.nextInt();
               if(opt==1){
                   buyProduct(productToBuy);}
               else if(opt==2) {
                   return;
               }

           }
           catch(NoResultException noResultException){
               System.out.println("The product can not be added yo your cart !!!");


           }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    public void buyProduct (Product productToBuy){


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            User foundPerson= em.createQuery(String.format("From Person where username='%s' ", getUsername()),User.class).getSingleResult();

            if(foundPerson.getSold()>=(productToBuy.getPrice()- (productToBuy.getPrice()*productToBuy.getDiscount()/100))) {
                sold = sold-(productToBuy.getPrice()- (productToBuy.getPrice()*productToBuy.getDiscount()/100));
                System.out.println("You have bought the product: "+ productToBuy.getName() + " and you have spent "+ productToBuy.getPriceWithDiscount());
                int rows = em.createQuery(String.format("UPDATE Person SET sold=%d WHERE username='%s'",sold,getUsername())).executeUpdate();
               if(idsList==null){
                   setIdsList(String.valueOf(productToBuy.getId()));
               }
               else
                setIdsList(getIdsList()+","+ productToBuy.getId());

                int row2 = em.createQuery(String.format("UPDATE Person SET idsList='%s' WHERE username='%s'",idsList,getUsername())).executeUpdate();
                System.out.println("Your sold is: "+ sold);


                // increase popularity count
                productToBuy.setCountPopularity(productToBuy.getCountPopularity()+1);
                int row = em.createQuery(String.format("UPDATE Product SET countPopularity=%d WHERE id=%d",productToBuy.getCountPopularity(),productToBuy.getId())).executeUpdate();


            }
            else {
                System.out.println("You don't have enough money !");
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }


    }


    public void viewSold() {

        EntityManager em = ConnectionToDb.connectToDb();

        try {
            em.getTransaction().begin();
            User foundPerson = em.find(User.class, getId());
            System.out.println("Your sold is: " + foundPerson.getSold());
            if(foundPerson.getSold()==0)
                System.out.println("Please add money if you want to place an order.");
        }
        finally {
            em.close();
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
