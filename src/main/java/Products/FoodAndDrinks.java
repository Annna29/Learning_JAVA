package Products;

import Util.Category;
import Util.Constants;
import Util.Validation;
import Util.ValidationType;


public class FoodAndDrinks extends Product{

    private int numberOfkg;
    private String flavor;


    public FoodAndDrinks() {
    }

    public FoodAndDrinks(Category category, int id, String name, String description, int price, int discount, int countPopularity, int numberOfkg, String flavor) {
        super(category, id, name, description, price, discount, countPopularity);
        this.numberOfkg = numberOfkg;
        this.flavor = flavor;
    }

    public int getNumberOfkg() {
        return numberOfkg;
    }

    public void setNumberOfkg(int numberOfkg) {
        this.numberOfkg = numberOfkg;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public void populateProduct(){
        super.populateProduct();
        System.out.println("Enter the number of kg (choose between 1-20 ): ");
        numberOfkg = Validation.readANumber(ValidationType.NUMBER_KG);

        System.out.println("Enter the flavor :");
        flavor = Validation.readAString(ValidationType.DETAILS);

        System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
    }

}
