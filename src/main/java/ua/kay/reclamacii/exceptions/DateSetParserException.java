package ua.kay.reclamacii.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateSetParserException extends Exception {

    private ParseException date;

    public ParseException getDate() {
        return date;
    }

    public void setDate(ParseException date) {
        this.date = date;
    }

    public DateSetParserException(String message, ParseException date) {
        super(message);
        this.date = date;
    }
}
