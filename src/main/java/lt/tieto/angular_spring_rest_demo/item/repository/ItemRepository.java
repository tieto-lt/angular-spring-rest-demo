package lt.tieto.angular_spring_rest_demo.item.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.angular_spring_rest_demo.core.repository.BaseRepository;
import lt.tieto.angular_spring_rest_demo.item.repository.model.ItemDb;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository extends BaseRepository<ItemDb> {

    private static final RowMapper<ItemDb> ROW_MAPPER = (rs, rowNum) ->
            ItemDb.builder()
                  .name(rs.getString("name"))
                  .quantity(rs.getInt("quantity"))
                  .size(rs.getString("size"))
                  .id(rs.getLong("id"))
                  .build();

    private static final RowUnmapper<ItemDb> ROW_UNMAPPER = itemDb -> mapOf(
            "id", itemDb.getId(),
            "name", itemDb.getName(),
            "quantity", itemDb.getQuantity(),
            "size", itemDb.getSize()
    );

    public ItemRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "items", "id");
    }
}
