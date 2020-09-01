package com.packagename.shoppingapp.database.products;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int prod_Sno;
    private String prod_Name;
    private String prod_Price;

    public Product() {
//        this.prod_Sno = prod_Sno;
//        this.prod_Name = prod_Name;
//        this.prod_Price = prod_Price;
    }

    protected Product(Parcel in) {
        prod_Sno = in.readInt();
        prod_Name = in.readString();
        prod_Price = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getProd_Sno() {
        return prod_Sno;
    }

    public void setProd_Sno(int prod_Sno) {
        this.prod_Sno = prod_Sno;
    }

    public String getProd_Name() {
        return prod_Name;
    }

    public void setProd_Name(String prod_Name) {
        this.prod_Name = prod_Name;
    }

    public String getProd_Price() {
        return prod_Price;
    }

    public void setProd_Price(String prod_Price) {
        this.prod_Price = prod_Price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(prod_Sno);
        parcel.writeString(prod_Name);
        parcel.writeString(prod_Price);
    }
}
