package com.dhirain.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.dhirain.myapplication.model.HistoryModel;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public class StoreHistoryAsync extends AsyncTask<HistoryModel, Void, String> {
    private static final String TAG = "StoreHistoryAsync";
    private Context context;
    GitRepoDatabaseHelper dbHelper;
    SQLiteDatabase db;

    public StoreHistoryAsync(Context context) {
        this.context = context;
        dbHelper = new GitRepoDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    @Override
    protected void onPostExecute(String result) {
        Log.d(TAG, "onPostExecute: ");
    }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(Void... values) {}


    @Override
    protected String doInBackground(HistoryModel... timeLineModels) {

        HistoryModel timeLineModel = timeLineModels[0];
        Log.d(TAG, "doInBackground: "+timeLineModel.toString());
        long id=cupboard().withDatabase(db).put(timeLineModel);
        Log.d(TAG, "doInBackground: "+id);
        return "done";
    }
}
