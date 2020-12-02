package my.edu.tarc.smarthome.ui.gallery

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import my.edu.tarc.smarthome.R
import com.google.firebase.database.FirebaseDatabase

class StaircaseFragment : Fragment() , View.OnClickListener{

    private lateinit var staircaseViewModel: StaircaseViewModel
    var database = FirebaseDatabase.getInstance()

    //Getting Database Reference
    var databaseReference = database.reference

    //Getting Reference to Root Node

    var myRef = database.getReference("Student/Name")


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

        myRef.setValue("12345")
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {

                R.id.btn_test -> {

                    testSetValue()

                }

            }
        }
    }
    fun testSetValue() {

    }


}