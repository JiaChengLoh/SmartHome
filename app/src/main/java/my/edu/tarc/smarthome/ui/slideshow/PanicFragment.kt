package my.edu.tarc.smarthome.ui.slideshow

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

class PanicFragment : Fragment() , View.OnClickListener{

    private lateinit var slideshowViewModel: PanicViewModel

    var database = FirebaseDatabase.getInstance()

    //Getting Database Reference
    var databaseReference = database.reference

    //Getting Reference to Root Node


    var myLcdScr = database.getReference("PI_04_CONTROL/lcdscr")
    var myLcd = database.getReference("PI_04_CONTROL/lcdtxt")
    var myBuzzer = database.getReference("PI_04_CONTROL/buzzer")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel = ViewModelProvider(this).get(PanicViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_panic, container, false)
        val textView: TextView = view.findViewById(R.id.text_slideshow)
        val textViewPanic: TextView = view.findViewById(R.id.textViewPanic)
        val btn_on: Button = view.findViewById(R.id.btn_panic_on)
        val btn_off: Button = view.findViewById(R.id.btn_panic_off)
        btn_on.setOnClickListener(this)
        btn_off.setOnClickListener(this)

        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_panic_on-> {
                //Setting Value
                PanicOn()
            }

            R.id.btn_panic_off -> {
                //Setting Value
                PanicOff()
            }
        }
    }


    fun PanicOn() {
        myLcdScr.setValue("1")
        myLcd.setValue("====EMERGENCY===")
        myBuzzer.setValue("1")
    }

    fun PanicOff() {
        myLcd.setValue("=====NORMAL=====")
        myBuzzer.setValue("0")
    }
}