package objects.contact;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
public class ContactInfo {
    String name;
    String email;
    String country;
    String city;
    String creditCard;
    String month;
    String year;
    Date date;
}
