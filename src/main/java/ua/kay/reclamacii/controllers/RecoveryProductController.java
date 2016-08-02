package ua.kay.reclamacii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.reclamacii.models.RecoveryProduct;
import ua.kay.reclamacii.services.RecoveryProductService;

import java.util.List;

@RestController
@RequestMapping("/recovery_product")
public class RecoveryProductController {
    @Autowired
    RecoveryProductService recoveryProductService;

    @RequestMapping("/all")
    public List<RecoveryProduct> findAllRecoveryProducts(){
        return recoveryProductService.getAll();
    }

    @RequestMapping("/{id}")
    public RecoveryProduct findRecoveryProductById(@PathVariable("id") Long id){
        return recoveryProductService.getById(id);
    }
}
