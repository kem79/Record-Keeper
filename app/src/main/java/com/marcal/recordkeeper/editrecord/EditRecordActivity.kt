package com.marcal.recordkeeper.editrecord

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.marcal.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding
    private val screenData: ScreenData by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA, ScreenData::class.java) as ScreenData
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA) as ScreenData
        }
    }
    private val recordPreferences by lazy { getSharedPreferences(screenData.sharedPreferencesName,
        MODE_PRIVATE
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
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
        val activityPreferences = getPreferences(MODE_PRIVATE)
        activityPreferences.edit {
            putInt("some data key for my activity", 15)
        }

        // shared preferences (multiple preferences files)
        // arguably the most useful
        val activitySharedPref = getSharedPreferences("my activity file name", MODE_PRIVATE)
        activitySharedPref.edit {
            putBoolean("my data key", true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
            else -> {
                return true
            }
        }
    }

    private fun setupUi() {
        title = "${screenData.record} Record"
        binding.textInputRecord.hint = screenData.recordFiledHint
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
        recordPreferences.edit {
            remove("${screenData.record} $SHARED_PREFERENCES_RECORD_KEY")
            remove("${screenData.record} $SHARED_PREFERENCES_DATE_KEY")
        }
    }

    private fun displayRecord() {
        binding.editTextRecord.setText(recordPreferences.getString("${screenData.record} $SHARED_PREFERENCES_RECORD_KEY", null))
        binding.editTextDate.setText(recordPreferences.getString("${screenData.record} $SHARED_PREFERENCES_DATE_KEY", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

//        val editor = runningSharedPref.edit()
//        editor.putString("record", record)
//        editor.putString("date", date)
//        // commit changes to the shared pref file
//        editor.apply()

        recordPreferences.edit {
            putString("${screenData.record} $SHARED_PREFERENCES_RECORD_KEY", record)
            putString("${screenData.record} $SHARED_PREFERENCES_DATE_KEY", date)
        }
    }

    companion object {
        const val SHARED_PREFERENCES_RECORD_KEY = "record"
        const val SHARED_PREFERENCES_DATE_KEY = "date"
        const val INTENT_EXTRA_SCREEN_DATA = "screen_data"
    }

    data class ScreenData(
        val record: String,
        val sharedPreferencesName: String,
        val recordFiledHint: String
    ): Serializable

}