package service;

import java.util.List;

public interface IStudentService<E>{
    void add(E e);
    void delete(int id);
    void edit(int id,E e);
    List<E> findAll();
}
