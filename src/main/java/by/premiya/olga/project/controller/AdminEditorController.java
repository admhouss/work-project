package by.premiya.olga.project.controller;

import by.premiya.olga.project.entity.Accumulator;
import by.premiya.olga.project.entity.Wheel;
import by.premiya.olga.project.service.ProductService;
import by.premiya.olga.project.util.ApplicationContextHolder;
import by.premiya.olga.project.util.Pages;
import by.premiya.olga.project.util.json.EntityPropertiesLoader;
import by.premiya.olga.project.util.json.ItemJSON;
import by.premiya.olga.project.util.json.PropertiesJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author vlad
 */
@Controller
@RequestMapping("/auth/administration/editor")
public class AdminEditorController {

    @Autowired
    private EntityPropertiesLoader propertiesLoader;

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "edit/{productName}/{model}", method = RequestMethod.POST)
    private @ResponseBody
    ItemJSON editProduct(@RequestBody ItemJSON json, @PathVariable String productName, @PathVariable String model) {
        ProductService productService = (ProductService) ApplicationContextHolder.getBean("productServiceImpl");
        productService.addNewProduct(productName, json, model);
        return json;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "edit/{productName}", method = RequestMethod.POST)
    private @ResponseBody
    ItemJSON addNewProduct(@RequestBody ItemJSON json, @PathVariable String productName) {
        ProductService productService = (ProductService) ApplicationContextHolder.getBean("productServiceImpl");
        productService.addNewProduct(productName, json, "");
        return json;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "show/{product}", method = RequestMethod.GET)
    public String getProduct(ModelMap model, @PathVariable String product) {
        ProductService productService = (ProductService) ApplicationContextHolder.getBean("productServiceImpl");
        model.put("products", productService.getProducts(product));
        model.put("productName", product);
        return Pages.ADMIN_SHOW_PAGE;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "get/full/properties/{product}", method = RequestMethod.POST)
    public @ResponseBody PropertiesJSON getProperties(@PathVariable String product) {
        return propertiesLoader.getProperties(product);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "get/product/name/{model}", method = RequestMethod.POST)
    public @ResponseBody String getProductName(@PathVariable String model) {
        ProductService productService = (ProductService) ApplicationContextHolder.getBean("productServiceImpl");
        return productService.getProductName(model);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "get/edit/item/{model}/{productName}", method = RequestMethod.POST)
    public @ResponseBody ItemJSON getEditItem(@PathVariable String model, @PathVariable String productName) {
        ProductService productService = (ProductService) ApplicationContextHolder.getBean("productServiceImpl");
        ItemJSON result = productService.getItem(productName, model);
        result.setObjectProduct(productName);
        return result;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "remove/item/{model}/{productName}", method = RequestMethod.POST)
    public @ResponseBody Boolean removeItem(@PathVariable String model, @PathVariable String productName) {
        ProductService productService = (ProductService) ApplicationContextHolder.getBean("productServiceImpl");
        return productService.removeItem(productName,model);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "show/product/{model}", method = RequestMethod.GET)
    public String getItem(ModelMap modelMap, @PathVariable String model) {
        ProductService productService = (ProductService) ApplicationContextHolder.getBean("productServiceImpl");
        Object product = productService.getProductByModel(productService.getProductName(model), model);
        if (product instanceof Wheel) {
            modelMap.put("wheel", product);
            return Pages.SHOW_WHEEL_PAGE;
        }else if (product instanceof Accumulator) {
            modelMap.put("accumulator", product);
            return Pages.SHOW_WHEEL_PAGE;
        }
        return Pages.REDIRECT + Pages.PAGE_404;
    }
}