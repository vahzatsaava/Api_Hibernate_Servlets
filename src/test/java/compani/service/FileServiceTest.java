package compani.service;

import compani.model.File;

import compani.repository.FileRepository;
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
public class FileServiceTest {
    @Mock
    private FileRepository repository;

    @InjectMocks
    private FileService service;
    private final File file = new File("Repo","Http");
    private final List<File> files = new ArrayList<>();

    @Test
    public void createTest_Successful() {
        Mockito.when(repository.save(any(File.class))).thenReturn(file);
        Assertions.assertEquals("Repo", service.add(file).getFileName());
    }
    @Test
    public void createTest_unSuccessful() {
        Mockito.when(repository.save(any(File.class))).thenReturn(file);
        Assertions.assertNotEquals(null, service.add(file).getFileName());
    }

    @Test
    public void update_Successful() {
        Mockito.when(repository.update(any(File.class))).thenReturn(file);
        Assertions.assertEquals("Repo", service.update(file).getFileName());
    }
    @Test
    public void update_unSuccessful() {
        Mockito.when(repository.update(any(File.class))).thenReturn(file);
        Assertions.assertNotEquals(null, service.update(file).getFileName());
    }

    @Test
    public void find_Successful() {
        Mockito.when(repository.findById(anyInt())).thenReturn(file);
        Assertions.assertEquals("Repo", service.find(1).getFileName());
    }
    @Test
    public void find_unSuccessful() {
        Mockito.when(repository.findById(anyInt())).thenReturn(file);
        Assertions.assertNotEquals(null, service.find(1).getFileName());
    }

    @Test
    public void getFiles_Successful() {
        files.add(file);
        Mockito.when(repository.getAll()).thenReturn(files);
        Assertions.assertEquals("Repo",service.getFiles().get(0).getFileName());
    }
    @Test
    public void getFiles_unSuccessful() {
        files.add(file);
        Mockito.when(repository.getAll()).thenReturn(files);
        Assertions.assertNotEquals(null,service.getFiles().get(0).getFileName());
    }

}