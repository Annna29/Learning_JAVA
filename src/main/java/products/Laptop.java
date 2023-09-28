package products;

import jakarta.persistence.Entity;
import util.Category;
import util.Constants;
import util.Validation;
import util.ValidationType;

@Entity
public class Laptop extends Product{

    private String type;
    public String operatingSystem;
  //  private String processor;

    public Laptop() {
    }

    public Laptop(Category category, int id, String name, String description, int price, int discount, int countPopularity) {
        super(category, id, name, description, price, discount, countPopularity);
    }

    public Laptop(Category category, int id, String name, String description, int price, int discount, int countPopularity, String type, String procesor) {
        super(category, id, name, description, price, discount, countPopularity);
        this.type = type;
       // this.processor = procesor;
    }

    public Laptop(Category category, String name, String description, int price, int discount, int countPopularity, String type,String operatingSystem) {
        super(category, name, description, price, discount, countPopularity);
        this.type = type;
        this.operatingSystem=operatingSystem;

    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public String getProcessor() {
//        return processor;
//    }
//
//    public void setProcessor(String processor) {
//        this.processor = processor;
//    }

    @Override
    public void populateProduct(){

        super.populateProduct();
        setCategory(Category.ELECTRONICE);

        System.out.println("Enter the type  : ");
        type = Validation.readAString(ValidationType.DETAILS);

//        System.out.println("Enter the processor type :");
//        processor = Validation.readAString(ValidationType.PROCESSOR);

        System.out.println("Enter the operating system : ");
        operatingSystem = Validation.readAString(ValidationType.DETAILS);

        System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);

    }

    @Override
    public String toString() {
        return  "Laptop{" +
                "category=" + getCategory() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", priceWithDiscount=" + getPriceWithDiscount() +
                ", discount=" + getDiscount() +
                ", countPopularity=" + getCountPopularity() +
                ", operatingSystem=" + operatingSystem +
                ", type=" + type +
                '}';
    }
}
