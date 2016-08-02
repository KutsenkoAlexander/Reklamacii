package ua.kay.reclamacii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.reclamacii.models.SprExecutor;
import ua.kay.reclamacii.services.ExecutorService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/executor")
public class ExecutorController {
    @Autowired
    ExecutorService executorService;

    @RequestMapping("/all")
    public List<SprExecutor> findAllExecutors(){
        return executorService.getAll();
    }

    @RequestMapping("/{id}")
    public SprExecutor findExecutorById(@PathVariable("id") Long id){
        return executorService.getById(id);
    }

    @RequestMapping("/search")
    public List<SprExecutor> findExecutorByTypedName(@PathParam(value = "params") String params){
        return executorService.findExecutorByTypedName(params);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SprExecutor saveExecutor(@RequestBody SprExecutor executor) {
        return executorService.save(executor);
    }
}
