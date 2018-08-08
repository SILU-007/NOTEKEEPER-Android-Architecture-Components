package com.example.sunilkumar.archtut.Room;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity
public class BorrowModel  {
    public BorrowModel() {
    }

    public BorrowModel(String pFirstName, String pItemName, Date pDate) {
        firstName = pFirstName;
        itemName = pItemName;
        mDate = pDate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int pUid) {
        uid = pUid;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "item_name")
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String pItemName) {
        itemName = pItemName;
    }

    @TypeConverters(DateConverter.class)
    private Date mDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String pFirstName) {
        firstName = pFirstName;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date pDate) {
        mDate = pDate;
    }
}
