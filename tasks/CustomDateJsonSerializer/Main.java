package jr.testing.CustomDateJsonSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String toParse = "10-01-2024 02:30:00";
        Date date = null;
        try {
            date = df.parse(toParse);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Event event = new Event("party", date);
        String result = new ObjectMapper().writeValueAsString(event);
        System.out.println(result);
    }
}
