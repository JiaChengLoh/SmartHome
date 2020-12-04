package my.edu.tarc.smarthome.ui.bathroom

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_bathroom.*
import kotlinx.android.synthetic.main.nav_header_main.*
import my.edu.tarc.smarthome.R

class BathroomFragment: Fragment() , SensorEventListener {
    private lateinit var bathroomViewModel: BathroomViewModal

    var database = FirebaseDatabase.getInstance()

    //Getting Database Reference
    var databaseReference = database.reference

    //Getting Reference to Root Node

    var myLcdScr = database.getReference("PI_04_CONTROL/lcdscr")
    var myLcd = database.getReference("PI_04_CONTROL/lcdtxt")
    var myRelay = database.getReference("PI_04_CONTROL/relay1")
    var myLed = database.getReference("PI_04_CONTROL/ledlgt")
    var myBzr = database.getReference("PI_04_CONTROL/buzzer")

    private lateinit var mSensorManager: SensorManager
    private var mSensors: Sensor? = null
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_bathroom, container, false)
        bathroomViewModel = ViewModelProvider(this).get(BathroomViewModal::class.java)
        mSensorManager = this.activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        //mSensorManager = view.getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        Define the sensor
        mSensors = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        bathroomViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view
    }

    private fun bathroomOff() {
        myLcdScr.setValue("1")
        myLcd.setValue("===BUZZER==OFF===")
        myBzr.setValue("0")
    }

    override fun onSensorChanged(p0: SensorEvent?) {
//        Sensor change value
        val millibarsOfPressure = p0!!.values[0]
        if (p0.sensor.type == Sensor.TYPE_LIGHT)
            Toast.makeText(this.activity, "" + millibarsOfPressure + " lx", Toast.LENGTH_SHORT).show()
                if (millibarsOfPressure <= 10) {
                    myLed.setValue("1")
                    bathroom_image.setImageResource(R.drawable.open_turned_on_light)
                    bathroom_text.text = "LED Light ON"
                }
                else {
                    myLed.setValue("0")
                    bathroom_image.setImageResource(R.drawable.open_turned_off_light)
                    bathroom_text.text = "LED Light OFF"
                }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mSensors, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }
}