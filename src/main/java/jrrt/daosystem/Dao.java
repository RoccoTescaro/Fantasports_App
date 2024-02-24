package jrrt.core.daosystem;

import java.util.Optional;
import java.util.List;

public interface Dao<T> 
{
    public Dao<T> save(T t);
    public Dao<T> delete(T t);
    public Dao<T> update(T t, String[] params);
    public List<T> getAll();
    public Optional<T> get(Long id);
}
