package my.edu.tarc.smarthome.ui.medicine

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.smarthome.R
import my.edu.tarc.smarthome.databinding.FragmentMedicineBinding

import my.edu.tarc.smarthome.ui.bathroom.BathroomViewModal

class MedicineFragment: Fragment() {
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

    //private lateinit var medicineViewModel: MedicineViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding: FragmentMedicineBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_medicine, container, false
        )
        val viewModel = ViewModelProvider(this).get(MedicineViewModel::class.java)
        myLcd.setValue("====MEDICINE====")
        binding.medicineViewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        //val view = inflater.inflate(R.layout.fragment_medicine, container, false)
        //medicineViewModel = ViewModelProvider(this).get(MedicineViewModel::class.java)
        //val textView: TextView = view.findViewById(R.id.text_medicine)
        //val btn_on: Button = view.findViewById(R.id.btn_reminder_on)
        //val btn_off: Button = view.findViewById(R.id.btn_panic_off)
        //textViewPanic.setText("Panic Button")
        //textViewPanic.setOnClickListener(this)
        //btn_on.setOnClickListener(this)
        //btn_off.setOnClickListener(this)


        createChannel(
            getString(R.string.medicine_notification_channel_id),
            getString(R.string.medicine_notification_channel_name)
        )

        myLcdScr.setValue("1")
        myLcdbkB.setValue("5")
        myLcdbkG.setValue("5")
        myLcdbkR.setValue("5")
        myRelay.setValue("0")
        myBzr.setValue("0")

        return binding.root
    }

    private fun createChannel(channelId: String, channelName: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,

                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.breakfast_notification_channel_description)

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        fun newInstance() = MedicineFragment()
    }
}