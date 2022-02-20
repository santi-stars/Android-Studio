package com.svalero.listacompra.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.listacompra.domain.Product;

import java.util.List;

@Dao
public interface ProductDao {   // CLASE DE ACCESO A LOS DATOS DE LA BBDD ROOM DEL MOVIL
    @Query("SELECT * FROM product")
    List<Product> getAll();

    // MÉTODO LIST QUE EJECUTA LA QUERY EN CUESTION
    @Query("SELECT * FROM product WHERE name = :name")
    List<Product> findByName(String name);
    //**********************************************
    @Insert
    void insert(Product product);

    @Update         // Aqui da fallo y hay que darle a Build/Rebuild Project
    void update(Product product);

    @Delete
    void delete(Product product);


}
