package posProjectForTuring.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CurrentDateTime {
    public static Timestamp get(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        
        return timestamp;
    }
}
