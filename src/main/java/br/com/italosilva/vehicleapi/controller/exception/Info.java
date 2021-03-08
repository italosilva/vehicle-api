package br.com.italosilva.vehicleapi.controller.exception;

import java.time.LocalDateTime;
import java.util.List;

public class Info {

    private final String timestamp;
    private final int status;
    private final String error;
    private final List<String> messages;
    private final String path;

    public Info(int status, String error, List<String> messages, String path) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getPath() {
        return path;
    }
}
