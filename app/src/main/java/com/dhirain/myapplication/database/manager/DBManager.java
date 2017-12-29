package com.dhirain.myapplication.database.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dhirain.myapplication.GitRepoApp;
import com.dhirain.myapplication.database.GitRepoDatabaseHelper;
import com.dhirain.myapplication.model.HistoryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by DJ on 14-09-2017.
 */

public class DBManager {

    private static DBManager sSingleton;
    private Context mContext;
    private GitRepoDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public static synchronized DBManager instance() {
        if (sSingleton == null) {
            sSingleton = new DBManager();
        }

        return sSingleton;
    }

    private DBManager() {
        mContext= GitRepoApp.singleton().getContext();
        dbHelper= new GitRepoDatabaseHelper(mContext);
        db = dbHelper.getWritableDatabase();
    }


    public List<HistoryModel> getAllHistoryDb() {
        List<HistoryModel> repos = new ArrayList<>();
        QueryResultIterable<HistoryModel> itr = cupboard().withDatabase(db).query(HistoryModel.class).orderBy("time").query();
        for (HistoryModel s : itr)
            repos.add(s);
        Collections.reverse(repos);
        return repos;
    }
}
