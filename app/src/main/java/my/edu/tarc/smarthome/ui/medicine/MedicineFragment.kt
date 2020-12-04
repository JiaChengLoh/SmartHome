package my.edu.tarc.smarthome.ui.medicine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.smarthome.R
import my.edu.tarc.smarthome.ui.bathroom.BathroomViewModal

class MedicineFragment: Fragment() {
    private lateinit var medicineViewModel: MedicineViewModal


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_medicine, container, false)
        medicineViewModel = ViewModelProvider(this).get(MedicineViewModal::class.java)
        val textView: TextView = view.findViewById(R.id.text_medicine)
        //val btn_on: Button = view.findViewById(R.id.btn_reminder_on)
        //val btn_off: Button = view.findViewById(R.id.btn_panic_off)
        //textViewPanic.setText("Panic Button")
        //textViewPanic.setOnClickListener(this)
        //btn_on.setOnClickListener(this)
        //btn_off.setOnClickListener(this)

        medicineViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view
    }


    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }
}