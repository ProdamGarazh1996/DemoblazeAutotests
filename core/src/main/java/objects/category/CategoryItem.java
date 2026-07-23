package objects.category;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryItem {
    String title;
    String cat;
    Integer id;
    String img;
    Double price;
    String desc;
}