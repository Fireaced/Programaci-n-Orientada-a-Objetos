package upm.repositorios;

import java.util.LinkedList;
import java.util.Optional;

public interface RepGenerico<T> {
    T create(T entity);

    T update(T entity);

    Optional<T> read(Integer id);

    void deleteById(Integer id);

    LinkedList<T> findAll();
}

