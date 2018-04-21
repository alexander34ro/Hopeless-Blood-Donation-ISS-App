package Networking;

public class NetworkingException extends Exception implements java.io.Serializable {
    public NetworkingException() {}
    public NetworkingException(String message) {
        super(message);
    }
    public NetworkingException(String message, Throwable reason) {
        super(message, reason);
    }
}
