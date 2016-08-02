package ua.kay.reclamacii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.reclamacii.models.SprProductType;
import ua.kay.reclamacii.services.ProductTypeService;

import java.util.List;

@RestController
@RequestMapping("/product_type")
public class ProductTypeController {
    @Autowired
    ProductTypeService productTypeService;

    @RequestMapping("/all")
    public List<SprProductType> findAllProductTypes(){
        return productTypeService.getAll();
    }

    @RequestMapping("/{id}")
    public SprProductType findProductTypeById(@PathVariable("id") Long id){
        return productTypeService.getById(id);
    }
}
