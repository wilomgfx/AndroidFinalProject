package getrekt.projetfinaljavav2.models.repo;

import java.util.List;

/**
 * Created by 1387434 on 2015-04-20.
 */
public interface CRUD<T>
{
    long save(T o);

    void saveMany(Iterable<T> list);

    void saveMany(T... list);

    T getById(Long p);

    List<T> getAll();

    void deleteOne(Long o);

    void deleteOne(T o);

    void deleteAll();
}
