package io.github.qqbot.spring;

public class QQBotApiException extends RuntimeException {
    private final int statusCode;
    private final String responseBody;

    public QQBotApiException(String message, int statusCode, String responseBody, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() { return statusCode; }
    public String getResponseBody() { return responseBody; }
}
