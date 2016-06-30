package lt.tieto.angular_spring_rest_demo.utils.repository.model;

import org.springframework.data.domain.Persistable;

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
