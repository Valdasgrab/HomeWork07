package lt.vgrabauskas.homework07

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts

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
        setClickOpenNoteDetails()
        setClickOpenNoteActivity()
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

    private fun setClickOpenNoteDetails() {
        noteListView.setOnItemClickListener { adapterView, view, position, l ->
            val note: Note = adapterView.getItemAtPosition(position) as Note

            val itemIntent = Intent(this, NoteDetails::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_ID, note.id)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT01, note.name)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT02, note.details)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_DATE, note.updateNameAndDetails(note.name,note.details))
            startActivity(itemIntent)
        }
    }

    companion object {
        const val MAIN_ACTIVITY_ITEM_ID = "package lt.vcs.androidtopics_item_id"
        const val MAIN_ACTIVITY_ITEM_TEXT01 = "package lt.vcs.androidtopics_item_text01"
        const val MAIN_ACTIVITY_ITEM_TEXT02 = "package lt.vcs.androidtopics_item_text02"
        const val MAIN_ACTIVITY_ITEM_DATE = "package lt.vcs.androidtopics_item_text02"
    }

    private fun setClickOpenNoteActivity() {
        openNoteActivityButton.setOnClickListener {
            startActivityForResult.launch(Intent(this, NoteDetails::class.java))
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    val note = Note(
                        id = result.data
                            ?.getIntExtra(NoteDetails.SECOND_ACTIVITY_ITEM_ID, 0)?.toLong() ?: 0,
                        name = result.data
                            ?.getStringExtra(NoteDetails.SECOND_ACTIVITY_ITEM_TEXT01) ?: "",
                        details = result.data
                            ?.getStringExtra(NoteDetails.SECOND_ACTIVITY_ITEM_TEXT02) ?: "",
                        )
                    adapter.add(note)
                }
            }
        }
}
