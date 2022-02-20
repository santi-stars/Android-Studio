package com.svalero.listacompra.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.listacompra.dao.ProductDao;
import com.svalero.listacompra.domain.Product;

@Database(entities = {Product.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}
// TODO ESTE CÓDIGO ENTERO PARA INDICAR LAS CLASES DE LAS ENTIDADES