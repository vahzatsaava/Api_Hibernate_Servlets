package compani.controller;

import compani.model.User;
import compani.repository.hibernate.HibernateUserRepositoryImp;
import compani.service.UserService;

import java.util.List;

public class UserController {
    private UserService userService = new UserService();

    public User add(User user) {
        return userService.add(user);
    }

    public User update(User user) {
        return userService.update(user);
    }

    public User find(int id) {
        return userService.find(id);
    }

    public List<User> getUsers() {
        return userService.getUsers();
    }

    public void delete(int id) {
        userService.delete(id);
    }
}
