package ua.kay.reclamacii.controllers;

import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.kay.reclamacii.services.MsWordService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/msword")
public class MsWordController {

    @Autowired
    MsWordService msWordService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void msWordItem(@PathVariable Long id, HttpServletResponse response) throws IOException, XmlException {
        response.setContentType("application/msword");
        InputStream is = new FileInputStream(msWordService.createWordDocument(id));
        org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
        is.close();
        msWordService.deleteWordDocument(id+".docx");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void msWordListItems(@RequestBody List<Long> listItemId, HttpServletResponse response) throws IOException, XmlException {
        response.setContentType("application/msword");
        InputStream is = new FileInputStream(msWordService.createWordDocument(listItemId));
        org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
        is.close();
        msWordService.deleteWordDocument("ListRecord.docx");
    }

}