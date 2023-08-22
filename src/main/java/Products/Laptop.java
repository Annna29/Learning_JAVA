package Products;

import Util.Category;
import Util.Constants;
import Util.Validation;
import Util.ValidationType;

public class Laptop extends Product{

    private String type;
    private String processor;

    public Laptop() {
    }

    public Laptop(Category category, int id, String name, String description, int price, int discount, int countPopularity) {
        super(category, id, name, description, price, discount, countPopularity);
    }

    public Laptop(Category category, int id, String name, String description, int price, int discount, int countPopularity, String type, String procesor) {
        super(category, id, name, description, price, discount, countPopularity);
        this.type = type;
        this.processor = procesor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public void populateProduct(){

        super.populateProduct();

        System.out.println("Enter the type  : ");
        type = Validation.readAString(ValidationType.DETAILS);

        System.out.println("Enter the processor type :");
        processor = Validation.readAString(ValidationType.PROCESSOR);

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
                ", discount=" + getDiscount() +
                ", countPopularity=" + getCountPopularity() +
                ", procesor=" + processor +
                ", type=" +type+
                '}';
    }
}
