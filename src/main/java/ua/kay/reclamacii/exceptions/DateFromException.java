package ua.kay.reclamacii.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateFromException extends Exception {

    private String dateFrom;

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public DateFromException(String message, String dateFrom) {
        super(message);
        this.dateFrom = dateFrom;
    }
}
