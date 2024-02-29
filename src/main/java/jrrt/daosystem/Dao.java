package jrrt.daosystem;

import java.util.Optional;
import java.util.Set;

public interface Dao<T> 
{
    public Dao<T> save(T t);
    public Dao<T> delete(T t);
    public Dao<T> update(T t, String[] params);
    public Set<T> getAll();
    public Optional<T> get(Long id);
}
