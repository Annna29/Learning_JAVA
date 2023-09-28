package util;

public enum Category {
    ALIMENTE(1),
    VESTIMENTATIE(2),
    ELECTRONICE(3),
    COSMETICE(4),
    ANIMALE(5),
    UNKNOWN(-1);

    private int id;

    Category(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Category getTheCategory(int id){

        for (Category category:Category.values()) {
            if (category.getId() == id)
                return category;
        }
                return Category.UNKNOWN;

    }
}
