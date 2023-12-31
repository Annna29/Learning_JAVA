package products;

import jakarta.persistence.Entity;
import util.Category;
import util.Constants;
import util.Validation;
import util.ValidationType;
@Entity
public class Clothes extends Product{

    private String color;
  // private String size;


    public Clothes() {
    }

    public Clothes(Category category, int id, String name, String description, int price, int discount, int countPopularity, String color) {
        super(category, id, name, description, price, discount, countPopularity);
        this.color = color;
    }

    public Clothes(Category category, String name, String description, int price, int discount, int countPopularity, String color) {
        super(category, name, description, price, discount, countPopularity);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    public String getSize() {
//        return size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }

    @Override
    public void populateProduct() {

        super.populateProduct();
        setCategory(Category.VESTIMENTATIE);

        System.out.println("Enter the color : ");
        color = Validation.readAString(ValidationType.DETAILS);

        System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "category=" + getCategory() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", priceWithDiscount=" + getPriceWithDiscount() +
                ", discount=" + getDiscount() +
                ", countPopularity=" + getCountPopularity() +
                ", color=" + color +
                '}';
    }
}
