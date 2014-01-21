package by.premiya.olga.project.controller;

import by.premiya.olga.project.dao.ProductionDao;
import by.premiya.olga.project.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Vlad Abramov
 */
@Controller
public class ProductionController {

    @Autowired
    private ProductionDao productionDao;

    @RequestMapping(value = "/show/{product}", method = RequestMethod.GET)
    public String getProduct(ModelMap model, @PathVariable String product) {
        model.put("products", productionDao.getProducts(product));
        model.put("productName", product);
        return Pages.SHOW_PAGE;
    }
}
