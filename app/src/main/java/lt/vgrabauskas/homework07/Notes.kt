package lt.vgrabauskas.homework07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Notes : AppCompatActivity() {

    lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}