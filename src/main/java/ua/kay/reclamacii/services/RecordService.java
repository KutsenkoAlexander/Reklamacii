package ua.kay.reclamacii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.exceptions.DateFromException;
import ua.kay.reclamacii.exceptions.DateSetParserException;
import ua.kay.reclamacii.models.Defect;
import ua.kay.reclamacii.models.DefectSolution;
import ua.kay.reclamacii.models.Record;
import ua.kay.reclamacii.repositories.CtpRepository;
import ua.kay.reclamacii.repositories.ProductTypeRepository;
import ua.kay.reclamacii.repositories.RecordRepository;

import java.text.ParseException;
import java.util.List;

import static ua.kay.reclamacii.utils.UnixDateHandler.toUnixTimeStamp;

@Service
public class RecordService implements CrudService<Record>{
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    CtpService ctpService;
    @Autowired
    RecoveryProductService recoveryProductService;
    @Autowired
    ShipmentService shipmentService;
    @Autowired
    DefectService defectService;
    @Autowired
    DefectSolutionService defectSolutionService;
    @Autowired
    CtpRepository ctpRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public Record save(Record obj) {
        ctpService.save(obj.getCtp());
        recoveryProductService.save(obj.getRecoveryProduct());
        shipmentService.save(obj.getShipment());

        try {
            obj.setEntryDate(toUnixTimeStamp(obj.getEntryDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Record savedRecord = recordRepository.saveAndFlush(obj);
        for(Defect defect : obj.getDefects()){
            defect.setRecord(savedRecord);
            defectService.save(defect);
        }
        for(DefectSolution defectSolution : obj.getDefectSolutions()){
            defectSolution.setRecord(savedRecord);
            defectSolutionService.save(defectSolution);
        }
        return savedRecord;
    }

    @Override
    public void delete(Long id) {
        recordRepository.delete(id);
    }

    public void deleteRecordList(List<Long> selectedRecords) {
        for(Long id : selectedRecords){
            delete(id);
        }
    }

    @Override
    public Record getById(Long id) {
        return recordRepository.findByIdRecord(id);
    }

    @Override
    public List<Record> getAll() {
        return recordRepository.findAll();
    }

    public PagedResources<Record> findAllRecords(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Record> records = recordRepository.findAll(pageable);
        return assembler.toResource(records);
    }

    public PagedResources<Record> sortRecord( String productType,
                                              String classDefect,
                                              String user,
                                              String dateFrom,
                                              String dateTo,
                                              String consumer,
                                              String productName,
                                              String number,
                                              Pageable pageable,
                                              PagedResourcesAssembler assembler) throws DateFromException, DateSetParserException {
        Long idProductType = (productType.equals("")) ? null : Long.valueOf(productType);
        Long idClassDefect = (classDefect.equals("")) ? null : Long.valueOf(classDefect);

        String dateMin = recordRepository.findByEntryDateMin();
        String dateMax = recordRepository.findByEntryDateMax();

        String dateFromNotNull;
        String dateToNotNull;

        try {
            dateFromNotNull = (dateFrom.equals("")) ? dateMin : toUnixTimeStamp(dateFrom);
        } catch (ParseException e) {
            throw new DateSetParserException("Unparseable date: ", e);
        }

        try {
            dateToNotNull = (dateTo.equals("")) ? dateMax : toUnixTimeStamp(dateTo);
        } catch (ParseException e) {
            throw new DateSetParserException("Unparseable date: ", e);
        }

        if(Long.valueOf(dateFromNotNull) > Long.valueOf(dateToNotNull)){
            throw new DateFromException("\"dateFromNotNull\" must be less then \"dateFromNotNull\"", dateFromNotNull);
        }

        if(Long.valueOf(dateFromNotNull) < Long.valueOf(dateMin)){
            dateFromNotNull = dateMin;
        }

        if(Long.valueOf(dateToNotNull) > Long.valueOf(dateMax)){
            dateToNotNull = dateMax;
        }

        Page<Record> records =  recordRepository.sortRecord(idProductType,
                                                            idClassDefect,
                                                            user,
                                                            dateFromNotNull,
                                                            dateToNotNull,
                                                            consumer,
                                                            productName,
                                                            number,
                                                            pageable);
        return assembler.toResource(records);
    }
}
