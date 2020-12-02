package my.edu.tarc.smarthome.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import my.edu.tarc.smarthome.R

class StaircaseFragment : Fragment() {

    private lateinit var staircaseViewModel: StaircaseViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        staircaseViewModel =
                ViewModelProvider(this).get(StaircaseViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_staircase, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        staircaseViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}