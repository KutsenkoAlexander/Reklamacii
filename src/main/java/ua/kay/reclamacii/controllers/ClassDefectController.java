package ua.kay.reclamacii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.reclamacii.models.SprClassDefect;
import ua.kay.reclamacii.services.ClassDefectService;

import java.util.List;

@RestController
@RequestMapping("/class_defect")
public class ClassDefectController {
    @Autowired
    ClassDefectService classDefectService;

    @RequestMapping("/all")
    public List<SprClassDefect> findAllClassDefects(){
        return classDefectService.getAll();
    }

    @RequestMapping("/{id}")
    public SprClassDefect findClassDefectById(@PathVariable("id") Long id){
        return classDefectService.getById(id);
    }
}
