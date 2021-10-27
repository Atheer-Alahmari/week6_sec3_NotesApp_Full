package com.example.week6_sec3_notesapp_full

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.*

class MainActivity : AppCompatActivity() {
    lateinit var note_ED: EditText
    lateinit var sub_btn: Button
    lateinit var list_RV: RecyclerView
    var listNote=ArrayList<Notes>()
   lateinit var dbhr :MyDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        note_ED = findViewById(R.id.note_ED)
        sub_btn = findViewById(R.id.btn_submit)

        list_RV = findViewById(R.id.recycler_View)// Recycler View

        dbhr= MyDBHelper(this)




        sub_btn.setOnClickListener {
            var input = note_ED.text.toString()

            //----------------Save DB--------------
            // var status=
            if(input.isNotEmpty()) {
                dbhr.save_date(input)

                Toast.makeText(applicationContext, "Note Added ", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Please Enter a Note ", Toast.LENGTH_SHORT).show()

            }
            //---------------------------------------------------

           updateList()
            note_ED.text.clear()

        }
    }
    //--------------------------------------
    fun updateList() {
        listNote=dbhr.retrive()

        list_RV.adapter = RV_Adapter(this,listNote)
        list_RV.layoutManager = LinearLayoutManager(this)

        list_RV.scrollToPosition(listNote.size - 1)

    }

}
