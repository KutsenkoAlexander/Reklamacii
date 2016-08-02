package ua.kay.reclamacii.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.reclamacii.models.Ctp;
import ua.kay.reclamacii.services.CtpService;

import java.util.List;

@RestController
@RequestMapping("/ctp")
public class CtpController {
    @Autowired
    CtpService ctpService;

    @RequestMapping("/all")
    public List<Ctp> findAllCtp(){
        return ctpService.getAll();
    }

    @RequestMapping("/{id}")
    public Ctp findCtpById(@PathVariable("id") Long id){
        return ctpService.getById(id);
    }
}
