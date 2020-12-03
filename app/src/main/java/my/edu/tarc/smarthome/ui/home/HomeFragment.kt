package my.edu.tarc.smarthome.ui.home

import android.os.Bundle
import android.util.Log
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


class HomeFragment : Fragment() , View.OnClickListener{

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

        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = view.findViewById(R.id.text_home)
        val btn_on: Button = view.findViewById(R.id.btn_on)
        val btn_off: Button = view.findViewById(R.id.btn_off_relay)
        btn_on.setOnClickListener(this)
        btn_off.setOnClickListener(this)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view
    }

    override fun onClick(view: View?) {
            when (view?.id) {
                R.id.btn_on -> {
                    //Setting Value
                    Log.i("Test: ","Hi")
                    myRef.setValue("1")
                }

                R.id.btn_off_relay -> {
                    //Setting Value
                    myRef.setValue("0")
                }
            }

    }

}

