package com.wxy3265.graduate

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name: String, version: Int): SQLiteOpenHelper(
    context, name, null, version) {
    private val createStudent = "create table Student (" +
            " id integer primary key autoincrement," +
            "name text," +
            "birth text," +
            "phone text," +
            "qq text," +
            "wx text," +
            "mail text," +
            "wb text," +
            "loc text," +
            "city text," +
            "school text)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createStudent)
        Toast.makeText(context, "本地数据库已更新/创建", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("drop table if exists Student")
        onCreate(db)
    }
}
