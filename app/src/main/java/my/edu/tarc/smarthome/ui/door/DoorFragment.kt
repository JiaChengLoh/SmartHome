package my.edu.tarc.smarthome.ui.door

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.smarthome.R
import my.edu.tarc.smarthome.ui.bathroom.BathroomViewModal

class DoorFragment: Fragment() {
    private lateinit var DoorViewModel: DoorViewModal

    var database = FirebaseDatabase.getInstance()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_door, container, false)
        DoorViewModel = ViewModelProvider(this).get(DoorViewModal::class.java)

        val myWebView: WebView = view.findViewById(R.id.webview)
        myWebView.loadUrl("http://139.59.100.218:5000/")

        myLcd.setValue("======DOOR======")
        myLcdScr.setValue("1")
        myLcdbkB.setValue("5")
        myLcdbkG.setValue("5")
        myLcdbkR.setValue("5")
        myRelay.setValue("0")
        myBzr.setValue("0")

        return view
    }


}