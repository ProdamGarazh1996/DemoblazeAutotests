package utils;

public enum HttpStatusCode {
    CREATED(201),
    UNPROCESSABLE_CONTENT(422),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    UNPROCESSABLE_ENTITY(422),
    NO_CONTENT(204),
    OK(200);

    private final int code;

    HttpStatusCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
