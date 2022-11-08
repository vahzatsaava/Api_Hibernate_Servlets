package compani.controller;

import compani.model.File;
import compani.service.FileService;

import java.util.List;

public class FileController {
    private FileService fileService = new FileService();
    public File add(File file){
        return fileService.add(file);
    }
    public File update(File file){
        return fileService.update(file);
    }
    public File find(int id){
        return fileService.find(id);
    }
    public List<File> getFiles(){
        return fileService.getFiles();
    }
    public void delete(int id){
        fileService.delete(id);
    }
}
