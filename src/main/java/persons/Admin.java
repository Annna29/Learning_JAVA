package persons;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import keys.PersonKey;
import products.Product;
import util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.Console;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Entity
public class Admin extends Person{

    public Admin(String username, byte[] password) {
        super(username, password);
    }

    public Admin(String username, String pass, PersonKey personKey) {
        super(username, pass,personKey);
    }

    public Admin() {
    }

    public void addProducts(Product product){
        product.populateProduct();

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        }
        finally{
            em.close();
        }

    }

    public void modifyPrice(int id , int newPrice){

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            Product foundProduct ;
              try {
                   foundProduct = em.find(Product.class, id);
                  foundProduct.setPrice(newPrice);
                  foundProduct.setPriceWithDiscount(newPrice-(foundProduct.getDiscount()*newPrice/100));
                  em.merge(foundProduct);
                  System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
              }
              catch(NullPointerException nullPointerException){
                  System.out.println(Constants.MESSAGE_PROD_NOT_FOUND_11);
              }


            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    public void modifyDiscount(int id, int newDiscount){


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            Product foundProduct;
            try {
                foundProduct = em.find(Product.class, id);
                foundProduct.setDiscount(newDiscount);
                foundProduct.setPriceWithDiscount(foundProduct.getPrice()-(newDiscount* foundProduct.getPrice()/100));
                em.merge(foundProduct);
                System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);

            }
            catch(NullPointerException nullPointerException) {
                System.out.println(Constants.MESSAGE_PROD_NOT_FOUND_11);
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }

    }

    public void seeAllProducts(){


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            Query listOfAllProductsFromDb = em.createNativeQuery("Select * from Product", Product.class);
          try {
              if (listOfAllProductsFromDb.getResultList().size() != 0)
                  for (Object p : listOfAllProductsFromDb.getResultList()) {
                      System.out.println(p + "\n");
                  }

          }
           catch(NullPointerException nullPointerException){
               System.out.println(Constants.MESSAGE_PROD_LIST_EMPTY_12);

           }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }


    }


    public void seeAllProductsTable() {

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            Query listOfAllProductsFromDb = em.createNativeQuery("Select * from Product", Product.class);
            ArrayList<Product> l = new ArrayList<>();
            try {
                if (listOfAllProductsFromDb.getResultList().size() != 0)
                    for (Object p : listOfAllProductsFromDb.getResultList()) {
                        l.add((Product) p);
                    }

                for (int i = 0; i < l.size(); i++) {
                    if (i == 0) {
                        System.out.println(l.get(i).printTableHeader2());
                        //System.out.println(l.get(i).printTableHeader());
                    }
                    System.out.println(l.get(i).printTableElm2());
                    //System.out.println(l.get(i).printTalbeElm());
                }
            }
            catch (NullPointerException nullPointerException){
                System.out.println(Constants.MESSAGE_PROD_LIST_EMPTY_12);
            }
            em.getTransaction().commit();
        }
        finally{
            em.close();
        }

    }


    public void modifyDescription(int id, String newDescription) {

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            Product foundProduct;
            try {
                foundProduct = em.find(Product.class, id);
                foundProduct.setDescription(newDescription);
                em.merge(foundProduct);
                System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
            }
          catch(NullPointerException nullPointerException) {
              System.out.println(Constants.MESSAGE_PROD_NOT_FOUND_11);
          }
            em.getTransaction().commit();
        }
        finally{
            em.close();
        }

    }

    public void removeProductById(int id){

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            try {
                Product productToRemove = em.find(Product.class, id);
                em.remove(productToRemove);
                System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
            }
            catch (IllegalArgumentException illegalArgumentException){
                System.out.println(Constants.MESSAGE_PROD_NOT_FOUND_11);

            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }

    }

    public void seeAllPersons(){

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            Query listOfPersonsFromDb = em.createNativeQuery("Select * from Person", Person.class);
        try {
//            if (listOfPersonsFromDb.getResultList().size() != 0)
//                for (Object p : listOfPersonsFromDb.getResultList()) {
//                    System.out.println(p);
//                }

            for(int i =0 ; i< listOfPersonsFromDb.getResultList().size(); i++){

                if(i==0){
                    System.out.println(((Person) listOfPersonsFromDb.getResultList().get(i)).printTableHeader2());
                }
                System.out.println( ((Person) listOfPersonsFromDb.getResultList().get(i)).printTableElm2());
            }
        }
        catch (NullPointerException nullPointerException){
            System.out.println(Constants.MESSAGE_PERS_LIST_EMPTY_13);
        }

            em.getTransaction().commit();
       }
       finally{
            em.close();
        }

        }

    public void removePerson(int id){

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            try {
                Person personToRemove = em.find(Person.class, id);
                em.remove(personToRemove);
                System.out.println("You have removed the person with the id: " + id);
            }
            catch(IllegalArgumentException illegalArgumentException){
                System.out.println("Person was not found !");
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    public void addAnewPerson( Person newPerson) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, IOException, BadPaddingException, InvalidKeyException {

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
           em.persist(newPerson);

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }

    }

    public void populateDetails() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        Scanner sc = new Scanner(System.in);
        boolean isFirstNameValid, isSecondNameValid, isAddressValid, isUserNameValid, isPasswordValid, isDateValid, isIdValid;
        System.out.println("Enter the first name:");
        String fn = Validation.readAString(ValidationType.DETAILS);
        setFirstName(fn);


        System.out.println("Enter the second name:");
        String sn = Validation.readAString(ValidationType.DETAILS);
        setSecondName(sn);


        System.out.println("Enter the username:");
        String un = Validation.readAString(ValidationType.USERNAME);
        setUsername(un);



        String newPersonPass;
        do {
            Console console = System.console();
            char[] pass = console.readPassword("Enter password: ");
            //newPerson.setPassword( String.copyValueOf(pass));
            newPersonPass = String.copyValueOf(pass);
            isPasswordValid = Validation.validatePassword(newPersonPass, Constants.USERNAME_PASSWORD_LENGTH);

        }
        while (!isPasswordValid);
        KeyPair pair = RSA.genRSAKeys();
        String stringValOfPrivateKey = Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());
        String stringValOfPublicKey =   Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());
        setPersonKey(new PersonKey(stringValOfPrivateKey,stringValOfPublicKey));

        setPassword((RSA.applyRSAEncryptingData(newPersonPass, pair.getPublic())));
        setPass(Base64.getEncoder().encodeToString(getPassword()));

        System.out.println("Enter the address :");
        String ad = Validation.readAString(ValidationType.DETAILS);
        setAddress(ad);

        System.out.println("Enter the date of birth :");
        String db = Validation.readAString(ValidationType.DETAILS);
        setDateOfBirth(db);



        System.out.println(" Well done, you have added a new admin ! ");

    }

    @Override
    public String toString() {
        return "Admin{" +
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

