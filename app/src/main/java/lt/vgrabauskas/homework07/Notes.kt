package lt.vgrabauskas.homework07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView

class Notes : AppCompatActivity() {

    lateinit var adapter: CustomAdapter
    lateinit var openNoteActivityButton: Button
    lateinit var noteListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openNoteActivityButton = findViewById(R.id.openNoteActivityButton)
        noteListView = findViewById(R.id.noteListView)
        val notes = mutableListOf<Note>()

        generateListOfNotes(notes)
        getAdapter(notes)


        noteListView.adapter = adapter
    }

    private fun getAdapter(notes: MutableList<Note>) {
        adapter = CustomAdapter(this)
        adapter.add(notes)
    }
    private fun generateListOfNotes(notes: MutableList<Note>) {
        for (note in 1..5) {
            notes.add(
                Note(
                    note.toLong(),
                    "text01%04x".format(note),
                    "text02%09x".format(note)
                )
            )
        }
    }
}