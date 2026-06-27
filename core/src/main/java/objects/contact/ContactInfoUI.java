package objects.contact;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactInfoUI {
    String name;
    String country;
    String city;
    String creditCard;
    String month;
    String year;
    Date date;
}
