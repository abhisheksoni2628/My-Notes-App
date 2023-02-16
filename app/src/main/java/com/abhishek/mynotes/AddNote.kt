package com.abhishek.mynotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import com.abhishek.mynotes.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private lateinit var note: com.abhishek.mynotes.Models.Note
    private lateinit var oldNote: com.abhishek.mynotes.Models.Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            oldNote = intent.getSerializableExtra("current_note") as com.abhishek.mynotes.Models.Note
            binding.etTitle.setText(oldNote.title)
            binding.etNote.setText(oldNote.note)
            isUpdate = true

        }catch (e : Exception){

            e.printStackTrace()

        }

        binding.imgSaveBtn.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val note_des = binding.etNote.text.toString()

            if (title.isNotEmpty() || note_des.isNotEmpty()){

                val formatter = SimpleDateFormat("EEE, d MM yyyy HH:mm a")

                if (isUpdate) {
                    note = com.abhishek.mynotes.Models.Note(
                        oldNote.id, title, note_des, formatter.format(Date())
                    )
                }
                else{
                    note = com.abhishek.mynotes.Models.Note(
                        null,title, note_des, formatter.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()

            }
            else{

                Toast.makeText(this@AddNote, "Please Enter Some Data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

        }

        binding.imgBackBtn.setOnClickListener {

            onBackPressed()

        }

    }
}