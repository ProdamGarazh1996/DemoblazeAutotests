package utils;

public enum ErrorMessageText {
    NON_EXISTING_USER("User does not exist."),
    EMPTY_REQUEST("Bad parameter, missing username");

    private final String message;

    ErrorMessageText(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
