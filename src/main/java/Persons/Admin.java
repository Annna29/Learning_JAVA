package Persons;
import DB.DB_Keys;
import Products.Product;
import Util.Constants;
import Util.RSA;
import Util.Validation;
import Util.ValidationType;

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

public class Admin extends Person{

    public Admin(String username, byte[] password) {
        super(username, password);
    }

    public Admin() {

    }

    public void addProducts(Product product, ArrayList<Product> arrayList){
        product.populateProduct();
        arrayList.add(product);
    }

    public void modifyPrice(int id ,String productName, int newPrice, ArrayList<Product> arrayList){
        boolean isPresent = false;
       for(int i = 0; i< arrayList.size(); i++){
           if(((arrayList.get(i).getName().equalsIgnoreCase(productName)) && arrayList.get(i).getId()==id)){
               arrayList.get(i).setPrice(newPrice);
               System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
               isPresent=true;
           }
        }
       if(!isPresent)
           System.out.println(Constants.MESSAGE_PROD_NOT_FOUND_11);
    }

    public void modifyDiscount(int id,String productName, int newDiscount, ArrayList<Product> arrayList){
        boolean isPresent= false;
        for(int i = 0; i< arrayList.size(); i++){
            if((arrayList.get(i).getName().equalsIgnoreCase(productName))  && arrayList.get(i).getId()==id){
                arrayList.get(i).setDiscount(newDiscount);
                System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
                isPresent=true;
            }
        }
        if(!isPresent)
            System.out.println(Constants.MESSAGE_PROD_NOT_FOUND_11);
    }

    public void seeAllProducts(ArrayList<Product>arrayList){
        if(!arrayList.isEmpty())
        for (Product p : arrayList) {
            System.out.println(p);
        }
       else if (arrayList.isEmpty()) {
            System.out.println(Constants.MESSAGE_PROD_LIST_EMPTY_12);
        }
    }

    // temp --
    public void seeAllProductsTable(ArrayList<Product>arrayList) {
        if (!arrayList.isEmpty()) {
            System.out.println(arrayList.get(0).printTableHeader());
            for (Product p : arrayList) {
                System.out.println(p.printTalbeElm());
            }
        }
        else if (arrayList.isEmpty()) {
            System.out.println(Constants.MESSAGE_PROD_LIST_EMPTY_12);
        }
    }


    public void modifyDescription(int id,String productName, String newDescription, ArrayList<Product>arrayList) {
        boolean isPresent= false;
        for (int i = 0; i < arrayList.size(); i++) {
            if (((arrayList.get(i).getName().equalsIgnoreCase(productName))  && arrayList.get(i).getId()==id ) ){
                arrayList.get(i).setDescription(newDescription);
                System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
                isPresent=true;
            }
        }

        if(!isPresent)
            System.out.println(Constants.MESSAGE_PROD_NOT_FOUND_11);
    }

    public void removeProductById( String productName,int id, ArrayList<Product> arrayList){
        boolean isPresent = false;
        for (int i = 0; i < arrayList.size(); i++) {
            if ((arrayList.get(i).getId())==id && (arrayList.get(i).getName().equalsIgnoreCase(productName))) {
                  arrayList.remove(arrayList.get(i));
                  isPresent=true;
                System.out.println("SUCCESS! You have removed the product with id = "+ id +" and name : " + productName + "!");
            }
        }
        if(!isPresent) System.out.println("The product with id = " + id + " and name: "+ productName + " was not found ! ");
    }

    public void seeAllPersons(HashMap<String,Person> mapPersons){
        if(!mapPersons.isEmpty())
        for(Map.Entry<String,Person> el : mapPersons.entrySet()){
            System.out.println(el.getValue());
        }

        else if (mapPersons.isEmpty()) {
            System.out.println(Constants.MESSAGE_PERS_LIST_EMPTY_13);

        }
    }

    public void removePerson(int id, HashMap<String,Person> mapPersons){
        boolean isPResent = false;
        Person person = new Person();
        for(Map.Entry<String,Person> el : mapPersons.entrySet()){
           if((el.getValue().getId()==id)) {
               person = el.getValue();
               isPResent = true;
           }

        }
        mapPersons.remove(person.getUsername());
        if(!isPResent)
            System.out.println("Person was not found !");
        if(isPResent)
            System.out.println("You have removed the person with the id: " + id);
    }

    public void addAnewPerson(HashMap<String,Person>mapPersons, Person newPerson){
        mapPersons.put(newPerson.getUsername(),newPerson);
    }

    public void populateDetails( DB_Keys db_k) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
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


//            System.out.println("Enter the password:");
//            password = RSA.applyRSAEncryptingData(sc.nextLine(), RSA.genRSAKeys().getPublic());
//            isPasswordValid=Validation.validatePassword("abc", Constants.USERNAME_PASSWORD_LENGTH);
        }
        while (!isPasswordValid);
        KeyPair pair = RSA.genRSAKeys();
        db_k.addKeys(getUsername(), pair);
        setPassword( RSA.applyRSAEncryptingData(newPersonPass, db_k.getPublicKeyForUser(getUsername())));

        System.out.println("Enter the address :");
        String ad = Validation.readAString(ValidationType.DETAILS);
        setAddress(ad);

        System.out.println("Enter the date of birth :");
        String db = Validation.readAString(ValidationType.DETAILS);
        setDateOfBirth(db);

        System.out.println("Enter the id :");
        int id = Validation.readANumber(ValidationType.ID);
        setId(id);

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

