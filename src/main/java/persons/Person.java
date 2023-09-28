package persons;

import jakarta.persistence.*;
import keys.PersonKey;
import products.Product;
import util.*;


import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "keyId", referencedColumnName = "id")
    private PersonKey personKey;

    private String secondName;
    private String firstName;
    private String dateOfBirth;
    private String address;
    private String username;
    private String pass;
    private byte[] password;


    public Person() {
    }

    public Person(String username, byte[] password) {
        this.username = username;
        this.password = password;
    }

    public Person(String username, String pass, PersonKey personKey) {
        this.username = username;
        this.pass = pass;
        this.personKey=personKey;
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

    public PersonKey getPersonKey() {
        return personKey;
    }

    public void setPersonKey(PersonKey personKey) {
        this.personKey = personKey;
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


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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


    public void filterAllProductsByText(String text) {

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
          TypedQuery<Product> results = em.createQuery(" from Product where name like:name", Product.class);
          results.setParameter("name","%"+text+"%");
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//              for (int i = 0; i < results.getResultList().size(); i++) {
//                  System.out.println(results.getResultList().get(i));
//              }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }



            em.getTransaction().commit();

        }

        finally{
            em.close();
        }

    }

    public void filterOneCategoryProductsByText(String text, Category category) {

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
        TypedQuery<Product> results = em.createQuery(" from Product where name like:name and category = :category", Product.class);
        results.setParameter("name","%"+text+"%");
        results.setParameter("category",category);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");
//        for (int i=0;i<results.getResultList().size();i++){
//            System.out.println(results.getResultList().get(i));
//        }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

        em.getTransaction().commit();
    }
        finally{
        em.close();
    }

    }

    public void comparingAllProductsByPriceAscending(){


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product order by price asc", Product.class);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");
//
//            for (int i=0;i<results.getResultList().size();i++){
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }


    }
    public void comparingAllProductsByPriceDescending(){

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product order by price desc", Product.class);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");
//            for (int i=0;i<results.getResultList().size();i++){
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

   public void comparingOneCategoryProductsByPriceAscending(int opt) {

       Category x = null;
       switch (opt) {
           case 1:
               x = Category.ANIMALE;
               break;
           case 2:
               x = Category.VESTIMENTATIE;
               break;
           case 3:
               x = Category.COSMETICE;
               break;
           case 4:
               x = Category.ALIMENTE;
               break;
           case 5, 6:
               x = Category.ELECTRONICE;
               break;
       }


           EntityManager em = ConnectionToDb.connectToDb();
           try {
               em.getTransaction().begin();
               TypedQuery<Product> results = em.createQuery(" from Product where category = :category order by price asc", Product.class);
               results.setParameter("category", x);
               if(results.getResultList().size()==0)
                   System.out.println("There are no results...");

//               for (int i = 0; i < results.getResultList().size(); i++) {
//                   System.out.println(results.getResultList().get(i));
//               }

               for(int i = 0 ; i < results.getResultList().size(); i++){
                   if(i==0) {
                       System.out.println(results.getResultList().get(i).printTableHeader2());
                   }
                   System.out.println(results.getResultList().get(i).printTableElm2());
               }

               em.getTransaction().commit();
           } finally {
               em.close();
           }

   }

    public void comparingOneCategoryProductsByPriceDescending( int opt){

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


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product where category = :category order by price desc", Product.class);
            results.setParameter("category", x);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i = 0; i < results.getResultList().size(); i++) {
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void comparingAllProductsByDiscountPriceAscending(){


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product order by priceWithDiscount asc", Product.class);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i = 0; i < results.getResultList().size(); i++) {
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }


    }
    public void comparingAllProductsByDiscountPriceDescending(){


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product  order by priceWithDiscount desc", Product.class);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i = 0; i < results.getResultList().size(); i++) {
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }



            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void comparingOneCategoryProductsByDiscountPriceAscending(int opt){

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



        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product where category = :category order by priceWithDiscount asc", Product.class);
            results.setParameter("category", x);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i = 0; i < results.getResultList().size(); i++) {
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public void comparingOneCategoryProductsByDiscountPriceDescending( int opt){

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

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product where category = :category order by priceWithDiscount desc", Product.class);
            results.setParameter("category", x);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i = 0; i < results.getResultList().size(); i++) {
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }



    public void comparingAllProductsByPopularityAscending(){


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product order by countPopularity asc", Product.class);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i=0;i<results.getResultList().size();i++){
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }



            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    public void comparingAllProductsByPopularityDescending(){


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product order by countPopularity desc", Product.class);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i=0;i<results.getResultList().size();i++){
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    public void comparingOneCategoryProductsByPopularityAscending(int opt){

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


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product where category = :category order by countPopularity asc", Product.class);
            results.setParameter("category", x);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i = 0; i < results.getResultList().size(); i++) {
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void comparingOneCategoryProductsByPopularityDescending( int opt){

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


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product where category = :category order by countPopularity desc", Product.class);
            results.setParameter("category", x);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i = 0; i < results.getResultList().size(); i++) {
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void comparingAllProductsByNameAscending(){



        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product order by name asc", Product.class);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i=0;i<results.getResultList().size();i++){
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    public void comparingAllProductsByNameDescending(){


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product order by name desc", Product.class);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i=0;i<results.getResultList().size();i++){
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    public void comparingOneCategoryProductsByNameAscending(int opt){

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


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product where category= :category order by name asc", Product.class);
            results.setParameter("category",x);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i=0;i<results.getResultList().size();i++){
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    public void comparingOneCategoryProductsByNameDescending( int opt){

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


        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> results = em.createQuery(" from Product where category= :category order by name desc", Product.class);
            results.setParameter("category",x);
            if(results.getResultList().size()==0)
                System.out.println("There are no results...");

//            for (int i=0;i<results.getResultList().size();i++){
//                System.out.println(results.getResultList().get(i));
//            }

            for(int i = 0 ; i < results.getResultList().size(); i++){
                if(i==0) {
                    System.out.println(results.getResultList().get(i).printTableHeader2());
                }
                System.out.println(results.getResultList().get(i).printTableElm2());
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }

    }

    @Transient
    HashMap<String, Integer> tableColDimHM= new HashMap<>(Map.of("idLen", 8,"firstnameLen",
            25,"secondnameLen", 25,"dateOfBirthLen", 25,"addressLen", 25,"usernameLen",
            25));

    public String printTableRow(String text, int colLen) {

        StringBuilder strBuilder = new StringBuilder();
        int insertSpaces = colLen - (2 + text.length() + 1);

        strBuilder.append("| ");

        strBuilder.append(text);

        for(int i = 0; i<insertSpaces; i++) {
            strBuilder.append(" ");
        }
        strBuilder.append("|");

        return strBuilder.toString();
    }

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

        strBuilder.append(printTableRow("ID",            tableColDimHM.get("idLen")));
        strBuilder.append(printTableRow("firstname",     tableColDimHM.get("firstnameLen")));
        strBuilder.append(printTableRow("secondname",    tableColDimHM.get("secondnameLen")));
        strBuilder.append(printTableRow("date of birth", tableColDimHM.get("dateOfBirthLen")));
        strBuilder.append(printTableRow("address",       tableColDimHM.get("addressLen")));
        strBuilder.append(printTableRow("username",      tableColDimHM.get("usernameLen")));


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

            strBuilder.append(printTableRow(String.valueOf(id).toString(), tableColDimHM.get("idLen")));
            strBuilder.append(printTableRow(firstName, tableColDimHM.get("firstnameLen")));
            strBuilder.append(printTableRow(secondName, tableColDimHM.get("secondnameLen")));
            strBuilder.append(printTableRow(dateOfBirth, tableColDimHM.get("dateOfBirthLen")));
            strBuilder.append(printTableRow(address, tableColDimHM.get("addressLen")));
            strBuilder.append(printTableRow(username, tableColDimHM.get("usernameLen")));


            return strBuilder.toString();
        } catch (Exception e) {
            System.out.println(e);
        }

        return "ERROR!!!";
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
