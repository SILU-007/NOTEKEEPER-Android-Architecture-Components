package com.example.sunilkumar.archtut;

import android.app.Application;
import android.os.AsyncTask;

import com.example.sunilkumar.archtut.Room.AppDatabase;
import com.example.sunilkumar.archtut.Room.BorrowModel;

import androidx.lifecycle.AndroidViewModel;


public class AddBorrowViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddBorrowViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

    }

    public void addBorrow(final BorrowModel borrowModel) {
        new addAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class addAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BorrowModel... params) {
            db.getItemAndPersonModelDao().addItemToBorrowModel(params[0]);
            return null;
        }

    }
}