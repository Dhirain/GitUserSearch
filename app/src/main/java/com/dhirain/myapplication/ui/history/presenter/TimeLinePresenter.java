package com.dhirain.myapplication.ui.history.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dhirain.myapplication.database.GitRepoDatabaseHelper;
import com.dhirain.myapplication.database.manager.DBManager;
import com.dhirain.myapplication.model.HistoryModel;
import com.dhirain.myapplication.ui.history.view.TimeLineView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public class TimeLinePresenter {
    private static final String TAG = "AllSongPresenter";
    private Context context;
    private List<HistoryModel> totalMusicList;
    private List<HistoryModel> currentNewsList;
    private int currentPage = 0;
    private TimeLineView allSongView;
    private GitRepoDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public TimeLinePresenter(Context context, TimeLineView view ) {
        this.context = context;
        this.allSongView = view;
        dbHelper = new GitRepoDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public void onViewAttached() {
        allSongView.showProgress();
        fetchRepoFromDb();
    }

    public void paggination() {
        if (currentNewsList == null) {
            currentPage = 0;
            currentNewsList = new ArrayList<>();
        }
        int tillPage = currentPage + 10;
        while (currentPage < tillPage && currentPage < totalMusicList.size()) {
            currentNewsList.add(totalMusicList.get(currentPage));
            currentPage++;
        }
        Log.d(TAG, "paggination: " + currentPage);
        allSongView.updateList(currentNewsList);
    }


    private void fetchRepoFromDb() {
        totalMusicList = DBManager.instance().getAllHistoryDb();
        Log.d(TAG, "fetchRepoFromDb: "+totalMusicList.toString());
        paggination();
        allSongView.hideProgress();
    }


}
