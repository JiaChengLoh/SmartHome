package my.edu.tarc.smarthome.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.smarthome.R

class HomeFragment : Fragment(){

    private lateinit var homeViewModel: HomeViewModel

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

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        //val textView: TextView = view.findViewById(R.id.text_home)

        myLcd.setValue("======Home======")
        myLcdScr.setValue("1")
        myLcdbkB.setValue("5")
        myLcdbkG.setValue("5")
        myLcdbkR.setValue("5")
        myRelay.setValue("0")
        myBzr.setValue("0")

        return view
    }
}

