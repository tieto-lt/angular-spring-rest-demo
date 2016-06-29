package lt.tieto.angular_spring_rest_demo.item.service;

import java.util.List;
import java.util.stream.Collectors;

import lt.tieto.angular_spring_rest_demo.core.exception.DataNotFoundException;
import lt.tieto.angular_spring_rest_demo.item.mapper.ApiToDbItemMapper;
import lt.tieto.angular_spring_rest_demo.item.model.Item;
import lt.tieto.angular_spring_rest_demo.item.repository.ItemRepository;
import lt.tieto.angular_spring_rest_demo.item.repository.model.ItemDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Transactional(readOnly = true)
    public Item get(Long id) {
        ItemDb item = repository.findOne(id);
        if (item != null) {
            return ApiToDbItemMapper.from(item);
        } else {
            throw new DataNotFoundException("Item with id " + id + " not found");
        }
    }

    @Transactional(readOnly = true)
    public List<Item> all() {
        return repository.findAll().stream().map(ApiToDbItemMapper::from).collect(Collectors.toList());
    }

    @Transactional
    public Item createOrUpdateItem(Long id, Item item) {
        if (repository.exists(id)) {
            ItemDb updated = repository.update(ApiToDbItemMapper.to(id, item));
            return ApiToDbItemMapper.from(updated);
        } else {
            ItemDb created = repository.create(ApiToDbItemMapper.to(id, item));
            return ApiToDbItemMapper.from(created);
        }
    }

    @Transactional
    public Item createItem(Item item) {
        return ApiToDbItemMapper.from(repository.create(ApiToDbItemMapper.to(item)));
    }

    @Transactional
    public void remove(Long id) {
        if (!repository.exists(id)) {
            throw new DataNotFoundException("Item with id " + id + " doesn't exist");
        }
        repository.delete(id);
    }
}
