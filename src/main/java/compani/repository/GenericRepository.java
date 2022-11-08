package compani.repository;

import java.util.List;

public interface GenericRepository<T,ID>{
    T save(T t);
    T update(T t);
    T findById(int id);
    List<T> getAll();
    void deleteById(int id);
}
