package server.services;

import server.dao.UserDAO;
import shared.models.User;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User authenticate(String username, String password) throws Exception {
        return userDAO.authenticate(username, password);
    }
}
