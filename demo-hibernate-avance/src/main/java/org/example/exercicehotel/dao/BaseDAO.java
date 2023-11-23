package org.example.exercicehotel.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class BaseDAO<T, ID extends Serializable> {

    private final Class<T> persistentClass;
    protected final Session session;

    protected BaseDAO(Session session) {
        persistentClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.session = session;
    }

    public T findById(ID id) {
        return session.get(persistentClass, id);
    }

    public ID save(T entity) {
        ID id = (ID) session.save(entity);
        return id;
    }
}
