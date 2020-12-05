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
import my.edu.tarc.smarthome.R
import my.edu.tarc.smarthome.ui.bathroom.BathroomViewModal

class DoorFragment: Fragment() {
    private lateinit var DoorViewModel: DoorViewModal

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


        return view
    }


}