package com.example.testapp;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.testapp.Search_Listview.Search;
import com.example.testapp.Search_Listview.SearchDao;
import com.example.testapp.Search_Listview.SearchDatabase;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {
    private SearchDao searchDao;
    private SearchDatabase searchDatabase;
    private LiveData<List<Search>>allSearchLive = null;
    private LiveData<Integer> count;
    public SearchViewModel(Application application) {
        super(application);

        searchDatabase = SearchDatabase.getDatabase(application);
        //searchDatabase.getSearchDao().

        searchDao = searchDatabase.getSearchDao();

        allSearchLive = searchDao.GetAllWorks();

        count = searchDao.GetCount();
    }

    public LiveData<Integer> getCount() {
        return count;
    }

    public SearchDatabase getSearchDatabase() {
        return searchDatabase;
    }

    public void setSearchDatabase(SearchDatabase searchDatabase) {
        this.searchDatabase = searchDatabase;
    }

    public LiveData<List<Search>> getAllSearchLive() {
        return allSearchLive;
    }

    public void setAllSearchLive(LiveData<List<Search>> allSearchLive) {
        this.allSearchLive = allSearchLive;
    }

    public SearchDao getSearchDao() {
        return searchDao;
    }

    public void setSearchDao(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    void insertSearchs(Search...searches){
        new InsertAsyncTask(searchDao).execute(searches);
    }
    void updateSearchs(Search...searches){
        new UpdateAsyncTask(searchDao).execute(searches);
    }
    void deleteSearchs(Search...searches){
        new DeleteAsyncTask(searchDao).execute(searches);
    }
    void deleteAllWorksSearchs(){
        new DeleteAllAsyncTask(searchDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Search,Void,Void> {
        private SearchDao searchDao;

        public InsertAsyncTask(SearchDao searchDao) {
            this.searchDao = searchDao;
        }

        @Override
        protected Void doInBackground(Search... searches) {
            searchDao.InsertWorks(searches);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Search,Void,Void> {
        private SearchDao searchDao;

        public UpdateAsyncTask(SearchDao searchDao) {
            this.searchDao = searchDao;
        }

        @Override
        protected Void doInBackground(Search... searches) {
            searchDao.UpdateWorks(searches);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Search,Void,Void> {
        private SearchDao searchDao;

        public DeleteAsyncTask(SearchDao searchDao) {
            this.searchDao = searchDao;
        }

        @Override
        protected Void doInBackground(Search... searches) {
            searchDao.DeleteWorks(searches);
            return null;
        }
    }
/*
    static class GetAllWorksAsyncTask extends AsyncTask<Search,Void,Void> {
        private SearchDao searchDao;

        public GetAllWorksAsyncTask(SearchDao searchDao) {
            this.searchDao = searchDao;
        }

        @Override
        protected Void doInBackground(Search... searches) {
            return SearchDao.GetAllWorks(searches);
            //return null;
        }
    }*/

    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void> {
        private SearchDao searchDao;

        public DeleteAllAsyncTask(SearchDao searchDao) {
            this.searchDao = searchDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            searchDao.DeleteAll();
            return null;
        }
    }

    // TODO: Implement the ViewModel
}