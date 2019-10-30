package ir.pepotec.app.datasms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle: Bundle? = intent?.extras

        bundle?.let {
            val pdus = it.get("pdus") as Array<Any>

            var message = ""
            var phone = ""

            for (i in pdus.indices) {
                val smsMessage = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                if (i == 0)
                    phone = smsMessage.originatingAddress ?: ""
                message += smsMessage.userData.toString(Charsets.UTF_8)
            }

            Toast.makeText(context, "message : $message ; receive from : $phone", Toast.LENGTH_LONG).show()
        }

    }
}