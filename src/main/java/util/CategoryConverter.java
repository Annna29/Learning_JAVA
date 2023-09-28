package util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;



@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category,Integer> {
    @Override
    public Integer convertToDatabaseColumn(Category category) {
        return category.getId();
    }

    @Override
    public Category convertToEntityAttribute(Integer id) {

        return Category.getTheCategory(id);

    }
}

