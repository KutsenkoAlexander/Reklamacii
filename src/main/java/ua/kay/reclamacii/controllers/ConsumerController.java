package ua.kay.reclamacii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kay.reclamacii.models.SprConsumer;
import ua.kay.reclamacii.services.ConsumerService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    ConsumerService consumerService;

    @RequestMapping("/all")
    public List<SprConsumer> findAllConsumers(){
        return consumerService.getAll();
    }

    @RequestMapping("/{id}")
    public SprConsumer findConsumerByIdConsumer(@PathVariable("id") Long id) {
        return consumerService.getById(id);
    }

    @RequestMapping("/search")
    public List<SprConsumer> findConsumerByTypedName(@PathParam(value = "params") String params){
        return consumerService.findConsumerByName(params);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SprConsumer saveConsumer(@RequestBody SprConsumer consumer) {
        return consumerService.save(consumer);
    }
}
