package api.baseResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class BaseResponse<T> {
    /**
     * Базовый класс ответа c статус-кодом code, объектом data и строковым message
     *
     * @param <T> - тип ответа
     */
    private Integer code;
    private T item;
    private String message;
}
