package com.buselorest.model.dao.interfaces;

import com.buselorest.model.exception.PersistException;

public interface DaoFactory<Context> {

      interface DaoCreator<Context> {
            GenericDao create(Context context);
      }

      Context getContext() throws PersistException;

      GenericDao getDao(Context context, Class dtoClass) throws PersistException;
}
