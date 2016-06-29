package lt.tieto.angular_spring_rest_demo.item.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@JsonDeserialize(builder = Item.ItemBuilder.class)
@Data
@Builder
public class Item {

    private final Long id;

    @NotNull
    private final String name;

    @Min(0)
    private final Integer quantity;

    @NotNull
    @Pattern(regexp="\\d*x\\d*")
    private final String size;

    //For Lombok + Jackson json deserialization to work
    @JsonPOJOBuilder(withPrefix = "")
    public static final class ItemBuilder {}
}
