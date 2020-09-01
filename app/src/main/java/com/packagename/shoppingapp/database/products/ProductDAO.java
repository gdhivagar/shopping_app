package com.packagename.shoppingapp.database.products;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void insert(Product... products);

    @Query("SELECT * FROM Product")
    List<Product> getProductList();
}
