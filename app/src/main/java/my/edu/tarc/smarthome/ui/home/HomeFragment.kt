package my.edu.tarc.smarthome.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.smarthome.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    var database = FirebaseDatabase.getInstance()

    //Getting Database Reference
    var databaseReference = database.reference

    //Getting Reference to Root Node
    var myRef = database.getReference("PI_04_CONTROL/buzzer")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Setting Value
        myRef.setValue("1")

        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }


}