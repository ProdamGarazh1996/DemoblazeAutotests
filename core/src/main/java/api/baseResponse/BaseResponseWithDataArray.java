package api.baseResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Базовый класс ответа c статус-кодом code, массивом data и строковым message
 *
 * @param <T> - тип ответа
 */
@Getter
@Setter
public class BaseResponseWithDataArray<T> {
    private Integer code;
    @JsonProperty("Items")
    private T[] items;
    private String message;
}


