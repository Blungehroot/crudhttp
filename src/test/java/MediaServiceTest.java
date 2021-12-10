import com.crudhttp.app.model.Event;
import com.crudhttp.app.model.Media;
import com.crudhttp.app.model.User;
import com.crudhttp.app.repository.impl.MediaRepositoryImpl;
import com.crudhttp.app.service.impl.MediaServiceImpl;
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
public class MediaServiceTest {

    @Mock
    private MediaRepositoryImpl mediaRepository;

    @InjectMocks
    private MediaServiceImpl mediaService;

    @Test
    void saveMedia_shouldBeSuccess() {
        Media media = new Media();
        Event event = new Event();
        User user = new User();

        user.setName("Tester");
        user.setId(1);

        event.setId(1);
        event.setEventName("Update");
        event.setUser(user);

        media.setId(1);
        media.setFileName("TestName");
        media.setFileLink("/link/to/file");
        media.setEvent(event);

        when(mediaRepository.save(media)).thenReturn(media);

        Media mediaActual = mediaService.save(media);

        assertNotNull(mediaActual);
        assertEquals(media, mediaActual);

        verify(mediaRepository).save(ArgumentMatchers.eq(media));
    }

    @Test
    void deleteMedia_shouldBeSuccess() {
        Media media = new Media();

        media.setId(1);
        media.setFileName("TestName");
        media.setFileLink("/link/to/file");

        doNothing().when(mediaRepository).deleteById(media.getId());

        mediaService.deleteById(media.getId());

        verify(mediaRepository).deleteById(media.getId());
    }

    @Test
    void updateMedia_shouldBeSuccess() {
        Media media = new Media();
        Media mediaUpdate = new Media();

        media.setId(1);
        media.setFileName("TestName");
        media.setFileLink("/link/to/file");

        mediaUpdate.setFileName("Updated");
        mediaUpdate.setId(1);
        mediaUpdate.setFileLink("/link/to/fileUpdate");

        lenient().when(mediaRepository.save(media)).thenReturn(media);
        lenient().when(mediaRepository.update(mediaUpdate)).thenReturn(mediaUpdate);

        Media result = mediaService.update(mediaUpdate);

        assertNotNull(result);
        assertNotEquals(media, result);

        verify(mediaRepository).update(mediaUpdate);
    }

    @Test
    void getMediaById_shouldBeSuccess() {
        Media media = new Media();

        media.setId(1);
        media.setFileName("TestName");
        media.setFileLink("/link/to/file");

        when(mediaRepository.getById(media.getId())).thenReturn(media);

        Media result = mediaService.getById(media.getId());

        assertNotNull(result);
        assertEquals(media, result);

        verify(mediaRepository).getById(media.getId());
    }

    @Test
    void getAll_shouldBeSuccess() {
        Media media = new Media();

        media.setId(1);
        media.setFileName("TestName");
        media.setFileLink("/link/to/file");

        List<Media> mediaList = new ArrayList<>();
        mediaList.add(media);

        when(mediaRepository.getAll()).thenReturn(mediaList);

        assertEquals(mediaList, mediaService.getAll());

        verify(mediaRepository).getAll();
    }
}
