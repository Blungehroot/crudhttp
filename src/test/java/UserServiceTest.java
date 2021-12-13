import com.crudhttp.app.model.User;
import com.crudhttp.app.repository.impl.UserRepositoryImpl;
import com.crudhttp.app.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void saveUser_shouldBeSuccess() {
        User user = new User();

        user.setName("Tester");
        user.setId(1);

        when(userRepository.save(user)).thenReturn(user);

        User userActual = userService.save(user);

        assertNotNull(userActual);
        assertEquals(user, userActual);

        verify(userRepository).save(ArgumentMatchers.eq(user));
    }

    @Test
    void deleteUser_shouldBeSuccess() {
        User user = new User();

        user.setName("Tester");
        user.setId(1);

        doNothing().when(userRepository).deleteById(user.getId());

        userService.deleteById(user.getId());

        verify(userRepository).deleteById(user.getId());
    }

    @Test
    void updateUser_shouldBeSuccess() {
        User user = new User();
        User userUpdate = new User();

        user.setName("Tester");
        user.setId(1);

        userUpdate.setId(1);
        userUpdate.setName("Tester update");

        lenient().when(userRepository.save(user)).thenReturn(user);
        lenient().when(userRepository.update(userUpdate)).thenReturn(userUpdate);

        User result = userService.update(userUpdate);

        assertNotNull(result);
        assertNotEquals(user, result);

        verify(userRepository).update(userUpdate);
    }

    @Test
    void getUserById_shouldBeSuccess() {
        User user = new User();

        user.setName("Tester");
        user.setId(1);

        when(userRepository.getById(user.getId())).thenReturn(user);

        User result = userService.getById(user.getId());

        assertNotNull(result);
        assertEquals(user, result);

        verify(userRepository).getById(user.getId());
    }

    @Test
    void getAll_shouldBeSuccess() {
        User user = new User();

        user.setName("Tester");
        user.setId(1);

        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.getAll()).thenReturn(users);

        assertEquals(users, userService.getAll());

        verify(userRepository).getAll();
    }
}
