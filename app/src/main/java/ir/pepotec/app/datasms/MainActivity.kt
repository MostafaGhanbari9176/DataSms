package ir.pepotec.app.datasms


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telephony.SmsManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        send.setOnClickListener {
            checkData()
        }

    }

    private fun checkData() {
        val p = phone.text.toString().trim()
        if (p.length != 11) {
            phone.apply {
                error = "Error"
                requestFocus()
            }
            return
        }

        val m = message.text.toString().trim()
        if (m.isEmpty()) {
            message.apply {
                error = "Error"
                requestFocus()
            }
            return
        }
        Toast.makeText(this, "send", Toast.LENGTH_SHORT).show()
        message.setText("")
        sendSms(p, m)

    }

    /********************************* Primary ********************************/
    private fun sendSms(phone: String, message: String) {
        val smsManager = SmsManager.getDefault()

        /*use same port in receiver tag in manifest*/
        val port: Short = 9176

        smsManager.sendDataMessage(phone, null, port, message.toByteArray(), null, null)
    }
    /********************************* ...... ********************************/
}
