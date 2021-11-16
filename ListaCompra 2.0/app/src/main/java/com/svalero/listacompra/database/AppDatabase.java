package com.svalero.listacompra.Database;

import androidx.room.Database;

import com.svalero.listacompra.domain.Product;

@Database(entities = {Product.class}, version = 1)
public class AppDatabase {
}
