package by.premiya.olga.project.controller;

import by.premiya.olga.project.dao.ProductDao;
import by.premiya.olga.project.service.ProductService;
import by.premiya.olga.project.util.Pages;
import by.premiya.olga.project.util.json.ItemJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Vlad Abramov
 */
@Controller
public class ProductionController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/show/{product}", method = RequestMethod.GET)
    public String getProduct(ModelMap model, @PathVariable String product) {
        model.put("products", productService.getProducts(product));
        model.put("productName", product);
        return Pages.SHOW_PAGE;
    }

    @RequestMapping(value = "/get/all/models", method = RequestMethod.POST)
    public @ResponseBody List<String> getModels() {
        List<String> list = productService.getAllModels();
        return list;
    }

    @RequestMapping(value = "/get/{model}/for/show", method = RequestMethod.POST)
    public @ResponseBody ItemJSON getItemForShow(@PathVariable String model) {
        return productService.getItemForShow(model);
    }
}
