package objects.cart;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartApiRequest {
    String id;
    String cookie;
    @SerializedName("prod_id")
    String prodId;
    boolean flag;
}
