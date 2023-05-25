package lt.vgrabauskas.homework07

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NoteDetails: ActivityLifecycles() {

    lateinit var idEditText: EditText
    lateinit var noteNameEditText: EditText
    lateinit var detailsEditText: EditText
    lateinit var closeButton: Button
    lateinit var saveButton: Button
    private var finishIntentStatus = SECOND_ACTIVITY_NOTE_INTENT_RETURN_UPDATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        idEditText = findViewById(R.id.idEditText)
        noteNameEditText = findViewById(R.id.text01EditText)
        detailsEditText = findViewById(R.id.text02EditText)
        closeButton = findViewById(R.id.closeButton)
        saveButton = findViewById(R.id.saveButton)


        getIntentExtra()
        setClickListenerOfSaveButton()
        setClickListenerOfCloseButton()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(SECOND_ACTIVITY_NOTE_ID, idEditText.text.toString())
            putString(SECOND_ACTIVITY_NOTE_NAME, noteNameEditText.text.toString())
            putString(SECOND_ACTIVITY_NOTE_DETAILS, detailsEditText.text.toString())
            putInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS, finishIntentStatus)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            idEditText.setText(this.getString(SECOND_ACTIVITY_NOTE_ID))
            noteNameEditText.setText(this.getString(SECOND_ACTIVITY_NOTE_NAME))
            detailsEditText.setText(this.getString(SECOND_ACTIVITY_NOTE_DETAILS))
            finishIntentStatus = this.getInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS)
        }
    }

    private fun getIntentExtra() {
        val noteId: Int = intent.getIntExtra(Notes.MAIN_ACTIVITY_ITEM_ID, -1)
        val noteName = intent.getStringExtra(Notes.MAIN_ACTIVITY_ITEM_NAME) ?: ""
        val noteDetails = intent.getStringExtra(Notes.MAIN_ACTIVITY_ITEM_DETAILS) ?: ""

        if (noteId >= 0) {
            idEditText.setText(noteId.toString())
            noteNameEditText.setText(noteName)
            detailsEditText.setText(noteDetails)
        } else {
            finishIntentStatus = SECOND_ACTIVITY_NOTE_INTENT_RETURN_NEW
        }
    }
    private fun setClickListenerOfSaveButton() {
        saveButton.setOnClickListener {
            val finishIntent = Intent()
            finishIntent.putExtra(SECOND_ACTIVITY_NOTE_ID, (idEditText.text.toString()).toInt())
            finishIntent.putExtra(SECOND_ACTIVITY_NOTE_NAME, noteNameEditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_NOTE_DETAILS, detailsEditText.text.toString())
            setResult(finishIntentStatus, finishIntent)
            finish()
        }
    }
    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            finish()
        }
    }
    companion object {
        const val SECOND_ACTIVITY_NOTE_ID = "package lt.vgrabauskas.androidtopics.note_details_note_id"
        const val SECOND_ACTIVITY_NOTE_NAME = "package lt.vgrabauskas.androidtopics.note_details_note_name"
        const val SECOND_ACTIVITY_NOTE_DETAILS = "package lt.vgrabauskas.androidtopics.note_details_note_details"
        const val SECOND_ACTIVITY_FINISH_INTENT_STATUS =
            "lt.vgrabauskas.androidtopics.note_details_finish_intent_status"
        const val SECOND_ACTIVITY_NOTE_INTENT_RETURN_NEW = 101
        const val SECOND_ACTIVITY_NOTE_INTENT_RETURN_UPDATE = 102
    }


}