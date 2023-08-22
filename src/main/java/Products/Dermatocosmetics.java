package Products;

import Util.Category;
import Util.Constants;
import Util.Validation;
import Util.ValidationType;

public class Dermatocosmetics extends Product{

    public Dermatocosmetics() {
    }

    public Dermatocosmetics(Category category, int id, String name, String description, int price, int discount, int countPopularity, int volume, String fragrance) {
        super(category, id, name, description, price, discount, countPopularity);
        this.volume = volume;
        this.fragrance = fragrance;
    }

    private int volume;
    private String fragrance;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getFragrance() {
        return fragrance;
    }

    public void setFragrance(String fragrance) {
        this.fragrance = fragrance;
    }

    @Override
    public void populateProduct(){
        super.populateProduct();

        System.out.println("Enter the volume (choose between 10ml-500 ml ): ");
        volume= Validation.readANumber(ValidationType.VOLUME);

        System.out.println("Enter the fragrance :");
        fragrance = Validation.readAString(ValidationType.DETAILS);

        System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
    }
}
