package org.example.exercicehotel.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
public abstract class BaseDAO<T, ID extends Serializable> {

    private final Class<T> persistentClass;
    protected final Session session;

    protected BaseDAO(Session session) {
        persistentClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.session = session;
    }

    public T findById(ID id, Session session) {
        return session.get(persistentClass, id);
    }

    public ID save(T entity, Session session) {
        return (ID) session.save(entity);
    }

    public void update(T entity, Session session) {
        session.update(entity);
    }

    public void delete(T entity, Session session) {
        session.delete(entity);
    }

    public List<T> findAll(Session session) {
        return session.createQuery("from " + persistentClass.getName(), persistentClass).list();
    }
}
