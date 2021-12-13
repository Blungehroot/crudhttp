import com.crudhttp.app.model.Event;
import com.crudhttp.app.model.Media;
import com.crudhttp.app.model.User;
import com.crudhttp.app.repository.impl.EventRepositoryImpl;
import com.crudhttp.app.service.impl.EventServiceImpl;
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
public class EventServiceTest {

    @Mock
    private EventRepositoryImpl eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    void saveEvent_shouldBeSuccess() {
        Event event = new Event();
        event.setId(1);
        event.setEventName("Upload");

        User user = new User();
        user.setName("User");
        user.setId(1);

        Media media = new Media();
        media.setId(1);
        media.setFileLink("/files/path");
        media.setFileName("FileTest.jpg");

        event.setUser(user);
        event.setMedia(media);


        when(eventRepository.save(event)).thenReturn(event);

        Event eventActual = eventService.save(event);

        assertNotNull(eventActual);
        assertEquals(event, eventActual);

        verify(eventRepository).save(ArgumentMatchers.eq(event));
    }

    @Test
    void deleteEvent_shouldBeSuccess() {
        Event event = new Event();
        event.setId(1);
        event.setEventName("Upload");

        User user = new User();
        user.setName("User");
        user.setId(1);

        Media media = new Media();
        media.setId(1);
        media.setFileLink("/files/path");
        media.setFileName("FileTest.jpg");

        event.setUser(user);
        event.setMedia(media);

        doNothing().when(eventRepository).deleteById(event.getId());

        eventService.deleteById(user.getId());

        verify(eventRepository).deleteById(user.getId());
    }

    @Test
    void updateEvent_shouldBeSuccess() {
        Event event = new Event();
        Event eventUpdate = new Event();
        event.setId(1);
        event.setEventName("Upload");

        eventUpdate.setId(1);
        eventUpdate.setEventName("Update");

        User user = new User();
        user.setName("User");
        user.setId(1);

        Media media = new Media();
        media.setId(1);
        media.setFileLink("/files/path");
        media.setFileName("FileTest.jpg");

        event.setUser(user);
        event.setMedia(media);

        eventUpdate.setMedia(null);
        eventUpdate.setUser(user);

        lenient().when(eventRepository.save(event)).thenReturn(event);
        lenient().when(eventRepository.update(eventUpdate)).thenReturn(eventUpdate);

        Event result = eventService.update(eventUpdate);

        assertNotNull(result);
        assertNotEquals(event, result);

        verify(eventRepository).update(eventUpdate);
    }

    @Test
    void getEventById_shouldBeSuccess() {
        Event event = new Event();
        event.setId(1);
        event.setEventName("Upload");

        User user = new User();
        user.setName("User");
        user.setId(1);

        Media media = new Media();
        media.setId(1);
        media.setFileLink("/files/path");
        media.setFileName("FileTest.jpg");

        event.setUser(user);
        event.setMedia(media);

        when(eventRepository.getById(event.getId())).thenReturn(event);

        Event result = eventService.getById(event.getId());

        assertNotNull(result);
        assertEquals(event, result);

        verify(eventRepository).getById(event.getId());
    }

    @Test
    void getAll_shouldBeSuccess() {
        Event event = new Event();
        event.setId(1);
        event.setEventName("Upload");

        User user = new User();
        user.setName("User");
        user.setId(1);

        Media media = new Media();
        media.setId(1);
        media.setFileLink("/files/path");
        media.setFileName("FileTest.jpg");

        event.setUser(user);
        event.setMedia(media);

        List<Event> events = new ArrayList<>();
        events.add(event);

        when(eventRepository.getAll()).thenReturn(events);

        assertEquals(events, eventService.getAll());

        verify(eventRepository).getAll();
    }
}
