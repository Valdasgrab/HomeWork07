package lt.vgrabauskas.homework07

import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NoteDetails: ActivityLifecycles() {

    lateinit var idEditText: EditText
    lateinit var noteNameEditText: EditText
    lateinit var detailsEditText: EditText
    lateinit var closeButton: Button
    lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }
}