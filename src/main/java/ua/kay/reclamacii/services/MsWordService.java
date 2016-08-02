package ua.kay.reclamacii.services;

import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kay.reclamacii.models.Record;
import ua.kay.reclamacii.utils.WordTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class MsWordService {

    @Autowired
    RecordService recordService;

    public File createWordDocument(Long id) throws IOException, XmlException {
        Record record = recordService.getById(id);
        WordTable wordTale = new WordTable();
        wordTale.createTable(record);
        File file = null;
        try {
            FileOutputStream output = new FileOutputStream(id+".docx");
            wordTale.getDocument().write(output);
            file = new File(id+".docx");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public File createWordDocument(List<Long> listItemId) throws IOException, XmlException {
        List<Record> listRecord = new LinkedList<>();
        for(Long id : listItemId){
            Record record = recordService.getById(id);
            listRecord.add(record);
        }
        WordTable wordTale = new WordTable();
        wordTale.createTable(listRecord);
        File file = null;
        try {
            FileOutputStream output = new FileOutputStream("ListRecord.docx");
            wordTale.getDocument().write(output);
            file = new File("ListRecord.docx");
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void deleteWordDocument(String name){
        new File(name).delete();
    }

}
