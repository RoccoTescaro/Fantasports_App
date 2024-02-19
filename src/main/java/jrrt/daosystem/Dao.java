package jrrt.daosystem;

import java.util.Optional;
import java.util.List;

public interface Dao<T> 
{
    Optional<T> get(Long id);

    List<T> getAll();

    void add(T t);

    void update(T t, String[] params);

    void delete(T t);
}