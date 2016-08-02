package ua.kay.reclamacii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.kay.reclamacii.exceptions.DateFromException;
import ua.kay.reclamacii.exceptions.DateSetParserException;
import ua.kay.reclamacii.models.Record;
import ua.kay.reclamacii.services.RecordService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public PagedResources<Record> findAllRecords(Pageable pageable, PagedResourcesAssembler assembler) {
        return recordService.findAllRecords(pageable, assembler);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Record findRecordById(@PathVariable("id") Long id){
        return recordService.getById(id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Record saveRecord(@RequestBody Record record){
        return recordService.save(record);
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRecord(@PathVariable Long id) { recordService.delete(id); }

    @Transactional
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteRecordList(@RequestBody List<Long> listItemId){
        recordService.deleteRecordList(listItemId);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET, produces = {"application/json"})
    public PagedResources<Record> sortProduct( @PathParam("productType") String productType,
                                               @PathParam("classDefect") String classDefect,
                                               @PathParam("user") String user,
                                               @PathParam("dateFrom") String dateFrom,
                                               @PathParam("dateTo") String dateTo,
                                               @PathParam("consumer") String consumer,
                                               @PathParam("productName") String productName,
                                               @PathParam("number") String number,
                                               Pageable pageable,
                                               PagedResourcesAssembler assembler) throws DateFromException, DateSetParserException {
        return recordService.sortRecord(productType,
                                        classDefect,
                                        user,
                                        dateFrom,
                                        dateTo,
                                        consumer,
                                        productName,
                                        number,
                                        pageable,
                                        assembler);
    }
}
