package my.edu.tarc.smarthome.ui.reminder


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

import my.edu.tarc.smarthome.R


class ReminderFragment : Fragment() , View.OnClickListener{
    private lateinit var ReminderViewModel: ReminderViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_reminder, container, false)
        ReminderViewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)
        val textView: TextView = view.findViewById(R.id.text_reminder)
        //val btn_on: Button = view.findViewById(R.id.btn_reminder_on)
        //val btn_off: Button = view.findViewById(R.id.btn_panic_off)
        //textViewPanic.setText("Panic Button")
        //textViewPanic.setOnClickListener(this)
        //btn_on.setOnClickListener(this)
        //btn_off.setOnClickListener(this)

        ReminderViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }

}

