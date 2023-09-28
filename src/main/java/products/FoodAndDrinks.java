package products;

import jakarta.persistence.Entity;
import util.Category;
import util.Constants;
import util.Validation;
import util.ValidationType;

@Entity
public class FoodAndDrinks extends Product{

  //  private int numberOfkg;
    private String flavor;


    public FoodAndDrinks() {
    }

    public FoodAndDrinks(Category category, int id, String name, String description, int price, int discount, int countPopularity, String flavor) {
        super(category, id, name, description, price, discount, countPopularity);
      //  this.numberOfkg = numberOfkg;
        this.flavor = flavor;
    }
    public FoodAndDrinks(Category category, String name, String description, int price, int discount, int countPopularity, String flavor) {
        super(category, name, description, price, discount, countPopularity);
        //  this.numberOfkg = numberOfkg;
        this.flavor = flavor;
    }

//    public int getNumberOfkg() {
//        return numberOfkg;
//    }
//
//    public void setNumberOfkg(int numberOfkg) {
//        this.numberOfkg = numberOfkg;
//    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public void populateProduct(){
        super.populateProduct();
        setCategory(Category.ALIMENTE);
      //  System.out.println("Enter the number of kg (choose between 1-20 ): ");
      //  numberOfkg = Validation.readANumber(ValidationType.NUMBER_KG);

        System.out.println("Enter the flavor :");
        flavor = Validation.readAString(ValidationType.DETAILS);

        System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
    }

    @Override
    public String toString() {
        return "FoodAndDrinks{" +
                "category=" + getCategory() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", priceWithDiscount=" + getPriceWithDiscount() +
                ", discount=" + getDiscount() +
                ", countPopularity=" + getCountPopularity() +
                ", flavor=" + flavor +
                '}';
    }
}
