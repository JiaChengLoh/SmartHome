package my.edu.tarc.smarthome.ui.slideshow

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_panic.*
import my.edu.tarc.smarthome.R
import java.util.*
import kotlin.concurrent.schedule

class PanicFragment : Fragment() , View.OnClickListener{

    private lateinit var slideshowViewModel: PanicViewModel

    var database = FirebaseDatabase.getInstance()

    //var ctx: Context? = null

    //Getting Database Reference
    var databaseReference = database.reference

    //Getting Reference to Root Node

    var myLcd = database.getReference("PI_04_CONTROL/lcdtxt")
    var myLcdScr = database.getReference("PI_04_CONTROL/lcdscr")
    var myLcdbkB = database.getReference("PI_04_CONTROL/lcdbkB")
    var myLcdbkG = database.getReference("PI_04_CONTROL/lcdbkG")
    var myLcdbkR = database.getReference("PI_04_CONTROL/lcdbkR")
    var myRelay = database.getReference("PI_04_CONTROL/relay1")
    var myBzr = database.getReference("PI_04_CONTROL/buzzer")


    companion object {
        private const val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    }

    private lateinit var txtView: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var myLongitude: Double? = null
    private var myLatitude: Double? = null



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
        textViewPanic.text = "Normal"

        myLcd.setValue("=====NORMAL=====")
        myLcdScr.setValue("1")
        myLcdbkB.setValue("5")
        myLcdbkG.setValue("5")
        myLcdbkR.setValue("5")
        myRelay.setValue("0")
        myBzr.setValue("0")

        return view
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_panic_on-> {
                //Setting Value
                panicOn()
                textViewPanic.text = "Emergency!!!"
                Toast.makeText(getActivity(),"Emergency!",Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(),"Sending message",Toast.LENGTH_SHORT).show();


                Timer().schedule(2000) {
                    viewMap()
                }


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
        myLcd.setValue("====EMERGENCY===")
        myBzr.setValue("1")

    }

    private fun panicOff() {
        myLcd.setValue("=====NORMAL=====")
        myBzr.setValue("0")
    }


    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }





    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    getLocation()
            }
        }
    }

    private fun viewMap() {
        Log.i("LOG", "$myLongitude, $myLatitude")

        val smsBody = StringBuffer()
        smsBody.append("http://maps.google.com?q=")
        smsBody.append(4.468468468468468)
        smsBody.append(",")
        smsBody.append(101.17239240586088)
        try {
            val mobile = "60175998355"
            val msg = smsBody.toString()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=$mobile&text=$msg")))
        } catch (e: java.lang.Exception) {
            //whatsapp app not install
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
            location?.let {
                myLongitude = it.longitude
                myLatitude = it.latitude
            }
        }
    }


}