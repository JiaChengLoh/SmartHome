package my.edu.tarc.smarthome.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.google.firebase.database.FirebaseDatabase
import my.edu.tarc.smarthome.R
import my.edu.tarc.smarthome.util.sendNotification

class AlarmReceiver: BroadcastReceiver() {

    var database = FirebaseDatabase.getInstance()
    //Getting Database Reference
    var databaseReference = database.reference

    //Getting Reference to Root Node

    var myLcdScr = database.getReference("PI_04_CONTROL/lcdscr")
    var myLcd = database.getReference("PI_04_CONTROL/lcdtxt")
    var myBuzzer = database.getReference("PI_04_CONTROL/buzzer")

    override fun onReceive(context: Context, intent: Intent) {
        // TODO: Step 1.10 [Optional] remove toast
//        Toast.makeText(context, context.getText(R.string.eggs_ready), Toast.LENGTH_SHORT).show()

        // TODO: Step 1.9 add call to sendNotification
        val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
                context.getText(R.string.medicine_ready).toString(),
                context
        )

        myLcdScr.setValue("1")
        myLcd.setValue("=TAKE==MEDICINE=")
        myBuzzer.setValue("1")

    }

}