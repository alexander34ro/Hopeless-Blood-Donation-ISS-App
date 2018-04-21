package Networking;


import Networking.Interfaces.ServerInterface;

public class ServerImplementation implements ServerInterface {

    public ServerImplementation() {
    }

    @Override
    public String removeLastCharacter(String text) {
        return text.substring(0, text.length() - 1);
    }
}
