package com.marcal.recordkeeper.running

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.marcal.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRunningRecordBinding
    private val runningSharedPref by lazy { getSharedPreferences("running_preferences", Context.MODE_PRIVATE) }
    private val distance: String? by lazy { intent.getStringExtra("Distance") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        displayRecord()

        // default preference file without name (name is fully qualified package name + _preferences.xml
        // it s application wide preferences file
        val applicationPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        applicationPreferences.edit {
            putString("My key value here", "My value stored in default shared preferences")
        }

        // activity preferences (1 file per activity)
        // 1 file per activity
        // package name + activity name + .xml
        // seldom use, only if your activity requires a config specific to theis view
        val activityPreferences = getPreferences(Context.MODE_PRIVATE)
        activityPreferences.edit {
            putInt("some data key for my activity", 15)
        }

        // shared preferences (multiple preferences files)
        // arguably the most useful
        val activitySharedPref = getSharedPreferences("my activity file name", Context.MODE_PRIVATE)
        activitySharedPref.edit {
            putBoolean("my data key", true)
        }
    }

    private fun setupUi() {
        title = "$distance Record"
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
        binding.buttonDelete.setOnClickListener {
            deleteRecord()
            finish()
        }
    }

    private fun deleteRecord() {
        runningSharedPref.edit {
            remove("$distance record")
            remove("$distance date")
        }
    }

    private fun displayRecord() {
        binding.editTextRecord.setText(runningSharedPref.getString("$distance record", null))
        binding.editTextDate.setText(runningSharedPref.getString("$distance date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

//        val editor = runningSharedPref.edit()
//        editor.putString("record", record)
//        editor.putString("date", date)
//        // commit changes to the shared pref file
//        editor.apply()

        runningSharedPref.edit {
            putString("$distance record", record)
            putString("$distance date", date)
        }
    }
}