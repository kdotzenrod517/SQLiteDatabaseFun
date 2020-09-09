package com.kdotz.databasedemo

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val sqLiteDatabase : SQLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null)
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INT(4), id INTEGER PRIMARY KEY)")

//            sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Krista', 23)")
//            sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Krista', 43)")
//            sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Andrew', 22)")

            sqLiteDatabase.execSQL("DELETE FROM newUsers WHERE id = 3")

            val c: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM newUsers", null)

            val nameIndex = c.getColumnIndex("name")
            val ageIndex = c.getColumnIndex("age")
            val idIndex = c.getColumnIndex("id")

            c.moveToFirst()

            while (!c.isAfterLast) {
                println("First and age, and id ${c.getString(nameIndex)} ${Integer.toString(c.getInt(ageIndex))} ${Integer.toString(c.getInt(idIndex))}")
                c.moveToNext()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
