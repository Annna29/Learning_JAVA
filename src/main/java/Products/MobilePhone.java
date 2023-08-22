package Products;

import Util.Category;
import Util.Constants;
import Util.Validation;
import Util.ValidationType;



public class MobilePhone extends Product {
    public String operatingSystem;
   // public int ramMemory;

    public MobilePhone() {

    }
    public MobilePhone(Category category, int id, String name, String description, int price, int discount, int countPopularity, String operatingSystem) {
        super(category, id, name, description, price, discount, countPopularity);
        this.operatingSystem = operatingSystem;
    }



    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

//    public int getRamMemory() {
//        return ramMemory;
//    }
//
//    public void setRamMemory(int ramMemory) {
//        this.ramMemory = ramMemory;
//    }

    @Override
    public void populateProduct() {
        super.populateProduct();
        System.out.println("Enter the operating system : ");
        operatingSystem = Validation.readAString(ValidationType.DETAILS);
        System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
    }
}
