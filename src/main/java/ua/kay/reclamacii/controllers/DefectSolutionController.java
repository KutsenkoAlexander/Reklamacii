package ua.kay.reclamacii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.reclamacii.models.DefectSolution;
import ua.kay.reclamacii.services.DefectSolutionService;

import java.util.List;

@RestController
@RequestMapping("/defect_solution")
public class DefectSolutionController {
    @Autowired
    DefectSolutionService defectSolutionService;

    @RequestMapping("/all")
    public List<DefectSolution> findAllDefectSolutions(){
        return defectSolutionService.getAll();
    }

    @RequestMapping("/{id}")
    public DefectSolution findDefectSolutionById(@PathVariable("id") Long id){
        return defectSolutionService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSolutionDefect(@PathVariable Long id) { defectSolutionService.delete(id); }

    @RequestMapping(method = RequestMethod.POST)
    public List<DefectSolution> saveDefectSolutions(@RequestBody List<DefectSolution> defectSolutionsList){
        return defectSolutionService.saveDefectSolutions(defectSolutionsList);
    }
}
