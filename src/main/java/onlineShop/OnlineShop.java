package onlineShop;
import db.DB_Keys;
import db.DB_PRODUCTS;
import db.DB_Persons;
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
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OnlineShop {
    // Temp. DataBase handles
    // TODO: They will be replaced with adapters which will
    //       interact with the DB.
    DB_Keys db_k;
    DB_Persons db_p;
    DB_PRODUCTS db_prod;

    Person currentPerson = new Person();

    // Constructor
    public OnlineShop() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        // Create DB objects
        db_k    = new DB_Keys();
        db_p    = new DB_Persons();
        db_prod = new DB_PRODUCTS();

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
        db_k.addKeys(username, pair);
        encryptedPass = RSA.applyRSAEncryptingData(pass, db_k.getPublicKeyForUser(username));
        db_p.addPersons(new User(username, encryptedPass));
    }

    // Manually Add Admin Function
    public void manualAddAdmin(String username, String pass) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        byte[] encryptedPass;

        KeyPair pair = RSA.genRSAKeys();
        db_k.addKeys(username, pair);
        encryptedPass = RSA.applyRSAEncryptingData(pass, db_k.getPublicKeyForUser(username));
        db_p.addPersons(new Admin(username, encryptedPass));
    }

    public void addProducts(){

        db_prod.getListOfProduct().add(new Product(Category.ELECTRONICE,1,"Dell","Powerfull laptop",3500,5,1));
        db_prod.getListOfProduct().add(new Laptop(Category.ELECTRONICE,2,"Dell2","Powerfull for gaming",4800,0,5));
        db_prod.getListOfProduct().add(new Laptop(Category.ELECTRONICE,3,"Dell3","Powerfull for gaming",4800,7,11));
        db_prod.getListOfProduct().add(new Laptop(Category.ELECTRONICE,4,"Dell4","Powerfull for gaming",4800,0,12));
        db_prod.getListOfProduct().add(new Laptop(Category.ELECTRONICE,5,"Dell5","Powerfull for gaming",4800,0,1));
        db_prod.getListOfProduct().add(new Laptop(Category.ELECTRONICE,6,"Dell6","Powerfull for gaming",4800,78,0));
        db_prod.getListOfProduct().add(new Laptop(Category.ELECTRONICE,7,"Lenovo","Powerfull for gaming",4800,0,4));
        db_prod.getListOfProduct().add(new Product(Category.ALIMENTE,8,"Lapte","delicious",35,0,5));
        db_prod.getListOfProduct().add(new Product(Category.ALIMENTE,9,"Cafea"," tasty almond",50,0,1));
        db_prod.getListOfProduct().add(new Product(Category.ELECTRONICE,10,"Laptop ASUS","Powerfull laptop",3500,0,0));
        db_prod.getListOfProduct().add(new Product(Category.ALIMENTE,11,"Dellicious Almond Chocolate"," tasty almond",50,0,0));


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

        boolean isUsernameValid;
        boolean isUsernameInDB = false;
        char[] pass;

        Scanner scanner = new Scanner(System.in);

        System.out.println("-- SIGN UP MENU --");

        do{
            System.out.println("Enter username: ");
            newUser.setUsername(scanner.nextLine());

            isUsernameValid = Validation.validateDetails(newUser.getUsername(), Constants.USERNAME_PASSWORD_LENGTH);

            if(!isUsernameValid) {
                System.out.println("Username and password must be at least 4 characters !");
                System.out.println(Constants.MESSAGE_CANCEL_ACTION_3);

                try {
                    int ans = scanner.nextInt();
                    if(ans==9)
                        return;
                }
                catch (InputMismatchException e){
                    System.out.println(Constants.MESSAGE_ERROR_NUMBER_2);
                    scanner.nextLine();
                    return;
                }

            } else {
                isUsernameInDB = db_p.getMapPersons().containsKey(newUser.getUsername());
                if(isUsernameInDB) {
                    System.out.println("This username is already used...Please try another one ! ");
                    System.out.println(Constants.MESSAGE_CANCEL_ACTION_3);
                    try {
                        int ans = scanner.nextInt();
                        if (ans == 9)
                            return;
                    }catch (InputMismatchException e){
                        System.out.println(Constants.MESSAGE_ERROR_NUMBER_2);
                        scanner.nextLine();
                        return;
                    }
                }
            }
        } while (isUsernameInDB || !isUsernameValid);

        boolean isPasswordValid;

        do {
//            System.out.println("Enter password: ");
//            newPerson.setPassword(scanner.nextLine());

            Console console = System.console();
            pass = console.readPassword("Enter password: ");
            //newPerson.setPassword( String.copyValueOf(pass));
            newPersonPass = String.copyValueOf(pass);

            isPasswordValid = Validation.validatePassword(newPersonPass, Constants.USERNAME_PASSWORD_LENGTH);
            if(!isPasswordValid){
                System.out.println("Password must contains a special character: $,%,@,#. \nPassword and username must be at least 4 characters ");
                System.out.println(Constants.MESSAGE_CANCEL_ACTION_3);
                try {
                    int ans = scanner.nextInt();
                    if (ans == 9)
                        return;
                }
                catch(InputMismatchException e){
                    System.out.println(Constants.MESSAGE_ERROR_NUMBER_2);
                    return;
                }
            }
        } while(!isPasswordValid);

        // -- Logic to Encrypt Password -- //

        // Gen Keys and push them into the Key Database.
        db_k.addKeys(newUser.getUsername(), RSA.genRSAKeys());

        byte[] RSAPassword = RSA.applyRSAEncryptingData(newPersonPass, db_k.getPublicKeyForUser(newUser.getUsername()));
       // System.out.println(Base64.getEncoder().encodeToString(RSAPassword));
        newUser.setPassword(RSAPassword);

        //
        System.out.println("You have successfully SignedUp!! :D ");
        newUser.setAccountDetails();
        db_p.addPersons(newUser);
        System.out.println("Well done! :)");
        System.out.println("Now let's go shopping!! :D ");

    }

    public Person logIn () throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, IOException, BadPaddingException, InvalidKeyException {
        String username,password;
        String decryptedPass;

        Scanner scanner = new Scanner(System.in);
        boolean ok = false;
        char[] pass;

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

        if(db_p.getMapPersons().containsKey(username)) {
            // Fetch keys in order to decrypt the pass.
            PrivateKey privateKey = db_k.getPrivateKeyForUser(username);
            decryptedPass = RSA.applyRSADecryptingData(db_p.getMapPersons().get(username).getPassword(), privateKey);
         //   System.out.println("Decrypted Pass: " + decryptedPass);
            if(decryptedPass.equals(password)){
                System.out.println("You have successfully log in ! ");
                return db_p.getMapPersons().get(username);
            } else {
                System.out.println("Wrong password!");
            }
        } else {
            System.out.println("User not registered!");
        }

        return null;

//        for (Map.Entry<String, Person> el : db_p.getMapPersons().entrySet()) {
//            if ((username.equals(el.getKey())) && (password.equals(RSA.applyRSADecryptingData(el.getValue().getPassword())))) {
//                ok = true;
//            }
//            if (ok) {
//                System.out.println("You have successfully log in ! ");
//                return el.getValue();
//            }
//
//        }
//
//        if(!ok) {
//            System.out.println("Something is wrong...please retry ! ");
//
//        }
//        return null;
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
                    OnlineShopMenu.printFilterProductsByTextMenu();
                    submenuOpt = OnlineShopMenu.readMenuOption();
                    filterProductsByText(user, submenuOpt);
                    break;

                case 2:
                    OnlineShopMenu.printDisplayProductByPriceMenu();
                    submenuOpt = OnlineShopMenu.readMenuOption();
                    sortProductByPrice(user, submenuOpt);
                    break;

                case 3:
                    OnlineShopMenu.printDisplayProductByPopularityMenu();
                    submenuOpt = OnlineShopMenu.readMenuOption();
                    sortProductByPopularity(user, submenuOpt);
                    break;

                case 4:
                    OnlineShopMenu.printDisplayProductByNameMenu();
                    submenuOpt = OnlineShopMenu.readMenuOption();
                    sortProductByName(user, submenuOpt);
                    break;

                case 5:
                      user.addMoney();
                      break;

                case 6:
                    // adding products to your shopping cart and after that you can easily buy them
                    shoppingAction(user);

                      break;

                case 7:
                    OnlineShopMenu.printDisplayProductByDiscountPriceMenu();
                    submenuOpt = OnlineShopMenu.readMenuOption();
                    sortProductByDiscountPrice(user, submenuOpt);
                    break;

                case 8: runShop = false;
                        break;

                default: System.out.println(Constants.MESSAGE_INVALID_OPTION_1);
                         break;
            }
        } while(runShop);

        return null;
    }

    public void filterProductsByText(Person person, int option) {
        String filterText;
        if(option == 1) {

            System.out.println(Constants.MESSAGE_ENTER_TEXT_4);
            filterText=Validation.readAString(ValidationType.SEARCHING_TEXT);
            person.filterAllProductsByText(filterText, db_prod.getListOfProduct());

        }
        else if(option == 2) {
            System.out.println(Constants.MESSAGE_ENTER_TEXT_4);
            filterText=Validation.readAString(ValidationType.SEARCHING_TEXT);
            System.out.println("Please choose a category: ");
            System.out.println("SELECT the products's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");

            int cat=-1;
            cat = OnlineShopMenu.readMenuOption();

            switch (cat) {
                case 1:
                    person.filterOneCategoryProductsByText(filterText, Category.ANIMALE, db_prod.getListOfProduct());
                    break;
                case 2:
                    person.filterOneCategoryProductsByText(filterText, Category.VESTIMENTATIE, db_prod.getListOfProduct());
                    break;
                case 3:
                    person.filterOneCategoryProductsByText(filterText, Category.COSMETICE, db_prod.getListOfProduct());
                    break;
                case 4:
                    person.filterOneCategoryProductsByText(filterText, Category.ALIMENTE, db_prod.getListOfProduct());
                    break;
                case 5:
                    person.filterOneCategoryProductsByText(filterText, Category.ELECTRONICE, db_prod.getListOfProduct());
                    break;
                default :
                    System.out.println(Constants.MESSAGE_INVALID_CATEGORY_5);
                    break;
            }
        } else {
            System.out.println(Constants.MESSAGE_INVALID_OPTION_6);
        }
    }

    public void sortProductByPrice(Person person, int optionPrice) {

        if(optionPrice == 1)
            person.comparingAllProductsByPriceAscending(db_prod.getListOfProduct());

        if(optionPrice == 2)
            person.comparingAllProductsByPriceDescending(db_prod.getListOfProduct());

        if(optionPrice == 3) {
            System.out.println("SELECT the product's category you want to search:\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
            int chosenOp = OnlineShopMenu.readMenuOption();
            person.comparingOneCategoryProductsByPriceAscending(chosenOp, db_prod.getListOfProduct());
        }

        if(optionPrice == 4) {
            System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
            int chosenOp = OnlineShopMenu.readMenuOption();
            person.comparingOneCategoryProductsByPriceDescending(chosenOp, db_prod.getListOfProduct());
        }

        if(optionPrice > 4 || optionPrice < 1){
            System.out.println(Constants.MESSAGE_INVALID_OPTION_6);
        }
    }

    public void sortProductByPopularity(Person person, int optionPopularity) {

        if(optionPopularity == 1)
            person.comparingAllProductsByPopularityAscending(db_prod.getListOfProduct());

        if(optionPopularity == 2)
            person.comparingAllProductsByPopularityDescending(db_prod.getListOfProduct());

        if(optionPopularity == 3){
            System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
            int chosenOp = OnlineShopMenu.readMenuOption();
            person.comparingOneCategoryProductsByPopularityAscending(chosenOp, db_prod.getListOfProduct());
        }

        if(optionPopularity == 4){
            System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
            int chosenOp = OnlineShopMenu.readMenuOption();
            person.comparingOneCategoryProductsByPopularityDescending(chosenOp, db_prod.getListOfProduct());
        }

        if(optionPopularity > 4 || optionPopularity < 1){
            System.out.println(Constants.MESSAGE_INVALID_OPTION_6);
        }
    }

    public void sortProductByName(Person person, int optionNameMenu){
        if(optionNameMenu == 1)
            person.comparingAllProductsByNameAscending(db_prod.getListOfProduct());

        if(optionNameMenu == 2)
            person.comparingAllProductsByNameDescending(db_prod.getListOfProduct());

        if(optionNameMenu == 3){
            System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
            int chosenOp = OnlineShopMenu.readMenuOption();
            person.comparingOneCategoryProductsByNameAscending(chosenOp, db_prod.getListOfProduct());
        }

        if(optionNameMenu == 4){
            System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
            int chosenOp = OnlineShopMenu.readMenuOption();
            person.comparingOneCategoryProductsByNameDescending(chosenOp, db_prod.getListOfProduct());
        }

        if(optionNameMenu > 4 || optionNameMenu < 1){
            System.out.println(Constants.MESSAGE_INVALID_OPTION_6);
        }
    }

    public void  sortProductByDiscountPrice (Person person, int optionDiscountPrice){
        if(optionDiscountPrice == 1)
            person.comparingAllProductsByDiscountPriceAscending(db_prod.getListOfProduct());

        if(optionDiscountPrice == 2)
            person.comparingAllProductsByDiscountPriceDescending(db_prod.getListOfProduct());

        if(optionDiscountPrice == 3){
            System.out.println("SELECT the product's category you want to search:\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
            int chosenOp = OnlineShopMenu.readMenuOption();
            person.comparingOneCategoryProductsByDiscountPriceAscending(chosenOp, db_prod.getListOfProduct());
        }

        if(optionDiscountPrice == 4){
            System.out.println("SELECT the product's category you want to search :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Electronics");
            int chosenOp = OnlineShopMenu.readMenuOption();
            person.comparingOneCategoryProductsByDiscountPriceDescending(chosenOp, db_prod.getListOfProduct());
        }

        if(optionDiscountPrice > 4 || optionDiscountPrice  < 1){
            System.out.println(Constants.MESSAGE_INVALID_OPTION_6);
        }
    }

    public void shoppingAction(User user){
        String prName;
        System.out.println("Please enter the name of the product you want to add to your cart: ");
        prName = Validation.readAString(ValidationType.DETAILS);
        user.addToShoppingBag(prName, db_prod.getListOfProduct());

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
                    System.out.println("SELECT the category of product you want to add :\n1. AnimalFood\n2. Clothes\n3. Dermatocosmetics\n4. Food and drinks\n5. Laptop\n6. Mobile phone");
                    submenuOpt = OnlineShopMenu.readMenuOption();
                    manuallyAddProducts(admin, submenuOpt);
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
                    admin.seeAllProducts(db_prod.getListOfProduct());
                    admin.seeAllProductsTable(db_prod.getListOfProduct());
                    break;

                case 6:
                    admin.seeAllPersons(db_p.getMapPersons());
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

    public void manuallyAddProducts(Admin admin, int category){
        if (category == 1)
            admin.addProducts(new AnimalFood(), db_prod.getListOfProduct());
        if (category == 2)
            admin.addProducts(new Clothes(), db_prod.getListOfProduct());
        if (category == 3)
            admin.addProducts(new Dermatocosmetics(), db_prod.getListOfProduct());
        if (category == 4)
            admin.addProducts(new FoodAndDrinks(), db_prod.getListOfProduct());
        if (category == 5)
            admin.addProducts(new Laptop(), db_prod.getListOfProduct());
        if (category == 6)
            admin.addProducts(new MobilePhone(), db_prod.getListOfProduct());

        if (category > 6)
            System.out.println(Constants.MESSAGE_INVALID_OPTION_7);
    }

    public void modifyProductPrice(Admin admin) {

        boolean isNameValid, isPriceValid , isIdProdValid;
        int newPrice = -1;
        int cnt = 0, idProd=-1;
        String name;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(Constants.MESSAGE_ENTER_PR_NAME_8);
            name = scanner.nextLine();
            isNameValid = Validation.validateDetails(name, Constants.DETAILS_LENGTH);
        }
        while (!isNameValid);

        do {
            System.out.println(Constants.MESSAGE_ENTER_PR_ID_9);
            try {
                idProd = scanner.nextInt();
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

        System.out.println("Will modify the following product: ");
        System.out.println("Product Name:" + name);
        System.out.println("Product ID  :" + idProd);
        System.out.println("New Price   :" + newPrice);

        admin.modifyPrice(idProd, name, newPrice, db_prod.getListOfProduct());
    }

    public void modifyProductDescription(Admin admin){
        String productname, newDescription;
        int idProduct = -1;

        System.out.println(Constants.MESSAGE_ENTER_PR_NAME_8);
        productname = Validation.readAString(ValidationType.DETAILS);

        System.out.println(Constants.MESSAGE_ENTER_PR_ID_9);
        idProduct = Validation.readANumber(ValidationType.ID);

        System.out.println("Please enter the new description: ");
        newDescription = Validation.readAString(ValidationType.DETAILS);

        admin.modifyDescription(idProduct,productname, newDescription, db_prod.getListOfProduct());
    }

    public void removeProduct(Admin admin){
        int id = -1;
        String productName;

        System.out.println(Constants.MESSAGE_ENTER_PR_NAME_8);
        productName = Validation.readAString(ValidationType.DETAILS);

        System.out.println(Constants.MESSAGE_ENTER_PR_ID_9);
        id = Validation.readANumber(ValidationType.ID);

        admin.removeProductById(productName, id, db_prod.getListOfProduct());

    }
    public void removePersonByAdmin(Admin admin){
        int personId=-1;
        System.out.println("Please enter the id's person you want to remove from the database:");
        personId = Validation.readANumber(ValidationType.ID);
        admin.removePerson(personId, db_p.getMapPersons());

    }
    public void addNewAdmin(Admin admin) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        Admin newAdmin = new Admin();
        newAdmin.populateDetails(db_k);
        admin.addAnewPerson(db_p.getMapPersons(), newAdmin);
    }

    public void modifyProductDiscount(Admin admin){
        int newDiscount = -1;
        int idP=-1;
        String nameP;

        System.out.println(Constants.MESSAGE_ENTER_PR_NAME_8);
        nameP = Validation.readAString(ValidationType.DETAILS);

        System.out.println(Constants.MESSAGE_ENTER_PR_ID_9);
        idP = Validation.readANumber(ValidationType.ID);

        System.out.println("Please enter the new discount (0-100) : ");
        newDiscount = Validation.readANumber(ValidationType.DISCOUNT);

        admin.modifyDiscount(idP,nameP, newDiscount, db_prod.getListOfProduct());

    }



}
