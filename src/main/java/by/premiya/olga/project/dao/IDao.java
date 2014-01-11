package by.premiya.olga.project.dao;

/**
 * @author vabramov
 */
public interface IDao<T, ID> {
    void save(T entity);

    void update(T entity);

    T getById(ID id);

    void delete(T entity);
}
