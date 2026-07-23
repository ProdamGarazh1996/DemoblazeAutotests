package api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiEndPoints {

    //Category
    BY_CAT("/bycat"),
    VIEW("/view"),
    SIGNUP("/signup"),
    LOGIN("/login"),
    ADDTOCART("/addtocart"),
    VIEWCART("/viewcart"),
    DELETE_ITEM("/deleteitem"),
    PAGINATION("/pagination");

    private final String endPoint;
}
