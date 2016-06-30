package lt.tieto.angular_spring_rest_demo.item.repository.model;

import lt.tieto.angular_spring_rest_demo.utils.repository.model.DbModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ItemDb extends DbModel {

    private String name;
    private Integer quantity;
    private String size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("quantity", quantity)
                .append("size", size)
                .toString();
    }
}
