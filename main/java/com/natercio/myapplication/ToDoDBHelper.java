package com.natercio.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by natercio on 11/10/13.
 */
public class ToDoDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myappdb";

    public static final int DATABESE_VERSION = 1;

    public ToDoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABESE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE task (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, expiration TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS task");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<ToDoTask> getAllTasks() {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<ToDoTask> tasks = new ArrayList<ToDoTask>();

        Cursor all = db.query("task", null, null, null, null, null, null);

        while (all.moveToNext()) {
            ToDoTask task = new ToDoTask(
                    all.getLong(all.getColumnIndex("id")),
                    all.getString(all.getColumnIndex("title")),
                    all.getString(all.getColumnIndex("content")));

            tasks.add(task);
        }

        return tasks;
    }

    public void updateTask(ToDoTask task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        if (db != null) {
            values.put("expiration", task.getDueDate());
            values.put("content", task.getContent());
            values.put("title", task.getTitle());

            db.update("task", values, "id = ?", new String[] {String.valueOf(task.getId())});
        }
    }

    public long putTask(ToDoTask task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", task.getTitle());
        values.put("content", task.getContent());
        values.put("expiration", task.getDueDate());

        return db.insert("task", "null", values);
    }
}
