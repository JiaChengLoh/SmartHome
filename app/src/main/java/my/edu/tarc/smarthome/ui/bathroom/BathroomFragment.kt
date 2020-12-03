package my.edu.tarc.smarthome.ui.bathroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import my.edu.tarc.smarthome.R
import my.edu.tarc.smarthome.ui.slideshow.PanicViewModel

class BathroomFragment: Fragment() {
    private lateinit var bathroomViewModel: BathroomViewModal

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        bathroomViewModel = ViewModelProvider(this).get(BathroomViewModal::class.java)
        val view = inflater.inflate(R.layout.fragment_bathroom, container, false)
        val textView: TextView = view.findViewById(R.id.text_bathroom)


        bathroomViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view
    }
}