package lt.tieto.angular_spring_rest_demo.item.controller;

import javax.validation.Valid;
import java.util.List;

import lt.tieto.angular_spring_rest_demo.core.controller.BaseController;
import lt.tieto.angular_spring_rest_demo.item.model.Item;
import lt.tieto.angular_spring_rest_demo.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController extends BaseController {

    @Autowired
    private ItemService service;

    @RequestMapping(method = RequestMethod.GET, path = "/api/items/{id}")
    public Item get(@PathVariable Long id) {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/items")
    public List<Item> all() {
        return service.all();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/items/{id}")
    public Item createOrUpdateItem(@PathVariable Long id, @Valid @RequestBody Item item) {
        return service.createOrUpdateItem(id, item);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/items")
    public Item createItem(@RequestBody @Valid Item item) {
        return service.createItem(item);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/items/{id}")
    public void remove(@PathVariable Long id) {
        service.remove(id);
    }
}
