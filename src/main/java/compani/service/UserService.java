package compani.service;

import compani.model.User;
import compani.repository.UserRepository;
import compani.repository.hibernate.HibernateUserRepositoryImp;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new HibernateUserRepositoryImp();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public User find(int id) {
        return userRepository.findById(id);
    }
    public List<User> getUsers(){
        return userRepository.getAll();
    }
    public void delete(int id){
        userRepository.deleteById(id);
    }
}
