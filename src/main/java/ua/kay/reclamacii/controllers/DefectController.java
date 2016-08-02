package ua.kay.reclamacii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.reclamacii.models.Defect;
import ua.kay.reclamacii.services.DefectService;

import java.util.List;

@RestController
@RequestMapping("/defect")
public class DefectController {
    @Autowired
    DefectService defectService;

    @RequestMapping("/all")
    public List<Defect> findAllDefects(){
        return defectService.getAll();
    }

    @RequestMapping("/{id}")
    public Defect findDefectById(@PathVariable("id") Long id){
        return defectService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDefect(@PathVariable Long id) { defectService.delete(id); }
}
