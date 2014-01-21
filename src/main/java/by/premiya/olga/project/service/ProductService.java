package by.premiya.olga.project.service;

import by.premiya.olga.project.entity.Wheel;

import java.util.List;
import java.util.Map;

/**
 * @author Vlad Abramov
 */
public interface ProductService {
    List getProducts(String name);

    List<Wheel> getWheels(Map<String, String> searchParams);
}
