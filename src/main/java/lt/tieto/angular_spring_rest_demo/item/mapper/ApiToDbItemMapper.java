package lt.tieto.angular_spring_rest_demo.item.mapper;

import lt.tieto.angular_spring_rest_demo.item.model.Item;
import lt.tieto.angular_spring_rest_demo.item.repository.model.ItemDb;

public class ApiToDbItemMapper {

    public static Item from(ItemDb db) {
        return Item.builder()
                   .id(db.getId())
                   .name(db.getName())
                   .quantity(db.getQuantity())
                   .size(db.getSize())
                   .build();
    }

    public static ItemDb to(Long id, Item api) {
        return ItemDb.builder()
                     .id(id)
                     .name(api.getName())
                     .quantity(api.getQuantity())
                     .size(api.getSize())
                     .build();
    }

    public static ItemDb to(Item api) {
        return to(api.getId(), api);
    }
}
