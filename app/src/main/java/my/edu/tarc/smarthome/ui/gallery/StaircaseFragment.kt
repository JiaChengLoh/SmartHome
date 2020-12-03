package my.edu.tarc.smarthome.ui.gallery

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
import my.edu.tarc.smarthome.R
import com.google.firebase.database.FirebaseDatabase

class StaircaseFragment : Fragment() , View.OnClickListener{

    private lateinit var staircaseViewModel: StaircaseViewModel
    var database = FirebaseDatabase.getInstance()

    //Getting Database Reference
    var databaseReference = database.reference

    //Getting Reference to Root Node

    var myLcd = database.getReference("PI_04_CONTROL/lcdtxt")
    var myRelay = database.getReference("PI_04_CONTROL/relay1")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        staircaseViewModel =
                ViewModelProvider(this).get(StaircaseViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_staircase, container, false)
        val textView: TextView = view.findViewById(R.id.text_gallery)
        val btn_on: Button = view.findViewById(R.id.btn_on_relay)
        val btn_off: Button = view.findViewById(R.id.btn_off_relay)
        btn_on.setOnClickListener(this)
        btn_off.setOnClickListener(this)
        staircaseViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_on_relay -> {
                //Setting Value
                Log.i("Test: ","Hi")
                testSetValueOn()
            }

            R.id.btn_off_relay -> {
                //Setting Value
                testSetValueOff()
            }
        }

    }

    fun testSetValueOn() {

        myLcd.setValue("====IN===USE====")
        myRelay.setValue("1")
    }

    fun testSetValueOff() {
        myLcd.setValue("====AVAILABLE===")
        myRelay.setValue("0")
    }

}