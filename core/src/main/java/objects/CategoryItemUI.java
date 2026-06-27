package objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryItemUI {
    String name;
    String category;
    String price;
    String description;
}
