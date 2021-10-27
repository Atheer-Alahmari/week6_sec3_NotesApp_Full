package com.example.week6_sec3_notesapp_full

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class MyDBHelper (context: Context): SQLiteOpenHelper(context,"details.db", null ,1) {

    var sqliteDatabase: SQLiteDatabase =writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table massage (Myid  integer primary key autoincrement, Note text )")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

//-------------------------------------------------------------------------

    fun save_date(input: String): Long {
        var cv= ContentValues()
        cv.put("Note",input)

        var status=sqliteDatabase.insert("massage",null,cv)
        return status
    }

    //-------------------------------------------------------------------------

    fun retrive(): ArrayList<Notes> {
        var c: Cursor =sqliteDatabase.query("massage",null,null, null,null,null,null)

        c.moveToFirst()

        var listNote= ArrayList<Notes>()

        while (!c.isAfterLast) {
            var id   =c.getInt(c.getColumnIndex("Myid"))
            var note = c.getString(c.getColumnIndex("Note"))

            var obNotes=Notes(id,note)
            listNote.add(obNotes)

            c.moveToNext()
        }
        return listNote

    }

    //-------------------------------------------------------------------------

    fun update(idn: Int, note:String) {
        sqliteDatabase = writableDatabase
        var cv = ContentValues()
            cv.put("Note",note)
        sqliteDatabase.update("massage", cv, "Myid = ?", arrayOf(idn.toString()))


    }

    //-------------------------------------------------------------------------

    fun delete(idn: Int) {
        sqliteDatabase = writableDatabase
        sqliteDatabase.delete("massage", "Myid = ?", arrayOf(idn.toString()))


    }


}

