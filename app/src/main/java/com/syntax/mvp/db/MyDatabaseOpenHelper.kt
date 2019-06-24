package com.syntax.mvp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    /**
     * AUTOINCREMENT is error => id from moviesId is String
     * */

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            FavoritesMovies.TABLE_MOVIES, true,
            FavoritesMovies._ID to INTEGER + PRIMARY_KEY,
            FavoritesMovies.MOVIES_ID to TEXT,
            FavoritesMovies.MOVIES_NAME to TEXT,
            FavoritesMovies.MOVIES_POP to TEXT,
            FavoritesMovies.MOVIES_DATE to TEXT,
            FavoritesMovies.MOVIES_OVERVIEW to TEXT,
            FavoritesMovies.IMAGES to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoritesMovies.TABLE_MOVIES, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(getApplicationContext())