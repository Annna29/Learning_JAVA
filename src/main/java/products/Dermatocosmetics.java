package products;

import jakarta.persistence.Entity;
import util.Category;
import util.Constants;
import util.Validation;
import util.ValidationType;

@Entity
public class Dermatocosmetics extends Product{

    public Dermatocosmetics() {
    }

    public Dermatocosmetics(Category category, int id, String name, String description, int price, int discount, int countPopularity, String fragrance) {
        super(category, id, name, description, price, discount, countPopularity);
       // this.volume = volume;
        this.fragrance = fragrance;
    }

    public Dermatocosmetics(Category category,  String name, String description, int price, int discount, int countPopularity, String fragrance) {
        super(category, name, description, price, discount, countPopularity);
        // this.volume = volume;
        this.fragrance = fragrance;
    }

   // private int volume;
    private String fragrance;

//    public int getVolume() {
//        return volume;
//    }
//
//    public void setVolume(int volume) {
//        this.volume = volume;
//    }

    public String getFragrance() {
        return fragrance;
    }

    public void setFragrance(String fragrance) {
        this.fragrance = fragrance;
    }

    @Override
    public void populateProduct(){
        super.populateProduct();
        setCategory(Category.COSMETICE);

//        System.out.println("Enter the volume (choose between 10ml-500 ml ): ");
//        volume= Validation.readANumber(ValidationType.VOLUME);

        System.out.println("Enter the fragrance :");
        fragrance = Validation.readAString(ValidationType.DETAILS);

        System.out.println(Constants.MESSAGE_SUCCESS_ACTION_10);
    }

    @Override
    public String toString() {
        return "Dermatocosmetics{" +
                "category=" + getCategory() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", priceWithDiscount=" + getPriceWithDiscount() +
                ", discount=" + getDiscount() +
                ", countPopularity=" + getCountPopularity() +
                ", fragrance=" + fragrance +
                '}';
    }
}
