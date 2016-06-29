package lt.tieto.angular_spring_rest_demo.core.repository.model;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Persistable;

@AllArgsConstructor
public class DbModel implements Persistable<Long> {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
