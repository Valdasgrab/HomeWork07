package lt.vgrabauskas.homework07

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import lt.vgrabauskas.homework07.databinding.NoteBinding

class CustomAdapter(context: Context) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Note>()

    var maxId: Any = if (list.isEmpty()){
        -1
    } else {
        list.maxBy { note -> note.id }.id
    }
        private set
    fun add (vararg note: Note) {
        list.addAll(note)
        notifyDataSetChanged()
    }
    fun add (notes: List<Note>) {
        list.addAll(notes)
        notifyDataSetChanged()
    }

    fun update(index: Int, note: Note) {
        list.set(index, note)
        notifyDataSetChanged()
    }
    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun remove(vararg note: Note) {
        list.removeAll(note)
        notifyDataSetChanged()
    }

    fun remove(notes: List<Note>) {
        list.removeAll(notes)
        notifyDataSetChanged()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val binding: NoteBinding

        if (view == null) {
            binding = NoteBinding.inflate(inflater, parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = view.tag as NoteBinding
        }

        binding.idTextView.text = list[position].id.toString()
        binding.text01TextView.text = list[position].name
        binding.text02TextView.text = list[position].details
        binding.creationDateTextView.text = list[position].creationDate.toString()
        binding.updateDateTextView.text = list[position].updateDate.toString()
        return view
    }
    override fun getCount(): Int =list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()



}
