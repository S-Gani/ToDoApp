package com.example.todoapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "tasks_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_TASKS = "tasks"
        private const val COLUMN_TASK_NAME = "task_name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TASKS_TABLE = "CREATE TABLE $TABLE_TASKS (" +
                "$COLUMN_TASK_NAME TEXT)"
        db?.execSQL(CREATE_TASKS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        onCreate(db)
    }

    // Add task method
    fun addTask(taskName: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TASK_NAME, taskName)
        db.insert(TABLE_TASKS, null, contentValues)
        db.close()
    }

    // Get all tasks method
    fun getAllTasks(): List<String> {
        val tasks = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_TASKS", null)
        if (cursor.moveToFirst()) {
            do {
                val taskName = cursor.getString(cursor.getColumnIndex(COLUMN_TASK_NAME))
                tasks.add(taskName)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return tasks
    }
}