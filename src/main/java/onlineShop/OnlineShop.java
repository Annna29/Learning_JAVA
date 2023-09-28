package onlineShop;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import keys.PersonKey;
import persons.Admin;
import persons.Person;
import persons.User;
import products.*;
import util.*;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.Console;
import java.io.IOException;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OnlineShop {

    Person currentPerson = new Person();

    // Constructor
    public OnlineShop() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        //admin
        manualAddAdmin("Mart","1234@");
        //user
        manualAddUser("Roar","1234%");
        //adding some products to the shop
        addProducts();
    }


    // Manually Add User Function
    public void manualAddUser(String username, String pass) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
       byte[] encryptedPass;

        KeyPair pair = RSA.genRSAKeys();
        String stringValOfPrivateKey = Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());
        String stringValOfPublicKey = Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());

        encryptedPass = RSA.applyRSAEncryptingData(pass,pair.getPublic());


        EntityManager em = ConnectionToDb.connectToDb();

        try {
            em.getTransaction().begin();
//
//         User user = new User(username,Base64.getEncoder().encodeToString(encryptedPass),new PersonKey(stringValOfPrivateKey,stringValOfPublicKey));
//           em.persist(user);


           em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    // Manually Add Admin Function
    public void manualAddAdmin(String username, String pass) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        byte[] encryptedPass;

        KeyPair pair = RSA.genRSAKeys();

        encryptedPass = RSA.applyRSAEncryptingData(pass, pair.getPublic());

        EntityManager em = ConnectionToDb.connectToDb();
        String stringValOfPrivateKey = Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());
        String stringValOfPublicKey = Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());

        try {
            em.getTransaction().begin();

//      Admin admin = new Admin(username, Base64.getEncoder().encodeToString(encryptedPass),new PersonKey(stringValOfPrivateKey,stringValOfPublicKey));
//          em.persist(admin);

           Admin foundAdmin= em.find(Admin.class,1);
           foundAdmin.setAddress("Bucuresti");
           foundAdmin.setFirstName("Mart");
           foundAdmin.setSecondName("Mart");
           foundAdmin.setDateOfBirth("02.05.2000");
            System.out.println(foundAdmin);
            em.merge(foundAdmin);
            em.getTransaction().commit();
        }
        finally{
            em.close();
        }

    }

    public void addProducts(){

        EntityManager em = ConnectionToDb.connectToDb();
        try {

            em.getTransaction().begin();
   /*
            Product prd = new Laptop(Category.ELECTRONICE,"Laptop Dell Precision 5680", "Best of 2023",8900,0,0,"Gaming","Windows");
            em.persist(prd);
            Product prd2 = new Laptop(Category.ELECTRONICE,"Laptop Dell Precision  3581", "Powerfull for gaming",12900,0,0,"Gaming","LINUX");
            em.persist(prd2);
            Product prd3 = new Laptop(Category.ELECTRONICE,"Laptop Dell XPS 9530", "eXtreme Performance System - slim design",9900,0,0,"Business","LINUX");
            em.persist(prd3);
            Product prd4 = new Laptop(Category.ELECTRONICE,"Laptop Dell XPS 13", "eXtreme Performance System - long batery life ",7900,0,0,"Business","Windows");
            em.persist(prd4);
            Product prd5 = new Laptop(Category.ELECTRONICE,"Laptop Dell Ultrabook XPS", "Slim & Business laptop",10000,1,0,"Business","LINUX");
            em.persist(prd5);
            Product prd6 = new Laptop(Category.ELECTRONICE,"Laptop DELL Inspiron 3520", "Gaming laptop- best price",3500,5,0,"Business","LINUX");
            em.persist(prd6);
            Product prd7 = new Laptop(Category.ELECTRONICE,"Laptop LENOVO IdeaPad 3", "Lenovo Eye Care & Smart Wireless",3300,0,0,"Business","Windows");
            em.persist(prd7);
            Product prd8 = new Laptop(Category.ELECTRONICE,"Laptop LENOVO Legion 5", "max performance for gamers",7700,0,0,"Gaming","Windows");
            em.persist(prd8);
            Product prd9 = new Laptop(Category.ELECTRONICE,"Laptop Gaming Lenovo Legion Slim 7", "Smart Engine & Lenovo LA2-Q si LA1 AI Chips",6700,0,0,"Gaming","LINUX");
            em.persist(prd9);
            Product prd10 = new Laptop(Category.ELECTRONICE,"Laptop Lenovo V15", "AMD Ryzen 5/ 6 nuclee",2500,6,0,"Gaming","Windows");
            em.persist(prd10);
            Product prd11 = new AnimalFood(Category.ANIMALE,"Purina PRO PLAN", "Best choice for sensitive skin",335,1,0,"SALMON");
            em.persist(prd11);
            Product prd12 = new AnimalFood(Category.ANIMALE,"Purina PRO PLAN puppy", "ideal food for puppies (0-50 kg)",355,1,0,"BEEF");
            em.persist(prd12);
            Product prd13 = new AnimalFood(Category.ANIMALE,"Purina PRO PLAN puppy-big pack", "ideal food for all ages",566,1,0,"LAMB");
            em.persist(prd13);
            Product prd14 = new AnimalFood(Category.ANIMALE,"ROYAL CANIN", "only natural ingredients",420,0,0,"SALMON");
            em.persist(prd14);
            Product prd15 = new AnimalFood(Category.ANIMALE,"PEDIGREE", "tasty snacks for your dog",120,0,0,"BEEF");
            em.persist(prd15);
            Product prd16 = new FoodAndDrinks(Category.ALIMENTE, "Jacobs", "Coffee for a greater morning", 55,0,0,"intense");
            em.persist(prd16);
            Product prd17 = new FoodAndDrinks(Category.ALIMENTE, "LAVAZZA", "Best coffee aroma", 75,0,0,"intense");
            em.persist(prd17);
            Product prd18 = new FoodAndDrinks(Category.ALIMENTE, "Tchibo caffisimo", "Capsule cremoase- aroma migdale", 70,0,0,"almond");
            em.persist(prd18);
            Product prd19 = new FoodAndDrinks(Category.ALIMENTE, "Haribo", "Bomboane si jeleuri fructate ", 50,0,0,"Tropical");
            em.persist(prd19);
            Product prd20 = new MobilePhone(Category.ELECTRONICE, "Samsung Galaxy A53", "Tehnologie 5G ", 1950,0,0,"Android");
            em.persist(prd20);
            Product prd21 = new MobilePhone(Category.ELECTRONICE, "Samsung Galaxy S21", "Best Samsung technology ", 3950,0,0,"Android");
            em.persist(prd21);
            Product prd22 = new MobilePhone(Category.ELECTRONICE, "Samsung Galaxy S22", "Smart charging technology  ", 4950,0,0,"Android");
            em.persist(prd22);
            Product prd23 = new Dermatocosmetics(Category.COSMETICE, "Kalvin Klein- Euphoria","Strong perfume -black orchid fragrance",350,0,0,"Floral");
            em.persist(prd23);
            Product prd24 = new Dermatocosmetics(Category.COSMETICE, "Giorgio Armani- Aqua di gioia","Aquatic perfume",350,0,0,"Fresh");
            em.persist(prd24);
            Product prd25 = new Dermatocosmetics(Category.COSMETICE, "Ange ou Demon- Givenchy","Sweet mandarin perfume",360,0,0,"Floral");
            em.persist(prd25);
            Product prd26 = new Clothes(Category.VESTIMENTATIE,"Geaca Ski- Columbia","Omni heat technology",1500,0,0,"black");
            em.persist(prd26);
            Product prd27 = new Clothes(Category.VESTIMENTATIE,"Geaca- The Northface","Waterproof, breathable technology",1800,0,0,"black");
            em.persist(prd27);
            Product prd28 = new Clothes(Category.VESTIMENTATIE,"Geaca- Colmar","The best choice for ski and snowboard",3800,0,0,"blue");
            em.persist(prd28);

    */

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    //
    public void startUpMenu() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        int option;
        boolean runShop = true;

        System.out.println("Welcome to My online shop !");

        do {
                OnlineShopMenu.printLoginMenu();
                option=OnlineShopMenu.readMenuOption();

                switch (option) {
                    case 1:
                        userSignUp();
                        break;
                    case 2:
                        // If LogIn fails --> currentPerson == NULL;
                        currentPerson = logIn();

                        // When functions end current person will be null (LOGOUT)
                        if(Admin.class.isInstance(currentPerson)) {
                            runAsAdmin(Admin.class.cast(currentPerson));
                        } else if (User.class.isInstance(currentPerson)) {
                            runAsUser(User.class.cast(currentPerson));
                        }
                        break;
                    case 9:
                        runShop = false;
                        break;
                    default:
                        System.out.println(Constants.MESSAGE_INVALID_OPTION_1);
                        break;
                }
        } while (runShop);
    }


    public void userSignUp () throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        User newUser = new User();
        String newPersonPass;
        String tmpScannerStr;

        boolean retry = false;
        boolean isUsernameValid;
        boolean isUsernameInDB = false;
        char[] pass;

        Scanner scanner = new Scanner(System.in);

        System.out.println("-- SIGN UP MENU --");

        do{

            if(!retry) { System.out.println("Enter username: "); }

            tmpScannerStr = scanner.nextLine();
            if(tmpScannerStr.equals("9") && retry) { return; }; // Exit signUp if no.9 is typed in.

            newUser.setUsername(tmpScannerStr);

            isUsernameValid = Validation.validateDetails(newUser.getUsername(), Constants.USERNAME_PASSWORD_LENGTH);

            if(!isUsernameValid) {
                System.out.println("Username and password must be at least 4 characters !");
                System.out.println(Constants.MESSAGE_CANCEL_ACTION_3);
                retry = true;
            } else {
                EntityManager em = ConnectionToDb.connectToDb();
                try {
                    em.getTransaction().begin();

                    String myQ = String.format("SELECT * FROM person WHERE username='%s'", newUser.getUsername());
                    Query q = em.createNativeQuery(myQ, Person.class);

                    // If SQL query returns something it means that the user is in the DB.
                    isUsernameInDB = (q.getResultList().size() > 0) ? true : false;

                    //em.getTransaction().commit(); // TODO: not needed since this OP is Read-Only
                }
                finally{
                    em.close();
                }

                if(isUsernameInDB) {
                    System.out.println("This username is already used...Please try another one ! ");
                    System.out.println(Constants.MESSAGE_CANCEL_ACTION_3);
                    retry = true;
                }
            }
        } while (isUsernameInDB || !isUsernameValid);

        boolean isPasswordValid;
        retry = false; // Reinitialize in case it was set in the username validation.

        do {
            Console console = System.console();

            if(retry) {
                System.out.println(Constants.MESSAGE_CANCEL_ACTION_3);
                pass = console.readPassword();
            } else {
                pass = console.readPassword("Enter password: ");
            }

            newPersonPass = String.copyValueOf(pass);

            // If password contains only one no. (that being 9) in
            // the retry step, EXIT to Main-Menu
            if(newPersonPass.equals("9") && retry) { return; };

            isPasswordValid = Validation.validatePassword(newPersonPass, Constants.USERNAME_PASSWORD_LENGTH);

            if(!isPasswordValid){
                System.out.println("Password must contains a special character: $,%,@,#. \nPassword and username must be at least 4 characters ");
                retry = true;
            }
        } while(!isPasswordValid);

        // -- Logic to Encrypt Password -- //

        // Gen Keys and push them into the PersonKey Database.
        KeyPair keyPair = RSA.genRSAKeys();

        String stringValOfPrivateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        String stringValOfPublicKey  = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

        newUser.setPersonKey(new PersonKey(stringValOfPrivateKey,stringValOfPublicKey));

        byte[] RSAPassword = RSA.applyRSAEncryptingData(newPersonPass, keyPair.getPublic());
        // System.out.println(Base64.getEncoder().encodeToString(RSAPassword));
        newUser.setPassword((RSAPassword)); // TODO: Can be removed. Password is stored as String (encoded)
        newUser.setPass(Base64.getEncoder().encodeToString(RSAPassword));

        //
        System.out.println("You have successfully SignedUp!! :D ");
        newUser.setAccountDetails();

        EntityManager em = ConnectionToDb.connectToDb();

        try {
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
        }
        finally{
            em.close();
        }

        System.out.println("Well done! :)");
        System.out.println("Now let's go shopping!! :D ");
    }

    public Person logIn () throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, IOException, BadPaddingException, InvalidKeyException {
        String username,password;
        String decryptedPass;

        Scanner scanner = new Scanner(System.in);
        boolean ok = false;
        char[] pass;

        Query q;
        Person p;

        System.out.println("Please LOG IN : ");
        System.out.println("Enter username : ");
        username = scanner.nextLine();

//        System.out.println("Enter password: ");
//        password=scanner.nextLine();

//        -->
        Console console = System.console();
        pass = console.readPassword("Enter password: ");
        password = String.copyValueOf(pass);
//         <--

        boolean isPresent = false;

        ArrayList<Person>listOfAllPersons = new ArrayList<>();

        EntityManager em = ConnectionToDb.connectToDb();
        try {
            em.getTransaction().begin();

            String myQ = String.format("SELECT * FROM Person WHERE username='%s'", username);
            q = em.createNativeQuery(myQ, Person.class);
            try {
                p = (Person) q.getSingleResult();
            } catch (NoResultException noResultException){
                System.out.println("User not registered!");
                return null;
            }

            // TODO: Check if case sensitive match is made between DB & Typed In Username

            byte[] decodedPrivateKey = Base64.getDecoder().decode(p.getPersonKey().getPrivateKeyVal());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            byte[] myPassToDecode = Base64.getDecoder().decode(p.getPass());
            decryptedPass = RSA.applyRSADecryptingData(myPassToDecode, privateKey);

            if (decryptedPass.equals(password)) {
                System.out.println("You have successfully log in ! ");
                return p;
            } else {
                System.out.println("Wrong password!");
            }

            em.getTransaction().commit();
        }
        finally{
            em.close();
        }

        return null;
    }

    public User runAsUser(User user){

        System.out.println("Please select an action!");
        Scanner scanner = new Scanner(System.in);
        String filterText;

        int option;
        int submenuOpt;
        boolean runShop = true;

        do {
            OnlineShopMenu.printSecondMenuUser();
            option = OnlineShopMenu.readMenuOption();

            switch (option) {
                case 1:
//                    OnlineShopMenu.printFilterProductsByTextMenu();
//                    submenuOpt = OnlineShopMenu.readMenuOption();
//                    filterProductsByText(user, submenuOpt);
                      filterProductsByText(user);
                    break;

                case 2:
                 //   OnlineShopMenu.printDisplayProductByPriceMenu();
                 //   submenuOpt = OnlineShopMenu.readMenuOption();
                 //   sortProductByPrice(user, submenuOpt);
                    sortProductByPrice(user);
                    break;

                case 3:
//                    OnlineShopMenu.printDisplayProductByPopularityMenu();
//                    submenuOpt = OnlineShopMenu.readMenuOption();
//                    sortProductByPopularity(user, submenuOpt);
                    sortProductByPopularity(user);
                    break;

                case 4:
//                    OnlineShopMenu.printDisplayProductByNameMenu();
//                    submenuOpt = OnlineShopMenu.readMenuOption();
 //                   sortProductByName(user, submenuOpt);
                    sortProductByName(user);
                    break;

                case 5:
                      user.addMoney();
                      break;

                case 6:
                    // adding products to your shopping cart and after that you can easily buy them
                    shoppingAction(user);

                      break;

                case 7:
//                    OnlineShopMenu.printDisplayProductByDiscountPriceMenu();
//                    submenuOpt = OnlineShopMenu.readMenuOption();
 //                   sortProductByDiscountPrice(user, submenuOpt);
                    sortProductByDiscountPrice(user);
                    break;

                case 9: runShop = false;
                        break;

                case 8: user.viewSold();
                        break;


                default: System.out.println(Constants.MESSAGE_INVALID_OPTION_1);
                         break;
            }
        } while(runShop);

        return null;
    }

    public void filterProductsByText(Person person) {
        String filterText;
        while (true) {
            OnlineShopMenu.printFilterProductsByTextMenu();
           int option = OnlineShopMenu.readMenuOption();

            if (option == 1) {

                System.out.println(Constants.MESSAGE_ENTER_TEXT_4);
                filterText = Validation.readAString(ValidationType.SEARCHING_TEXT);
                person.filterAllProductsByText(filterText);

            }
            if (option == 2) {
                System.out.println(Constants.MESSAGE_ENTER_TEXT_4);
                filterText = Validation.readAString(ValidationType.SEARCHING_TEXT);
                System.out.println("Please choose a category: ");
                System.out.println("SELECT the products's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");

                int cat = -1;
                cat = OnlineShopMenu.readMenuOption();

                switch (cat) {
                    case 1:
                        person.filterOneCategoryProductsByText(filterText, Category.ANIMALE);
                        break;
                    case 2:
                        person.filterOneCategoryProductsByText(filterText, Category.VESTIMENTATIE);
                        break;
                    case 3:
                        person.filterOneCategoryProductsByText(filterText, Category.COSMETICE);
                        break;
                    case 4:
                        person.filterOneCategoryProductsByText(filterText, Category.ALIMENTE);
                        break;
                    case 5:
                        person.filterOneCategoryProductsByText(filterText, Category.ELECTRONICE);
                        break;
                    default:
                        System.out.println(Constants.MESSAGE_INVALID_CATEGORY_5);
                        break;
                }
            }

            if (option == 3) {
                return;
            }

            if (option > 3) {
                System.out.println(Constants.MESSAGE_INVALID_OPTION_6);

            }
        }
    }

    public void sortProductByPrice(Person person) {

        while(true) {

             OnlineShopMenu.printDisplayProductByPriceMenu();
             int optionPrice = OnlineShopMenu.readMenuOption();

            if (optionPrice == 1)
                person.comparingAllProductsByPriceAscending();

            if (optionPrice == 2)
                person.comparingAllProductsByPriceDescending();

            if (optionPrice == 3) {
                System.out.println("SELECT the product's category you want to search:\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
                int chosenOp = OnlineShopMenu.readMenuOption();
                person.comparingOneCategoryProductsByPriceAscending(chosenOp);
            }

            if (optionPrice == 4) {
                System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
                int chosenOp = OnlineShopMenu.readMenuOption();
                person.comparingOneCategoryProductsByPriceDescending(chosenOp);
            }

            if(optionPrice==5)
                return;

            if (optionPrice > 5 || optionPrice < 1) {
                System.out.println(Constants.MESSAGE_INVALID_OPTION_6);
            }

        }
    }

    public void sortProductByPopularity(Person person) {

        while(true) {
            OnlineShopMenu.printDisplayProductByPopularityMenu();
            int optionPopularity = OnlineShopMenu.readMenuOption();

            if (optionPopularity == 1)
                person.comparingAllProductsByPopularityAscending();

            if (optionPopularity == 2)
                person.comparingAllProductsByPopularityDescending();

            if (optionPopularity == 3) {
                System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
                int chosenOp = OnlineShopMenu.readMenuOption();
                person.comparingOneCategoryProductsByPopularityAscending(chosenOp);
            }

            if (optionPopularity == 4) {
                System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
                int chosenOp = OnlineShopMenu.readMenuOption();
                person.comparingOneCategoryProductsByPopularityDescending(chosenOp);
            }

            if(optionPopularity ==5){
               return;
            }

            if (optionPopularity > 5 || optionPopularity < 1) {
                System.out.println(Constants.MESSAGE_INVALID_OPTION_6);
            }

        }
    }

    public void sortProductByName(Person person) {
        while (true) {

            OnlineShopMenu.printDisplayProductByNameMenu();
            int optionNameMenu = OnlineShopMenu.readMenuOption();


            if (optionNameMenu == 1)
                person.comparingAllProductsByNameAscending();

            if (optionNameMenu == 2)
                person.comparingAllProductsByNameDescending();

            if (optionNameMenu == 3) {
                System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
                int chosenOp = OnlineShopMenu.readMenuOption();
                person.comparingOneCategoryProductsByNameAscending(chosenOp);
            }

            if (optionNameMenu == 4) {
                System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
                int chosenOp = OnlineShopMenu.readMenuOption();
                person.comparingOneCategoryProductsByNameDescending(chosenOp);
            }

            if (optionNameMenu == 5) {
                return;
            }

            if (optionNameMenu > 5 || optionNameMenu < 1) {
                System.out.println(Constants.MESSAGE_INVALID_OPTION_6);
            }

        }

    }

    public void  sortProductByDiscountPrice (Person person){

        while(true) {

            OnlineShopMenu.printDisplayProductByDiscountPriceMenu();
           int optionDiscountPrice = OnlineShopMenu.readMenuOption();


            if (optionDiscountPrice == 1)
                person.comparingAllProductsByDiscountPriceAscending();

            if (optionDiscountPrice == 2)
                person.comparingAllProductsByDiscountPriceDescending();

            if (optionDiscountPrice == 3) {
                System.out.println("SELECT the product's category you want to search:\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
                int chosenOp = OnlineShopMenu.readMenuOption();
                person.comparingOneCategoryProductsByDiscountPriceAscending(chosenOp);
            }

            if (optionDiscountPrice == 4) {
                System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
                int chosenOp = OnlineShopMenu.readMenuOption();
                person.comparingOneCategoryProductsByDiscountPriceDescending(chosenOp);
            }

            if(optionDiscountPrice ==5){
                return;
            }

            if (optionDiscountPrice > 5 || optionDiscountPrice < 1) {
                System.out.println(Constants.MESSAGE_INVALID_OPTION_6);
            }

        }
    }

    public void shoppingAction(User user){
        String prName;
        System.out.println("Please enter the name of the product you want to add to your cart: ");
        prName = Validation.readAString(ValidationType.DETAILS);
        user.addToShoppingBag(prName);

    }


    public Admin runAsAdmin(Admin admin) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        System.out.println("Hello Admin! Please select an action!");
        int option;
        int submenuOpt;
        boolean runShop = true;

        do {
            OnlineShopMenu.printSecondMenuAdmin();
            option = OnlineShopMenu.readMenuOption();

            switch (option) {

                case 1:
//                    System.out.println("SELECT the category of product you want to add :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Laptop\n6. Mobile phone\n7.Return to previous menu");
//                    submenuOpt = OnlineShopMenu.readMenuOption();
 //                   manuallyAddProducts(admin, submenuOpt);
                    manuallyAddProducts(admin);
                    break;

                case 2:
                    modifyProductPrice(admin);
                    break;

                case 3:
                    modifyProductDescription(admin);
                    break;

                case 4:
                    removeProduct(admin);
                    break;

                case 5:
                    admin.seeAllProducts();
                    admin.seeAllProductsTable();
                    break;

                case 6:
                    admin.seeAllPersons();
                    break;

                case 7:
                    removePersonByAdmin(admin);
                    break;

                case 8:
                    // admin will add newAdmin (Admin newAdmin)
                    addNewAdmin(admin);
                    break;

                case 9:
                    modifyProductDiscount(admin);
                    break;

                case 10:
                    runShop = false;
                    break;
                default:
                    System.out.println(Constants.MESSAGE_INVALID_OPTION_1);
                    break;
            }
        } while(runShop);

        return null;
    }

    public void manuallyAddProducts(Admin admin) {

        while (true) {

            System.out.println("SELECT the category of product you want to add :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Laptop\n6. Mobile phone\n7. Return to previous menu");
            int category = OnlineShopMenu.readMenuOption();
            if (category == 1)
                admin.addProducts(new AnimalFood());
            if (category == 2)
                admin.addProducts(new Clothes());
            if (category == 3)
                admin.addProducts(new Dermatocosmetics());
            if (category == 4)
                admin.addProducts(new FoodAndDrinks());
            if (category == 5)
                admin.addProducts(new Laptop());
            if (category == 6)
                admin.addProducts(new MobilePhone());
            if (category == 7)
               break;

            if (category >7) {
                System.out.println(Constants.MESSAGE_INVALID_OPTION_7);

            }

        }
    }

    public void modifyProductPrice(Admin admin) {

        boolean isNameValid, isPriceValid , isIdProdValid;
        int newPrice = -1;
        int cnt = 0, idProd=-1;
        String name;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(Constants.MESSAGE_ENTER_PR_ID_9);
            try {
                idProd = scanner.nextInt();
                if(idProd==0){
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println(Constants.MESSAGE_ERROR_NUMBER_2);
                scanner.nextLine();
            }
            isIdProdValid = Validation.validateID(idProd);
        }
        while (!isIdProdValid);

        do {
            System.out.println("Please enter the new price: ");
            try {
                newPrice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Constants.MESSAGE_ERROR_NUMBER_2);
                scanner.nextLine();
            }
            isPriceValid = Validation.validateNumber(newPrice);
        }
        while (!isPriceValid);

//        System.out.println("Will modify the following product: ");
//        System.out.println("Product Name:" + name);
//        System.out.println("Product ID  :" + idProd);
//        System.out.println("New Price   :" + newPrice);

        admin.modifyPrice(idProd, newPrice);
    }

    public void modifyProductDescription(Admin admin){
        String newDescription;
        int idProduct = -1;


        System.out.println(Constants.MESSAGE_ENTER_PR_ID_9);
        idProduct = Validation.readANumber(ValidationType.ID);
        if (idProduct==0){
            return;
        }

        System.out.println("Please enter the new description: ");
        newDescription = Validation.readAString(ValidationType.DETAILS);

        admin.modifyDescription(idProduct, newDescription);
    }

    public void removeProduct(Admin admin){
        int id = -1;

        System.out.println(Constants.MESSAGE_ENTER_PR_ID_9);
        id = Validation.readANumber(ValidationType.ID);
        if(id==0){
            return;
        }

        admin.removeProductById(id);

    }

    public void removePersonByAdmin(Admin admin){
        int personId=-1;
        System.out.println("Please enter the id's person you want to remove from the database or press '0' to return to previous Menu:");
        personId = Validation.readANumber(ValidationType.ID);
        if(personId==0){
            return;
        }
        admin.removePerson(personId);

    }

    public void addNewAdmin(Admin admin) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        Admin newAdmin = new Admin();
        newAdmin.populateDetails();
        admin.addAnewPerson( newAdmin);
    }

    public void modifyProductDiscount(Admin admin){
        int newDiscount = -1;
        int idP=-1;


        System.out.println(Constants.MESSAGE_ENTER_PR_ID_9);
        idP = Validation.readANumber(ValidationType.ID);
        if (idP==0){
            return;
        }

        System.out.println("Please enter the new discount (0-100) : ");
        newDiscount = Validation.readANumber(ValidationType.DISCOUNT);

        admin.modifyDiscount(idP, newDiscount);

    }

}
