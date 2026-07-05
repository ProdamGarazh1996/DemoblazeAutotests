package objects.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartApiRequest {
    String id;
    String cookie;
    @JsonProperty("prod_id")
    String prodId;
    boolean flag;
}
