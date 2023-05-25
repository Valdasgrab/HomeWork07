package lt.vgrabauskas.homework07

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import lt.vgrabauskas.homework07.databinding.ActivityMainBinding
import java.time.LocalDateTime

class Notes : ActivityLifecycles() {

    private lateinit var adapter: CustomAdapter
    private var noteIndex = 1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notes = mutableListOf<Note>()
        setUpListView()
        updateAdapter(notes)


        setClickOpenNoteDetails()
        setClickOpenNoteActivity()
    }

//    private fun getAdapter(notes: MutableList<Note>) {
//        adapter = CustomAdapter(this)
//        adapter.add(notes)
//    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX, noteIndex)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        noteIndex = savedInstanceState.getInt(MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX)
    }


    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.noteListView.adapter = adapter
    }

    private fun updateAdapter(notes: MutableList<Note>) {
        adapter.add(notes)
        adapter.add(Note(101, "text01", "text02", LocalDateTime.now()))
        adapter.add(
            Note(102, "text01", "text02"),
            Note(103, "text01", "text02"),
            Note(104, "text01", "text02"),
            Note(105, "text01", "text02"),
        )
    }

    private fun setClickOpenNoteActivity() {
        binding.openNoteActivityButton.setOnClickListener {
            startActivityForResult.launch(Intent(this, NoteDetails::class.java))
        }
    }

    private fun setClickOpenNoteDetails() {
        binding.noteListView.setOnItemClickListener { adapterView, view, position, l ->
            val note: Note = adapterView.getItemAtPosition(position) as Note
            noteIndex = position

            val noteIntent = Intent(this, NoteDetails::class.java)
            noteIntent.putExtra(MAIN_ACTIVITY_ITEM_ID, note.id)
            noteIntent.putExtra(MAIN_ACTIVITY_ITEM_NAME, note.name)
            noteIntent.putExtra(MAIN_ACTIVITY_ITEM_DETAILS, note.details)
            startActivityForResult.launch(noteIntent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                NoteDetails.SECOND_ACTIVITY_NOTE_INTENT_RETURN_NEW -> {
                    val note = Note(
                        id = result.data
                            ?.getIntExtra(NoteDetails.SECOND_ACTIVITY_NOTE_ID, 0) ?: 0,
                        name = result.data
                            ?.getStringExtra(NoteDetails.SECOND_ACTIVITY_NOTE_NAME) ?: "",
                        details = result.data
                            ?.getStringExtra(NoteDetails.SECOND_ACTIVITY_NOTE_DETAILS) ?: ""
                    )

                    adapter.add(note)
                }

                NoteDetails.SECOND_ACTIVITY_NOTE_INTENT_RETURN_UPDATE -> {
                    val note = Note(
                        id = result.data
                            ?.getIntExtra(NoteDetails.SECOND_ACTIVITY_NOTE_ID, 0) ?: 0,
                        name = result.data
                            ?.getStringExtra(NoteDetails.SECOND_ACTIVITY_NOTE_NAME) ?: "",
                        details = result.data
                            ?.getStringExtra(NoteDetails.SECOND_ACTIVITY_NOTE_DETAILS) ?: ""
                    )

                    if (noteIndex >= 0) {
                        adapter.update(noteIndex, note)
                    }
                }
            }
        }



    companion object {
        const val MAIN_ACTIVITY_ITEM_ID = "package lt.vgrabauskas.homework07_item_id"
        const val MAIN_ACTIVITY_ITEM_NAME = "package lt.vgrabauskas.homework07_item_text01"
        const val MAIN_ACTIVITY_ITEM_DETAILS = "package lt.vgrabauskas.homework07_item_text02"
        const val MAIN_ACTIVITY_ITEM_DATE = "package lt.vgrabauskas.homework07_item_text02"
        const val MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX =
            "package lt.vgrabauskas.homework07_save_instance_state_item_index"
    }
}
