package com.packagename.shoppingapp.database.products;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = ProductDatabase.VERSION)
public abstract class ProductDatabase extends RoomDatabase {
    static final int VERSION = 1;
    public abstract ProductDAO getProductDAO();
}