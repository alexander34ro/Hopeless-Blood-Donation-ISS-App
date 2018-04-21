package Networking;

public class FlightsException extends Exception implements java.io.Serializable {
    public FlightsException() {}
    public FlightsException(String message) {
        super(message);
    }
    public FlightsException(String message, Throwable reason) {
        super(message, reason);
    }
}
