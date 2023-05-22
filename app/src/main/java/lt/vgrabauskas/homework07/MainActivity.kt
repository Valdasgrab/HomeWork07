package lt.vgrabauskas.homework07

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import lt.vgrabauskas.homework07.databinding.ActivityMainBinding
import java.time.LocalDateTime

class MainActivity : ActivityLifecycles() {

    private lateinit var adapter: CustomAdapter
    private var itemIndex = 1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notes = mutableListOf<Note>()
        setUpListView()
        generateListOfNotes(notes)
        updateAdapter(notes)


        setClickOpenNoteDetails()
        setClickOpenNoteActivity()
    }

//    private fun getAdapter(notes: MutableList<Note>) {
//        adapter = CustomAdapter(this)
//        adapter.add(notes)
//    }

    private fun generateListOfNotes(notes: MutableList<Note>) {
        for (note in 1..5) {
            notes.add(
                Note(
                    note,
                    "text01%04x".format(note),
                    "text02%09x".format(note)
                )
            )
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX, itemIndex)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        itemIndex = savedInstanceState.getInt(MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX)
    }


    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.noteListView.adapter = adapter
    }

    private fun updateAdapter(notes: MutableList<Note>) {
        adapter.add(notes)
        adapter.add(Note(101, "text01", "text02", LocalDateTime.now()))
//        adapter.add(
//            Note(102, "text01", "text02"),
//            Note(103, "text01", "text02"),
//            Note(104, "text01", "text02"),
//            Note(105, "text01", "text02"),
//        )
    }

    private fun setClickOpenNoteActivity() {
        binding.openNoteActivityButton.setOnClickListener {
            startActivityForResult.launch(Intent(this, NoteDetails::class.java))
        }
    }

    private fun setClickOpenNoteDetails() {
        binding.noteListView.setOnItemClickListener { adapterView, view, position, l ->
            val note: Note = adapterView.getItemAtPosition(position) as Note
            itemIndex = position

            val noteIntent = Intent(this, NoteDetails::class.java)
            noteIntent.putExtra(MAIN_ACTIVITY_ITEM_ID, note)
            startActivityForResult.launch(noteIntent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                NoteDetails.SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW -> {
                    val note: Note = getExtraFromParcelable(result.data)
                    adapter.add(note)
                }
                NoteDetails.SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE -> {
                    val item = getExtraFromParcelable(result.data)
                    if (itemIndex >= 0) {
                        adapter.update(itemIndex, item)
                    }
                }
            }
        }

    private fun getExtraFromParcelable(result: Intent?) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            result?.getParcelableExtra(MAIN_ACTIVITY_ITEM_ID, Note::class.java)
                ?: Note(0, "", "")
        } else {
            result?.getParcelableExtra(MAIN_ACTIVITY_ITEM_ID)
                ?: Note(0, "", "")
        }

    companion object {
        const val MAIN_ACTIVITY_ITEM_ID = "package lt.vgrabauskas.homework07_item_id"
        const val MAIN_ACTIVITY_ITEM_TEXT01 = "package lt.vgrabauskas.homework07_item_text01"
        const val MAIN_ACTIVITY_ITEM_TEXT02 = "package lt.vgrabauskas.homework07_item_text02"
        const val MAIN_ACTIVITY_ITEM_DATE = "package lt.vgrabauskas.homework07_item_text02"
        const val MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX =
            "package lt.vgrabauskas.homework07_save_instance_state_item_index"
    }
}
