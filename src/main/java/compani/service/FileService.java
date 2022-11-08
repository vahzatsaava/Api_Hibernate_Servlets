package compani.service;

import compani.model.File;
import compani.model.User;
import compani.repository.FileRepository;
import compani.repository.hibernate.HibernateFileRepositoryImpl;

import java.util.List;

public class FileService {
    private final FileRepository fileRepository;

    public FileService() {
        this.fileRepository = new HibernateFileRepositoryImpl();
    }
    public File add(File file){
        return fileRepository.save(file);
    }
    public File update(File file){
        return fileRepository.update(file);
    }
    public File find(int id){
        return fileRepository.findById(id);
    }
    public List<File> getFiles(){
        return fileRepository.getAll();
    }
    public void delete(int id){
        fileRepository.deleteById(id);
    }
}
