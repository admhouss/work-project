package by.premiya.olga.project.controller;

import by.premiya.olga.project.service.ProductService;
import by.premiya.olga.project.util.ApplicationContextHolder;
import by.premiya.olga.project.util.Pages;
import by.premiya.olga.project.util.json.EntityPropertiesLoader;
import by.premiya.olga.project.util.json.NewItemJSON;
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
    @RequestMapping(method = RequestMethod.GET)
    private String adminEditor() {
        return Pages.ADMIN_HOME_PAGE;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "new/{productName}", method = RequestMethod.POST)
    private @ResponseBody NewItemJSON addNewProduct(@RequestBody NewItemJSON json
            , @PathVariable String productName){
        ProductService productService = (ProductService) ApplicationContextHolder.getBean("productServiceImpl");
        productService.addNewProduct(productName, json);
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
    public @ResponseBody PropertiesJSON getProperties(ModelMap model, @PathVariable String product) {
        return propertiesLoader.getProperties(product);
    }
}