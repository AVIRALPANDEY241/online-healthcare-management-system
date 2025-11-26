package dao;

import java.util.List;

public interface GenericDao<T> {
    void save(T obj);
    T findById(int id);
    List<T> findAll();
}
