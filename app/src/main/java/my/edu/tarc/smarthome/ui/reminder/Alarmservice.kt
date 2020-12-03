package my.edu.tarc.smarthome.ui.reminder

import android.app.AlarmManager
import android.app.IntentService
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

class Alarmservice : IntentService(Alarmservice::class.java.simpleName) {
   private val notificationId = System.currentTimeMillis().toInt()
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onHandleIntent(intent: Intent?) {
       val action=intent!!.action


        //stop alarm sound
        if(action == ACTION_STOP_ALARM){
            if(AlarmReceiver.taskRingtone!!.isPlaying){
                AlarmReceiver.taskRingtone!!.stop()
                AlarmReceiver.vibrator!!.cancel()
            }
        }
        //snooze
        else if(action== ACTION_SNOOZE_ALARM){
            snoozeAlarm()
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun snoozeAlarm() {
        //cancel previous alarm tone
        if(AlarmReceiver.taskRingtone!!.isPlaying){
            AlarmReceiver.taskRingtone!!.stop()
            AlarmReceiver.vibrator!!.cancel()
        }

        //remind the user in 2 minutes
        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, notificationId, intent, 0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,Calendar.getInstance().timeInMillis +5*6000,pendingIntent)
    }
}