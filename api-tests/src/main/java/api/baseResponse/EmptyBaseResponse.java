package api.baseResponse;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmptyBaseResponse {
    /**
     * Базовый класс ответа c статус-кодом code, строковым message
     */
    private Integer code;
    private String message;
}
