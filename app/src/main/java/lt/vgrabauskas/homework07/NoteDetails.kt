package lt.vgrabauskas.homework07

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NoteDetails : ActivityLifecycles() {

    lateinit var idEditText: EditText
    lateinit var noteNameEditText: EditText
    lateinit var detailsEditText: EditText
    lateinit var closeButton: Button
    lateinit var saveButton: Button
    private var finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE

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
            putString(SECOND_ACTIVITY_ITEM_ID, idEditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT01, noteNameEditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT02, detailsEditText.text.toString())
            putInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS, finishIntentStatus)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            idEditText.setText(getString(SECOND_ACTIVITY_ITEM_ID))
            noteNameEditText.setText(getString(SECOND_ACTIVITY_ITEM_TEXT01))
            detailsEditText.setText(getString(SECOND_ACTIVITY_ITEM_TEXT02))
            finishIntentStatus = this.getInt(SECOND_ACTIVITY_FINISH_INTENT_STATUS)
        }
    }

    private fun getIntentExtra() {
        if (intent.hasExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID)) {
            var note: Note = getExtraFromParcelable(intent)
            idEditText.setText(note.id.toString())
            noteNameEditText.setText(note.name)
            detailsEditText.setText(note.details)
        } else {
            finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW
        }
    }

    private fun getExtraFromParcelable(result: Intent?) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            result?.getParcelableExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, Note::class.java)
                ?: Note(0, "", "")
        } else {
            result?.getParcelableExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID)
                ?: Note(0, "", "")
        }

    private fun setClickListenerOfSaveButton() {
        saveButton.setOnClickListener {
            val finishIntent = Intent()
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_ID, (idEditText.text.toString()).toInt())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT01, noteNameEditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT02, detailsEditText.text.toString())
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
        const val SECOND_ACTIVITY_ITEM_ID = "package lt.vgrabauskas.homework07.secondactivity_item_id"
        const val SECOND_ACTIVITY_ITEM_TEXT01 =
            "package lt.vgrabauskas.homework07.secondactivity_item_text01"
        const val SECOND_ACTIVITY_ITEM_TEXT02 =
            "package lt.vgrabauskas.homework07.secondactivity_item_text02"
        const val SECOND_ACTIVITY_FINISH_INTENT_STATUS =
            "package lt.vgrabauskas.homework07.secondactivity_finish_intent_status"
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW = 101
        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE = 102

    }


}