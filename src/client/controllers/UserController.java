package client.controllers;

import shared.rmi.RemoteInterface;
import shared.models.User;
import java.rmi.Naming;

public class UserController {
    private RemoteInterface remote;

    public UserController(String serverAddress) throws Exception {
        remote = (RemoteInterface) Naming.lookup(serverAddress);
    }

    public User login(String username, String password) throws Exception {
        return remote.authenticate(username, password);
    }
    
    public RemoteInterface getRemote() {
        return remote;
    }
}
