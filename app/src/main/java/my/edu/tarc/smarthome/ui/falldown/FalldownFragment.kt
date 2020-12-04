package my.edu.tarc.smarthome.ui.falldown


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_falldown.*
import my.edu.tarc.smarthome.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

class FalldownFragment: Fragment() , SensorEventListener {
    private lateinit var mSensorManager: SensorManager
    private var mSensors: Sensor? = null
    private var lastMovementFall: Long = 0
    private var movementStart: Long = 0
    private var ct = 2

    private lateinit var falldownViewModel: FalldownViewModal

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_falldown, container, false)
        falldownViewModel = ViewModelProvider(this).get(FalldownViewModal::class.java)
        //val btn_on: Button = view.findViewById(R.id.btn_bathroom_on)
        //val btn_off: Button = view.findViewById(R.id.btn_panic_off)
        //textViewPanic.setText("Panic Button")
        //textViewPanic.setOnClickListener(this)
        //btn_on.setOnClickListener(this)
        //btn_off.setOnClickListener(this)

        mSensorManager = this.activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        Define the sensor
        if (mSensors == null) {
            mSensors = if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            } else {
                null
            }
        }

        mSensors?.also { light ->
            mSensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)
        }

        return view
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {
//        Sensor change value
        val millibarsOfPressure = p0!!.values[0]
        if (p0.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            //Toast.makeText(this, "" + millibarsOfPressure + " lx", Toast.LENGTH_SHORT).show()

            movementStart = System.currentTimeMillis()

            val loX = p0.values[0]
            val loY = p0.values[1]
            val loZ = p0.values[2]

            val loAccelerationReader = sqrt(
                loX.toDouble().pow(2.0)
                        + loY.toDouble().pow(2.0)
                        + loZ.toDouble().pow(2.0)
            )

            val precision = DecimalFormat("0.00")
            val ldAccRound = java.lang.Double.parseDouble(precision.format(loAccelerationReader))

            if (ldAccRound > 0.3 && ldAccRound < 1.2 && (movementStart - lastMovementFall) > 1000) {
                val timeStamp = SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss").format(Date(System.currentTimeMillis()))
                val duration = (System.currentTimeMillis() - movementStart).toString()

                lastMovementFall = System.currentTimeMillis()
                falldown_image.setImageResource(R.drawable.falldown)
                falldown_text.text = "Fall down"

                //Toast.makeText(getActivity(),"Fall down", Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        Register the sensor on resume of the activity
        mSensorManager.registerListener(this, mSensors, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
//        unregister the sensor onPause else it will be active even if the activity is closed
        mSensorManager.unregisterListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        mSensorManager.unregisterListener(this)
        //stopSelf()
    }


}