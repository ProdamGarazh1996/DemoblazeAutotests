package api.baseResponse;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class EmptyBaseResponse {
    /**
     * Базовый класс ответа c статус-кодом code, строковым message
     */
    private Integer code;
    private String message;
}
