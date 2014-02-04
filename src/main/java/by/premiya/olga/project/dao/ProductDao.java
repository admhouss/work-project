package by.premiya.olga.project.dao;

import by.premiya.olga.project.entity.Accumulator;
import by.premiya.olga.project.entity.Wheel;

import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
public interface ProductDao {

    void save(Object product);

    void delete(Object product);

    Wheel getWheelById(Integer id);

    Accumulator getAccumulatorById(Integer id);

    Object getProductByModel(String productName, String model);

    List getProducts(String name);

    List<Wheel> getWheels(Map<String, String> searchParams);

    List<String> getWheelModels();

    List<String> getAccumulatorModels();

    List<String> getRadiatorModels();

    void update(Object product);

    Object getProductByModel(String model);
}
