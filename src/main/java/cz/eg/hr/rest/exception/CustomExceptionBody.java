package cz.eg.hr.rest.exception;

import java.time.ZonedDateTime;

/**
 * Custom exception payload
 */
public class CustomExceptionBody {
    private String message;
    private final ZonedDateTime time;

    public CustomExceptionBody(String message, ZonedDateTime time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    // No setter for time since it's meant to be read-only

    @Override
    public String toString() {
        return "CustomExceptionBody{" +
            "message='" + message + '\'' +
            ", time=" + time +
            '}';
    }
}
