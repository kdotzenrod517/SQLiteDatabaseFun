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
            val eventDatabase: SQLiteDatabase = this.openOrCreateDatabase("Events", MODE_PRIVATE, null)
            eventDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR, year INT(4))")

//            eventDatabase.execSQL("INSERT INTO events (event, year) VALUES ('Declaration of Independence', 1776)")
            eventDatabase.execSQL("INSERT INTO events (event, year) VALUES ('Coachella', 2018)")

            val c: Cursor = eventDatabase.rawQuery("SELECT * FROM events", null)

            val eventIndex = c.getColumnIndex("event")
            val yearIndex = c.getColumnIndex("year")

            c.moveToFirst()

            while (!c.isAfterLast) {
                println("Event and Year ${c.getString(eventIndex)} ${Integer.toString(c.getInt(yearIndex))}")
                c.moveToNext()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
