package com.sergio994350.userlistcheck.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sergio994350.userlistcheck.R

class EditUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        Log.d("logging", mode.toString())

        val dataUserId = intent.getStringExtra(EXTRA_DATA_USER_ID)?.toInt()
        Log.d("logging", "user # $dataUserId")
    }

    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_DATA_USER_ID = "extra_data_user_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        fun newIntentEditDataUser(context: Context, dataUserId: Int): Intent {
            val intent = Intent(context, EditUserActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_DATA_USER_ID, dataUserId)
            return intent
        }

        fun newIntentAddDataUser(context: Context): Intent {
            val intent = Intent(context, EditUserActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }
    }

}

