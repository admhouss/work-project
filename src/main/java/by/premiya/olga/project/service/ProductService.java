package by.premiya.olga.project.service;

import by.premiya.olga.project.entity.Wheel;
import by.premiya.olga.project.util.json.ItemJSON;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
public interface ProductService {
    List getProducts(String name);

    Object getProductByModel(String productName, String model);

    List<Wheel> getWheels(Map<String, String> searchParams);

    void addNewProduct(String productName, ItemJSON properties, String model);

    List<String> getAllModels();

    void updateProduct(Object product);

    ItemJSON getItem(String productName, String model);

    Object getById(String productName, Integer id);

    boolean removeItem(String productName, String id);

    String getProductName(String model);

    ItemJSON getItemForShow(String model);
}
