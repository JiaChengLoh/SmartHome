package my.edu.tarc.smarthome.ui.bathroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.smarthome.R
import my.edu.tarc.smarthome.ui.slideshow.PanicViewModel

class BathroomFragment: Fragment() , View.OnClickListener{
    private lateinit var bathroomViewModel: BathroomViewModal


    var database = FirebaseDatabase.getInstance()

    //var ctx: Context? = null

    //Getting Database Reference
    var databaseReference = database.reference

    //Getting Reference to Root Node

    var myLcdScr = database.getReference("PI_04_CONTROL/lcdscr")
    var myLcd = database.getReference("PI_04_CONTROL/lcdtxt")
    var myRelay = database.getReference("PI_04_CONTROL/relay")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_bathroom, container, false)
        bathroomViewModel = ViewModelProvider(this).get(BathroomViewModal::class.java)
        val textView: TextView = view.findViewById(R.id.text_bathroom)
        val btn_on: Button = view.findViewById(R.id.btn_bathroom_on)
        //val btn_off: Button = view.findViewById(R.id.btn_panic_off)
        //textViewPanic.setText("Panic Button")
        //textViewPanic.setOnClickListener(this)
        btn_on.setOnClickListener(this)
        //btn_off.setOnClickListener(this)

        bathroomViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_bathroom_on-> {
                //Setting Value
                bathroomOn()
                Toast.makeText(getActivity(),"Light ON", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private fun bathroomOn() {
        myLcdScr.setValue("1")
        myLcd.setValue("===LIGHT===ON===")
        myRelay.setValue("1")

    }

    private fun panicOff() {
        //myLcd.setValue("=====NORMAL=====")
       // myBuzzer.setValue("0")
    }


    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }
}