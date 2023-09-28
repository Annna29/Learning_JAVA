package products;

import jakarta.persistence.Entity;
import util.Category;
import util.Constants;
import util.Validation;
import util.ValidationType;

@Entity
public class AnimalFood extends Product{

   // private int numberOfKg;
    private String composition;

    public AnimalFood() {
    }

    public AnimalFood(Category category, int id, String name, String description, int price, int discount, int countPopularity,  String composition) {
        super(category, id, name, description, price, discount, countPopularity);

        this.composition = composition;
    }

    public AnimalFood(Category category, String name, String description, int price, int discount, int countPopularity,  String composition) {
        super(category, name, description, price, discount, countPopularity);

        this.composition = composition;
    }

//    public int getNumberOfKg() {
//        return numberOfKg;
//    }
//
//    public void setNumberOfKg(int numberOfKg) {
//        this.numberOfKg = numberOfKg;
//    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    @Override
    public void populateProduct() {
        super.populateProduct();
        setCategory(Category.ANIMALE);

//
//        System.out.println("Enter the number of kg (choose between 1-20 ) : ");
//        numberOfKg = Validation.readANumber(ValidationType.NUMBER_KG);

        System.out.println("Enter the composition :");
        composition = Validation.readAString(ValidationType.DETAILS);

        System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
    }

    @Override
    public String toString() {
        return "AnimalFood{" +
                "category=" + getCategory() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", priceWithDiscount=" + getPriceWithDiscount() +
                ", discount=" + getDiscount() +
                ", countPopularity=" + getCountPopularity() +
                ", composition=" + composition +
                '}';
    }
}
