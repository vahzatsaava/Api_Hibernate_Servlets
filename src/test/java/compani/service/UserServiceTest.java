package compani.service;


import compani.model.User;
import compani.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;
    private final User user = new User("Alfa","Betta");
    private final List<User> users = new ArrayList<>();

    @Test
    public void createTest_Successful() {
        Mockito.when(repository.save(any(User.class))).thenReturn(user);
        Assertions.assertEquals("Alfa", service.add(user).getFirstName());
    }
    @Test
    public void createTest_unSuccessful() {
        Mockito.when(repository.save(any(User.class))).thenReturn(user);
        Assertions.assertNotEquals(null, service.add(user).getFirstName());
    }
    @Test
    public void update_Successful(){
    Mockito.when(repository.update(any(User.class))).thenReturn(user);
    Assertions.assertEquals("Alfa",service.update(user).getFirstName());
    }

    @Test
    public void update_unSuccessful(){
        Mockito.when(repository.update(any(User.class))).thenReturn(user);
        Assertions.assertNotEquals(null,service.update(user).getFirstName());
    }
    @Test
    public void getAll_Successful(){
        users.add(user);
        Mockito.when(repository.getAll()).thenReturn(users);
        Assertions.assertEquals("Alfa",service.getUsers().get(0).getFirstName());
    }
    @Test
    public void getAll_unSuccessful(){
        users.add(user);
        Mockito.when(repository.getAll()).thenReturn(users);
        Assertions.assertNotEquals(null,service.getUsers().get(0).getFirstName());
    }
    @Test
    public void findByID_Successful(){
        Mockito.when(repository.findById(anyInt())).thenReturn(user);
        Assertions.assertEquals("Alfa",service.find(1).getFirstName());
    }
    @Test
    public void findByID_unSuccessful(){
        Mockito.when(repository.findById(anyInt())).thenReturn(user);
        Assertions.assertNotEquals(null,service.find(1).getFirstName());
    }

}