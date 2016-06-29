package lt.tieto.angular_spring_rest_demo.item.repository.model;

import lombok.Builder;
import lombok.Data;
import lt.tieto.angular_spring_rest_demo.core.repository.model.DbModel;

@Data
public class ItemDb extends DbModel {

    private final String name;
    private final Integer quantity;
    private final String size;

    @Builder
    public ItemDb(Long id, String name, Integer quantity, String size) {
        super(id);
        this.name = name;
        this.quantity = quantity;
        this.size = size;
    }
}
