package lt.vgrabauskas.homework07

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NoteDetails: ActivityLifecycles() {

    lateinit var idEditText: EditText
    lateinit var noteNameEditText: EditText
    lateinit var detailsEditText: EditText
    lateinit var dateAndTime: EditText
    lateinit var closeButton: Button
    lateinit var saveButton: Button

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

    private fun getIntentExtra() {
        idEditText.setText(
            intent.getIntExtra(Notes.MAIN_ACTIVITY_ITEM_ID, 0).toString()
        )
        noteNameEditText.setText(
            intent.getStringExtra(Notes.MAIN_ACTIVITY_ITEM_TEXT01)
        )
        detailsEditText.setText(
            intent.getStringExtra(Notes.MAIN_ACTIVITY_ITEM_TEXT02)
        )
    }
    private fun setClickListenerOfSaveButton() {
        saveButton.setOnClickListener {
            val finishIntent = Intent()
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_ID, (idEditText.text.toString()).toInt())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT01, noteNameEditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT02, detailsEditText.text.toString())
//            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_DATE, )
            setResult(RESULT_OK, finishIntent)
            finish()
        }
    }
    companion object {
        const val SECOND_ACTIVITY_ITEM_ID = "package lt.vcs.androidtopics.secondactivity_item_id"
        const val SECOND_ACTIVITY_ITEM_TEXT01 = "package lt.vcs.androidtopics.secondactivity_item_text01"
        const val SECOND_ACTIVITY_ITEM_TEXT02 = "package lt.vcs.androidtopics.secondactivity_item_text02"
        const val SECOND_ACTIVITY_ITEM_DATE = "package lt.vcs.androidtopics.secondactivity_item_text03"

    }
    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            finish()
        }
    }
}