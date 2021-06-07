package com.example.testapp;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BabbyViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    /*
    private BabbyDao babbyDao;
    private LiveData<List<BabbyEntity>> allBabbyEntityLive;
    public BabbyViewModel(@NotNull Application application)
    {
        super(application);
        BabbyDatabase babbyDatabase=BabbyDatabase.getDatabase(application);
        babbyDao=babbyDatabase.getBabbyDao();
        allBabbyEntityLive=babbyDao.getall();
    }

    public LiveData<List<BabbyEntity>> getAllBabbyEntityLive()
    {
        return allBabbyEntityLive;
    }

    void insetBabbyEntity(BabbyEntity...babbyEntities)
    {
        new InsertAsyncTask(babbyDao).execute(babbyEntities);
    }

    static class InsertAsyncTask extends AsyncTask<BabbyEntity,Void,Void> {
        private BabbyDao babbyDao1;
        public InsertAsyncTask(BabbyDao babbyDao){
            this.babbyDao1 = babbyDao;
        }
        @Override
        protected Void doInBackground(BabbyEntity... babbyEntities) {
            babbyDao1.insert(babbyEntities);
            return null;
        }
    }*/


}