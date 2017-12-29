package com.dhirain.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dhirain.myapplication.model.HistoryModel;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by DJ on 14-09-2017.
 */

public class GitRepoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gitExp.db";
    private static final int DATABASE_VERSION = 1;

    public GitRepoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        // register our models
        cupboard().register(HistoryModel.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // this will ensure that all tables are created
        cupboard().withDatabase(db).createTables();
        // add indexes and other database tweaks in this method if you want

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this will upgrade tables, adding columns and new tables.
        // Note that existing columns will not be converted
        cupboard().withDatabase(db).upgradeTables();
        // do migration work if you have an alteration to make to your schema here

    }
}
