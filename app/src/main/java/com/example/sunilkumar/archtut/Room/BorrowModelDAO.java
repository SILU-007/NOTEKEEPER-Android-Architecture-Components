package com.example.sunilkumar.archtut.Room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface BorrowModelDAO {

    @Query("select * from BorrowModel")
    LiveData<List<BorrowModel>> getAllBorrowModel();

    @Query("select * from BorrowModel where uid=:id")
    BorrowModel getItemById(String id);

    @Insert(onConflict = REPLACE)
    void addItemToBorrowModel(BorrowModel pBorrowModel);

    @Delete
    void deleteBorrowModel(BorrowModel pBorrowModel);

}
