package my.edu.tarc.smarthome.ui.slideshow

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_panic.*
import my.edu.tarc.smarthome.R

class PanicFragment : Fragment() , View.OnClickListener{

    private lateinit var slideshowViewModel: PanicViewModel

    var database = FirebaseDatabase.getInstance()

    //var ctx: Context? = null

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
        super.onCreate(savedInstanceState)
        slideshowViewModel = ViewModelProvider(this).get(PanicViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_panic, container, false)
        val textView: TextView = view.findViewById(R.id.text_slideshow)
        val textViewPanic: TextView = view.findViewById(R.id.textViewPanic)
        val btn_on: Button = view.findViewById(R.id.btn_panic_on)
        val btn_off: Button = view.findViewById(R.id.btn_panic_off)
        textViewPanic.text = "Press On if emergency"
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
                panicOn()
                textViewPanic.text = "Emergency!!!"
                Toast.makeText(getActivity(),"Emergency!",Toast.LENGTH_SHORT).show();

            }

            R.id.btn_panic_off -> {
                //Setting Value
                panicOff()
                textViewPanic.text = "Normal"
                //R.id.textViewPanic.setText = "Bye"
                Toast.makeText(getActivity(),"Normal~",Toast.LENGTH_SHORT).show();
            }
        }
    }


    private fun panicOn() {
        myLcdScr.setValue("1")
        myLcd.setValue("====EMERGENCY===")
        myBuzzer.setValue("1")

    }

    private fun panicOff() {
        myLcd.setValue("=====NORMAL=====")
        myBuzzer.setValue("0")
    }


    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }

}