package com.example.sunilkumar.archtut.ViewModel;

import android.app.Application;
import android.os.AsyncTask;

import com.example.sunilkumar.archtut.Room.AppDatabase;
import com.example.sunilkumar.archtut.Room.BorrowModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BorrowedListViewModel extends AndroidViewModel {

    private LiveData<List<BorrowModel>> itemAndPersonList;

    private AppDatabase mAppDatabase;

    public BorrowedListViewModel(@NonNull Application application) {
        super(application);

        mAppDatabase = AppDatabase.getDatabase(application);

        //call -> Database -> BorrowModelDao returning method -> query to get allBorrowModel
        //BorrowModel == a single row of table/entity BorrowModel in database
        itemAndPersonList = mAppDatabase.getItemAndPersonModelDao().getAllBorrowModel();
    }

    public LiveData<List<BorrowModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(BorrowModel pBorrowModel) {
        new deleteAsyncTask(mAppDatabase).execute(pBorrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BorrowModel... params) {
            db.getItemAndPersonModelDao().deleteBorrowModel(params[0]);
            return null;
        }

    }

}



