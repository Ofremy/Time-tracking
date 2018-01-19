package com.buselorest.model.dao.interfaces;

import com.buselorest.model.exception.PersistException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {
    /**
     * Создает новую запись и соответствующий ей объект
     */
     T create() throws PersistException;

    /**
     * Создает новую запись, соответствующую объекту object
     */
     T persist(T object) throws PersistException;

    /**
     * Возвращает объект соответствующий записи с первичным ключом key или null
     */
     T getByPK(PK key) throws PersistException;

    /**
     * Сохраняет состояние объекта group в базе данных
     */
     void update(T object) throws PersistException;

    /**
     * Удаляет запись об объекте из базы данных
     */
     void delete(T object) throws PersistException;

    /**
     * Возвращает список объектов соответствующих всем записям в базе данных
     */
     List<T> getAll() throws PersistException;
}