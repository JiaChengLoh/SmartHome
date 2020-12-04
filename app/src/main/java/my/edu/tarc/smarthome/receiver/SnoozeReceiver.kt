package my.edu.tarc.smarthome.receiver

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.text.format.DateUtils
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.FirebaseDatabase

class SnoozeReceiver: BroadcastReceiver() {
    var database = FirebaseDatabase.getInstance()
    //Getting Database Reference
    var databaseReference = database.reference

    //Getting Reference to Root Node

    var myLcd = database.getReference("PI_04_CONTROL/lcdtxt")
    var myBuzzer = database.getReference("PI_04_CONTROL/buzzer")

    private val REQUEST_CODE = 0

    override fun onReceive(context: Context, intent: Intent) {
        val triggerTime = SystemClock.elapsedRealtime() + DateUtils.MINUTE_IN_MILLIS

        val notifyIntent = Intent(context, AlarmReceiver::class.java)
        val notifyPendingIntent = PendingIntent.getBroadcast(
                context,
                REQUEST_CODE,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        AlarmManagerCompat.setExactAndAllowWhileIdle(
                alarmManager,
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,
                notifyPendingIntent
        )

        val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
        ) as NotificationManager
        notificationManager.cancelAll()


        myLcd.setValue("=====SNOOZE=====")
        myBuzzer.setValue("0")
    }

}